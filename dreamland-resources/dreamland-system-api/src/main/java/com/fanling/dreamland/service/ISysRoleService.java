package com.fanling.dreamland.service;


import com.fanling.dreamland.entitys.system.SysRole;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface ISysRoleService {
    /**
     * 根据条件返回角色表的信息，可查询和分页
     *
     * @param role
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    List<SysRole> selectRolesByUserId(String userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     */
    SysRole selectRoleById(String roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     */
    int deleteRoleById(String roleId);

    /**
     * 修改角色信息
     *
     * @param role
     */
    int updateRole(SysRole role);

    /**
     * 新增角色信息
     *
     * @param role
     */
    int insertRole(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName
     */
    boolean checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey
     */
    boolean checkRoleKeyUnique(String roleKey);

    /**
     * 获取所有角色列表
     *
     * @return
     */
    List<SysRole> selectRoleAll();

    /**
     * 检查角色是否分配了用户
     *
     * @param roleId
     * @return
     */
    boolean checkUserCount(String roleId);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRoleKeys(String userId);
}
