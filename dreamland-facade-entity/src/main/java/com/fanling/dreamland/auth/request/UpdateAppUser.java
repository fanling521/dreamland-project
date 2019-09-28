package com.fanling.dreamland.auth.request;

import lombok.Data;

/**
 * 个人信息维护
 */
@Data
public class UpdateAppUser {
    //用户标识
    private String uid;
    //用户名称
    private String user_name;
    //性别
    private String gender;
    //用户原手机号
    private String old_phone;
    //验证码
    private String password;
    //用户新手机号
    private String new_phone;
    //手机类型安卓还是IOS
    private String phone_type;
    //手机操作系统版本
    private String phone_version;
    //头像
    private String avatar;
}
