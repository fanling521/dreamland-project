package com.fanling.dreamland.entitys.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysUserSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 用户名称
    private String user_name;
    // 帐号状态（0正常 1停用）
    private String status;
}
