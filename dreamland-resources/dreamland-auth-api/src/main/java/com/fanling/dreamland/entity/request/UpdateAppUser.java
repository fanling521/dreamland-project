package com.fanling.dreamland.entity.request;

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
    private String sex;
    //用户原手机号
    private String old_phone;
    //角色
    private String role_key;
    //验证码
    private String password;
    //用户新手机号
    private String new_phone;
    //国际移动设备识别码
    private String IMEI;
    //国际移动用户识别码
    private String IMSI;
    //手机操作系统
    private String phone_os;
}
