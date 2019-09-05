package com.fanling.dreamland.entity.request;

import lombok.Data;

@Data
public class RegBody {
    //账号（手机号）
    private String account;
    //密码（验证码）
    private String password;
    //手机类型安卓还是IOS
    private String phone_type;
    //手机操作系统版本
    private String phone_version;
}
