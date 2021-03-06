package com.fanling.dreamland.module.service;

import com.fanling.dreamland.common.service.IService;
import com.fanling.dreamland.module.entity.ServiceItems;

/**
 * 维修设计保养条目表的业务层
 */
public interface IServiceItemsService extends IService<ServiceItems> {
    /**
     * 根据商品号查询
     *
     * @param subNo
     * @return
     */
    ServiceItems selectBySubNo(String subNo);
}
