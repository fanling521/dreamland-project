package com.fanling.dreamland.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 系统用户表 sys_user
 */
@Getter
@Setter
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = -1069958677744514805L;
    //用户标识
    private String userId;
    //登录名称
    private String loginName;
    //用户名称
    private String userName;
    //手机号码
    private String phone;
    //密码
    private String password;
    //盐加密
    private String salt;
    //帐号状态（0正常 1停用）
    private String status;
    //最后登陆IP
    private String loginIp;
    //最后登陆时间
    private Date loginDate;
    //角色集合
    private List<SysRole> roles;
    //角色组
    private String[] roleIds;

}
