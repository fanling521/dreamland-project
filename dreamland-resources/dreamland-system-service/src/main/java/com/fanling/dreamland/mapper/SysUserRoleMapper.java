package com.fanling.dreamland.mapper;

import com.fanling.dreamland.entitys.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色表的数据层
 */
@Mapper
public interface SysUserRoleMapper {
    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     */
    int deleteUserRoleByUserId(@Param("user_id") String userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param userId
     */
    int deleteUserRole(@Param("user_id") String[] userId);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     */
    int countUserRoleByRoleId(@Param("role_id") String roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList
     */
    int batchUserRole(List<SysUserRole> userRoleList);

    /**
     * 删除用户和角色关联信息
     *
     * @param userRole
     */
    int deleteUserRoleInfo(SysUserRole userRole);

}
