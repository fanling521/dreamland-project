package com.fanling.dreamland.config;

/**
 * 系统参数的一些枚举信息
 */
public enum SystemEnum {
    USER_COMMON("1"),
    USER_DISABLE("2"),
    //123456的md5
    DEFAULT_PWD("e10adc3949ba59abbe56e057f20f883e"),
    SYSTEM_USER("d466b2b8-223d-4983-bb34-c4253621bb58"),
    SYSTEM_ROLE("d92a9960-dc74-4a55-b3bd-46ec909205e0");


    private String code;

    private SystemEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
