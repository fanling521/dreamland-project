package com.fanling.dreamland.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户和角色关联 sys_user_role
 */
@Getter
@Setter
public class SysUserRole {
    //用户ID
    private String userId;
    //角色ID
    private String roleId;

}
