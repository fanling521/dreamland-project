package com.fanling.dreamland.module.config;

/**
 * 系统参数枚举
 */
public enum ModuleEnum {
    NEED_TO_PAY("1"),//默认盐
    NEED_TO_REPAIRE("2"),//默认性别
    NEED_TO_COMMENT("3"),//默认密码
    FINISH("4");
    private String code;

    private ModuleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
