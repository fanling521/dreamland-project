package com.fanling.dreamland.shiro.exception;

public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super(null, "密码不匹配");
    }
}
