package com.fanling.dreamland.mapper;

import com.fanling.dreamland.entitys.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表的数据层
 */
@Mapper
public interface SysRoleMapper {
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
    List<SysRole> selectRolesByUserId(@Param("user_id") String userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     */
    SysRole selectRoleById(@Param("role_id") String roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     */
    int deleteRoleById(@Param("role_id") String roleId);

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
    int checkRoleNameUnique(@Param("role_name") String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey
     */
    int checkRoleKeyUnique(@Param("role_key") String roleKey);
}
