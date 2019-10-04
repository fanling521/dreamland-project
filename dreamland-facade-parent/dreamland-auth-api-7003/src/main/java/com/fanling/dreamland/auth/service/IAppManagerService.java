package com.fanling.dreamland.auth.service;

import com.fanling.dreamland.auth.entity.AppManager;
import com.fanling.dreamland.common.service.IService;

/**
 * 管理人员表的业务层
 */
public interface IAppManagerService extends IService<AppManager> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @param password
     * @return
     */
    AppManager selectByLogin(String phone, String password);
}
