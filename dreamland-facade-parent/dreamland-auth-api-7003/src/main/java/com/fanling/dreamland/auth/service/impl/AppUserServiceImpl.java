package com.fanling.dreamland.auth.service.impl;

import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.auth.mapper.AppUserMapper;
import com.fanling.dreamland.auth.service.IAppUserService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * APP用户表的业务实现层
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {
    @Autowired
    protected AppUserMapper appUserMapper;
    /**
     * 登录信息获取
     *
     * @param phone
     * @return
     */
    @Override
    public AppUser selectByLogin(String phone) {
        return appUserMapper.selectByLogin(phone);
    }
}
