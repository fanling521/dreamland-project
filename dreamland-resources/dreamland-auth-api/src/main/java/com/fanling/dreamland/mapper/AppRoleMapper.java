package com.fanling.dreamland.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.AppRole;
import org.apache.ibatis.annotations.Param;

/**
 * APP角色表的数据层
 */
public interface AppRoleMapper extends BaseMapper<AppRole> {

    /**
     * 根据角色的名称查询（用户的类型）
     *
     * @return
     */
    AppRole selectByRoleName(@Param("role_name") String roleName);
}
