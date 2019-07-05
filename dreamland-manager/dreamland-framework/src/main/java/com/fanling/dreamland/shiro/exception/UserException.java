package com.fanling.dreamland.shiro.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException {
    /**
     * 错误信息
     */
    private String defaultMessage;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public UserException(Object[] args, String defaultMessage) {
        this.args = args;
        this.defaultMessage = defaultMessage;
    }
}
