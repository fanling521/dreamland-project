package com.fanling.dreamland.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.AppRole;

/**
 * APP角色表的业务层
 */
public interface IAppRoleService extends IService<AppRole> {

    /**
     * 根据角色的名称查询（用户的类型）
     *
     * @return
     */
    AppRole selectByRoleName(String roleName);
}
