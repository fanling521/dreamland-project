package com.fanling.dreamland.auth.service.impl;

import com.fanling.dreamland.auth.entity.AppManager;
import com.fanling.dreamland.auth.mapper.AppManagerMapper;
import com.fanling.dreamland.auth.service.IAppManagerService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
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
