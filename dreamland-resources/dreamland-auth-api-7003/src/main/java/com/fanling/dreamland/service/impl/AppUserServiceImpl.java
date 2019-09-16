package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.mapper.AppUserMapper;
import com.fanling.dreamland.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * APP用户表的业务实现层
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper,AppUser> implements IAppUserService {
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
