package com.fanling.dreamland.shiro.exception;

public class UserDeleteException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super(null, "用户已经被删除");

    }
}
