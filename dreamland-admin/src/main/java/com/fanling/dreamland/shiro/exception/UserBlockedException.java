package com.fanling.dreamland.shiro.exception;

public class UserBlockedException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super(null, "用户已经被禁用");
    }
}
