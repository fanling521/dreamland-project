package com.fanling.dreamland.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fanling.dreamland.R;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * jwt 授权工具
 * <p>
 * redis 12小时
 */
@Component
public class JwtTokenService {
    //过期时间 12 小时
    private final static long EXPIRE = 3600 * 12;
    //ACCESS_TOKEN
    private final static String ACCESS_TOKEN = "token_";
    //ACCESS_USER_ID
    private final static String ACCESS_USER_ID = "userid_";


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    /**
     * 从redis中取userId
     *
     * @param token
     * @return
     */
    public String getUserId(String token) {
        return ops.get(ACCESS_TOKEN + token);
    }

    /**
     * 从redis中取token
     *
     * @param userId
     * @return
     */
    public String getToken(String userId) {
        return ops.get(ACCESS_USER_ID + userId);
    }

    public R createToken(String userId, String phone, String password) {
        //检查redis 是否存在，存在则删除，重新生成
        String ex = ops.get(ACCESS_USER_ID + userId);
        if (StringUtils.isNotEmpty(ex)) {
            redisTemplate.delete(ACCESS_TOKEN + ex);
        }
        // 生成token
        String token = sign(password);
        // 保存或更新用户token
        R data = new R();
        data.put("userId", userId);
        data.put("token", token);
        //有则更新，无则新增
        ops.set(ACCESS_TOKEN + token, userId, EXPIRE, TimeUnit.SECONDS);
        ops.set(ACCESS_USER_ID + userId, token, EXPIRE, TimeUnit.SECONDS);
        return data;
    }

    /**
     * 生成签名,指定时间后过期
     *
     * @param password 用户的密码
     * @return 加密的token
     */
    public static String sign(String password) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create().sign(algorithm);
    }

    /**
     * 删除token
     *
     * @param userId
     */
    public void expireToken(String userId) {
        String token = ops.get(ACCESS_USER_ID + userId);
        if (StringUtils.isNotEmpty(token)) {
            redisTemplate.delete(ACCESS_USER_ID + userId);
            redisTemplate.delete(ACCESS_TOKEN + token);
        }
    }
}
