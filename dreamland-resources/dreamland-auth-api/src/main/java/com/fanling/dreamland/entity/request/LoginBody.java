package com.fanling.dreamland.entity.request;

import lombok.Data;

@Data
public class LoginBody {
    //账号（手机号）
    private String account;
    //密码（验证码）
    private String password;
    //角色类型
    private String role_type;
    //登录ip
    private String login_ip;
    //手机识别码
    private String phone_IMEI;
}
