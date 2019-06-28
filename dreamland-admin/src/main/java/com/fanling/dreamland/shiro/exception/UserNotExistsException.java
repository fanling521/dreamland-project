package com.fanling.dreamland.shiro.exception;

public class UserNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super(null, "用户不存在");
    }
}
