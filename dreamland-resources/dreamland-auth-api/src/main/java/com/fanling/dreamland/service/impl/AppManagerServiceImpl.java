package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.AppManager;
import com.fanling.dreamland.mapper.AppManagerMapper;
import com.fanling.dreamland.service.IAppManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理人员表的业务实现层
 */
@Service
public class AppManagerServiceImpl extends ServiceImpl<AppManagerMapper, AppManager> implements IAppManagerService {

    @Autowired
    private AppManagerMapper appManagerMapper;

    /**
     * 登录信息获取
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public AppManager selectByLogin(String phone, String password) {
        return appManagerMapper.selectByLogin(phone, password);
    }
}
