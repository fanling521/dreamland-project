package com.fanling.dreamland.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.AppUser;

/**
 * APP用户表的业务层
 */
public interface IAppUserService extends IService<AppUser> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @param key
     * @return
     */
    AppUser selectByLogin(String phone, String key);
}
