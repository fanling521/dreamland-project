package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.AppRole;
import com.fanling.dreamland.mapper.AppRoleMapper;
import com.fanling.dreamland.service.IAppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * APP角色表的业务实现层
 */
@Service
public class AppRoleServiceImpl extends ServiceImpl<AppRoleMapper,AppRole> implements IAppRoleService {

    @Autowired
    private AppRoleMapper appRoleMapper;

    /**
     * 根据角色的名称查询（用户的类型）
     *
     * @param roleName
     * @return
     */
    @Override
    public AppRole selectByRoleName(String roleName) {
        return appRoleMapper.selectByRoleName(roleName);
    }
}
