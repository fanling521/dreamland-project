package com.fanling.dreamland.entitys.moudle.member;

import com.fanling.dreamland.entitys.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 管理员用户表，登录标识就是手机号
 */
@Getter
@Setter
public class BaseAppUser extends BaseEntity {
    private String user_id;
    private String user_name;
    private String phone;
    private String user_status;
    private String login_ip;
    private Date login_date;
    private String password;
    private String salt;
}
