package com.fanling.dreamland.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.AppManager;

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
