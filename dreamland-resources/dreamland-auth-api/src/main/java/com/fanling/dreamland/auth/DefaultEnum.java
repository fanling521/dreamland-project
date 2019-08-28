package com.fanling.dreamland.auth;

/**
 * 系统参数枚举
 */
public enum DefaultEnum {
    USER_COMMON("1"),
    DEFAULT_SALT("1F2A3N4L5I6N7G"),//默认盐
    DEFAULT_SEX("1"),//默认性别
    USER_DISABLE("2"),
    SYSTEM_USER("d466b2b8-223d-4983-bb34-c4253621bb58"),
    SYSTEM_ROLE("d92a9960-dc74-4a55-b3bd-46ec909205e0"),
    DEFAULT_PASSWORD("e10adc3949ba59abbe56e057f20f883e");//默认密码

    private String code;

    private DefaultEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
