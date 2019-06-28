package com.fanling.dreamland.shiro.exception;

public class RoleBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super(null, "用户/角色已经被禁用");
    }
}
