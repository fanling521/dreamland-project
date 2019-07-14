package com.fanling.dreamland.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fanling.dreamland.R;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenService {
    //过期时间 1 小时
    private final static long EXPIRE = 3600 * 12;
    //ACCESS_TOKEN
    private final static String ACCESS_TOKEN = "access_token_";
    //ACCESS_USERID
    private final static String ACCESS_USERID = "access_userid_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    public String selectByToken(String token) {
        return ops.get(ACCESS_TOKEN + token);
    }

    /**
     * 创建token
     *
     * @param userId    用户标识
     * @param loginName 用户名
     * @param password  密码
     * @return
     */
    public R createToken(String userId, String loginName, String password) {
        // 生成token
        String token = sign(loginName, password);
        // 保存或更新用户token
        R data = new R();
        data.put("userId", userId);
        data.put("token", token);
        //秒
        data.put("expire", EXPIRE);

        ops.set(ACCESS_TOKEN + token, userId, EXPIRE, TimeUnit.SECONDS);
        ops.set(ACCESS_USERID + userId, token, EXPIRE, TimeUnit.SECONDS);
        return data;
    }

    /**
     * 删除token
     *
     * @param userId
     */
    public void expireToken(String userId) {
        String token = ops.get(ACCESS_USERID + userId);
        if (StringUtils.isNotEmpty(token)) {
            redisTemplate.delete(ACCESS_USERID + userId);
            redisTemplate.delete(ACCESS_TOKEN + token);
        }
    }

    /**
     * 生成签名,指定时间后过期
     *
     * @param loginName 用户名
     * @param password  用户的密码
     * @return 加密的token
     */
    public static String sign(String loginName, String password) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE * 24 * 7);
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create().withClaim("loginName", loginName).withExpiresAt(date).sign(algorithm);
    }
}
