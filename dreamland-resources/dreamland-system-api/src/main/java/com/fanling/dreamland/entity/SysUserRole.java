package com.fanling.dreamland.entity;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 */
@Data
public class SysUserRole {
    // 用户ID
    private String user_id;
    // 角色ID
    private String role_id;

}
