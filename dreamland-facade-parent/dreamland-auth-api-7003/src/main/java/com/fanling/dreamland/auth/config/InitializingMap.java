package com.fanling.dreamland.auth.config;

import com.fanling.dreamland.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色信息的初始化，是固定的值
 */
@Slf4j
@Component
public class InitializingMap implements InitializingBean {
    //验证码类型
    private static Map<String, String> captchaMap = new HashMap<>();

    /**
     * 检查验证是否符合规则
     * @param key
     * @return
     */
    public static boolean checkCaptcha(String key) {
        return StringUtils.isEmpty(captchaMap.getOrDefault(key, ""));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("--->初始化验证码类型");
        captchaMap.put("L1","login");
        captchaMap.put("R1","reg");
        captchaMap.put("R2","rebind");
    }
}
