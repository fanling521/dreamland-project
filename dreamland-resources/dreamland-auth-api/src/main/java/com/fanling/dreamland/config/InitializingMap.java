package com.fanling.dreamland.config;

import com.fanling.common.utils.StringUtils;
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
    //用户角色信息
    private static Map<String, String> roleMap = new HashMap<>();
    //验证码类型
    private static Map<String, String> captchaMap = new HashMap<>();

    /**
     * 检查是否存在这个角色
     *
     * @param key
     * @return
     */
    public static boolean checkRole(String key) {
        return StringUtils.isEmpty(roleMap.getOrDefault(key, ""));
    }

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
        log.info("--->初始化用户角色信息");
        roleMap.put("C", "普通会员");
        roleMap.put("P", "维修人员");

        log.info("--->初始化验证码类型");
        captchaMap.put("login","登录");
        captchaMap.put("reg","注册");
        captchaMap.put("rebind","重新绑定");
    }
}
