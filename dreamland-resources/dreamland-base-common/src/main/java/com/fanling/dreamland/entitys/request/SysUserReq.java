package com.fanling.dreamland.entitys.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserReq {
    //用户标识
    private String user_id;
    // 用户名称
    private String user_name;
    // 用户邮箱
    private String email;
    // 手机号码
    private String phone;
    // 角色组
    private String[] role_ids;
}
