package com.fanling.dreamland.entity.request;

import lombok.Data;

@Data
public class LoginBody {
    //账号（手机号）
    private String account;
    //密码（验证码）
    private String password;
    //角色类型
    private String role_key;
    //登录ip
    private String login_ip;
    //国际移动设备识别码
    private String IMEI;
    //国际移动用户识别码
    private String IMSI;
}
