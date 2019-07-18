package com.fanling.dreamland.entitys.system;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户和角色关联 sys_user_role
 */
@Getter
@Setter
public class SysUserRole {
    // 用户ID
    private String user_id;
    // 角色ID
    private String role_id;

}
