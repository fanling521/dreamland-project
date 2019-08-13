package com.fanling.dreamland.service;

import com.fanling.dreamland.common.service.IService;
import com.fanling.dreamland.entity.AppUser;

/**
 * APP用户表的业务层
 */
public interface IAppUserService extends IService<AppUser> {
    /**
     * 检查用户是否重复注册
     *
     * @param phone 手机号
     * @param key 角色（用户类型）
     * @return
     */
    boolean checkUserExists(String phone, String key);
}
