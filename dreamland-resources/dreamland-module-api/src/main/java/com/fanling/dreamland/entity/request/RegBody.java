package com.fanling.dreamland.entity.request;

import lombok.Data;

@Data
public class RegBody {
    //账号（手机号）
    private String account;
    //密码（验证码）
    private String password;
    //角色类型
    private String role_key;
    //手机识别码
    private String IMEI;
    //手机操作系统
    private String phone_os;
}
