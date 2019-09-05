package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP用户表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //性别
    private String gender;
    //用户名
    private String user_name;
    //手机号
    private String user_phone;
    //密码
    private String password;
    //盐
    private String slat;
    //上次登录时间
    private String last_login_date;
    //上次登录ip
    private String last_login_ip;
    //头像地址
    private String avatar;
}
