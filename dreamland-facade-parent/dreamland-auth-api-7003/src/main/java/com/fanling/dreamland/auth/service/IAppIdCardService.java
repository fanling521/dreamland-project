package com.fanling.dreamland.auth.service;

import com.fanling.dreamland.auth.entity.AppIdCard;
import com.fanling.dreamland.common.service.IService;

/**
 * 用户身份证表的业务层
 */
public interface IAppIdCardService extends IService<AppIdCard> {

    /**
     * 根据用用户标识查询实名信息
     *
     * @param userId
     * @return
     */
    AppIdCard selectByUid(String userId);

}
