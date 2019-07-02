package com.fanling.dreamland.mapper.system;

import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.domain.system.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 */
public interface SysRoleMenuMapper {
    /**
     * 通过菜单ID删除角色和菜单关联
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteRoleMenuByMenuId(String menuId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(String menuId);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单
     * @return 结果
     */
    int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
