package com.fanling.dreamland.entitys.system;

import com.fanling.dreamland.entitys.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //用户标识
    private String user_id;
    // 登录名称
    private String login_name;
    // 用户名称
    private String user_name;
    // 用户邮箱
    private String email;
    // 手机号码
    private String phone;
    // 密码
    private String password;
    // 盐加密
    private String salt;
    // 帐号状态（0正常 1停用）
    private String status;
    // 最后登陆IP
    private String login_ip;
    // 最后登陆时间
    private Date login_date;
    // 角色集合
    private List<SysRole> roles;
    // 角色组
    private String[] role_ids;

}
