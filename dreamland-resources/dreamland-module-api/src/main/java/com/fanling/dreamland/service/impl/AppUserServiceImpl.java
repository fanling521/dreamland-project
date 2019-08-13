package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.service.ServiceImpl;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.mapper.AppUserMapper;
import com.fanling.dreamland.service.IAppUserService;
import org.springframework.stereotype.Service;

/**
 * APP用户表的业务实现层
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper,AppUser> implements IAppUserService {

    /**
     * 检查用户是否重复注册
     *
     * @param phone 手机号
     * @param key   角色（用户类型）
     * @return
     */
    @Override
    public boolean checkUserExists(String phone, String key) {
        return false;
    }
}
