package com.fanling.dreamland.entitys.system;

import com.fanling.dreamland.entitys.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色表 sys_role
 */
@Getter
@Setter
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    // 角色ID
    private String role_id;
    // 角色名称
    private String role_name;
    // 角色权限
    private String role_key;
    // 角色排序
    private String role_sort;
    // 角色状态（0正常 1停用）
    private String role_status;
    // 用户是否存在此角色标识 默认不存在
    private boolean flag = false;
}
