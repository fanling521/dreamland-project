package com.fanling.dreamland.config;

import com.fanling.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaService {
    private final Logger logger = LoggerFactory.getLogger(CaptchaService.class);
    //过期时间
    private final static long EXPIRE = 60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    /**
     * 检查验证码，并且删除redis
     *
     * @param phone
     * @param captcha
     * @return
     */
    public boolean checkCaptcha(String phone, String captcha) {
        String redisCaptcha = ops.get("PHONE_" + phone);
        redisTemplate.delete("PHONE_" + phone);
        return captcha.equals(redisCaptcha);
    }

    /**
     * 是否存在手机号的redis值
     * @param phone
     * @return
     */
    public boolean getCaptcha(String phone) {
        return StringUtils.isNotEmpty(ops.get("PHONE_" + phone));
    }

    /**
     * @param phone
     * @param captcha
     */
    public void setCaptcha(String phone, String captcha) {
        ops.set("PHONE_" + phone, captcha, EXPIRE, TimeUnit.SECONDS);
        logger.info("---> 设置redis手机验证码：{}-{}", phone, captcha);
    }

    /**
     * 随机验证码生成
     *
     * @return
     */
    public String randomCaptcha() {
        StringBuilder code = new StringBuilder();
        int[] codeArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 6; i++) {
            int random = new Random().nextInt(9);
            code.append(codeArr[random]);
        }
        return code.toString();
    }
}
