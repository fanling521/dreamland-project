package com.fanling.dreamland.auth.config;

/**
 * 系统参数枚举
 */
public enum DefaultEnum {
    DEFAULT_SALT("1F2A3N4L5I6N7G"),//默认盐
    DEFAULT_SEX("1"),//默认性别
    DEFAULT_PASSWORD("e10adc3949ba59abbe56e057f20f883e");//默认密码

    private String code;

    private DefaultEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
