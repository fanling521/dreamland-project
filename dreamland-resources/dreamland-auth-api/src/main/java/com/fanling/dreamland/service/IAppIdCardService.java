package com.fanling.dreamland.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.AppIdCard;

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
