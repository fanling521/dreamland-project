package com.fanling.dreamland.auth.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * jwt 授权工具
 * <p>
 */
@Service
public class JwtTokenService {
    //过期时间 30天
    private final static long EXPIRE = 3600 * 24 * 30;
    //ACCESS_TOKEN
    private final static String ACCESS_TOKEN = "app_token_";
    //ACCESS_ID
    private final static String ACCESS_USER_ID = "app_id_";

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

    /**
     * 生成token
     *
     * @param id
     * @param password
     * @return
     */
    public R createToken(String id, String password) {
        //检查redis 是否存在，存在则删除，重新生成
        String ex = ops.get(ACCESS_USER_ID + id);
        if (StringUtils.isNotEmpty(ex)) {
            redisTemplate.delete(ACCESS_TOKEN + ex);
        }
        // 生成token
        String token = sign(id, password);
        // 保存或更新用户token
        R data = new R();
        data.put("token", token);
        //有则更新，无则新增
        ops.set(ACCESS_TOKEN + token, id, EXPIRE, TimeUnit.SECONDS);
        ops.set(ACCESS_USER_ID + id, token, EXPIRE, TimeUnit.SECONDS);
        return data;
    }

    /**
     * 生成签名,指定时间后过期
     *
     * @param uid
     * @param password 用户的密码
     * @return 加密的token
     */
    private static String sign(String uid, String password) {
        return JWT.create()
                .withClaim("account", uid)
                .sign(Algorithm.HMAC256(password));
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
