package com.fanling.dreamland.domain.system;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色和菜单关联 sys_role_menu
 */
@Getter
@Setter
public class SysRoleMenu {
    //角色ID
    private String roleId;
    //菜单ID
    private String menuId;
}
