package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.entity.ServiceItems;
import com.fanling.dreamland.module.mapper.ServiceItemsMapper;
import com.fanling.dreamland.module.service.IServiceItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 维修设计保养条目表的业务实现层
 */
@Service
public class ServiceItemsServiceImpl extends ServiceImpl<ServiceItemsMapper, ServiceItems> implements IServiceItemsService {

    @Autowired
    private ServiceItemsMapper serviceItemsMapper;

    /**
     * 根据商品号查询
     *
     * @param subNo
     * @return
     */
    @Override
    public ServiceItems selectBySubNo(String subNo) {
        return serviceItemsMapper.selectBySubNo(subNo);
    }


}
