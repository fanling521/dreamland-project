package com.fanling.dreamland.mapper;

import com.fanling.dreamland.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色表的数据层
 */
public interface SysUserRoleMapper {
    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     */
    void deleteUserRoleByUserId(@Param("user_id") String userId);

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
    void batchUserRole(List<SysUserRole> userRoleList);

}
