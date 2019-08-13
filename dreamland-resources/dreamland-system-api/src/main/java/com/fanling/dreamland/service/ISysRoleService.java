package com.fanling.dreamland.service;

import com.fanling.dreamland.common.service.IService;
import com.fanling.dreamland.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    List<SysRole> ListById(String userId);

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
