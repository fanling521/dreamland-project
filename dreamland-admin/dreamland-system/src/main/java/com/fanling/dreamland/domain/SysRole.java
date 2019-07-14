package com.fanling.dreamland.domain;


import lombok.Getter;
import lombok.Setter;

/**
 * 角色表 sys_role
 */
@Getter
@Setter
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 291847676340334821L;
    //角色ID
    private String roleId;
    //角色名称
    private String roleName;
    //角色排序
    private String roleSort;
    //角色权限
    private String roleKey;
    //角色状态（0正常 1停用）
    private String status;
    //用户是否存在此角色标识 默认不存在
    private boolean flag = false;
}
