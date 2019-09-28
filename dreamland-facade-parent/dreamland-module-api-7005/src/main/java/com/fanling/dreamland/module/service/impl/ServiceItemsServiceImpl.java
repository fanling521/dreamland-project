package com.fanling.dreamland.module.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.module.entity.ServiceItems;
import com.fanling.dreamland.module.mapper.ServiceItemsMapper;
import com.fanling.dreamland.module.service.IServiceItemsService;
import org.springframework.stereotype.Service;

/**
 * 维修设计保养条目表的业务实现层
 */
@Service
public class ServiceItemsServiceImpl extends ServiceImpl<ServiceItemsMapper, ServiceItems> implements IServiceItemsService {

}
