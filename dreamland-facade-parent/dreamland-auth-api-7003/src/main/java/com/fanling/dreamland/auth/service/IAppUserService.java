package com.fanling.dreamland.auth.service;

import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.common.service.IService;

/**
 * APP用户表的业务层
 */
public interface IAppUserService extends IService<AppUser> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @return
     */
    AppUser selectByLogin(String phone);
}
