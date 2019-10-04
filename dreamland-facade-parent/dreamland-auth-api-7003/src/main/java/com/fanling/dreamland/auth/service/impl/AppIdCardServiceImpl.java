package com.fanling.dreamland.auth.service.impl;

import com.fanling.dreamland.auth.entity.AppIdCard;
import com.fanling.dreamland.auth.mapper.AppIdCardMapper;
import com.fanling.dreamland.auth.service.IAppIdCardService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户身份证表的业务实现层
 */
@Service
public class AppIdCardServiceImpl extends ServiceImpl<AppIdCardMapper, AppIdCard> implements IAppIdCardService {

    @Autowired
    private AppIdCardMapper appIdCardMapper;

    /**
     * 根据用用户标识查询实名信息
     *
     * @param userId
     * @return
     */
    @Override
    public AppIdCard selectByUid(String userId) {
        return appIdCardMapper.selectByUid(userId);
    }
}
