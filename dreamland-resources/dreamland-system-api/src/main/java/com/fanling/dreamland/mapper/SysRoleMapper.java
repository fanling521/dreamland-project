package com.fanling.dreamland.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表的数据层
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    List<SysRole> listById(@Param("user_id") String userId);

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
