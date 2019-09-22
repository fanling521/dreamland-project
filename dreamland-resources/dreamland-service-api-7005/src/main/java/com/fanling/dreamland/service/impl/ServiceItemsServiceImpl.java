package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.ServiceItems;
import com.fanling.dreamland.mapper.ServiceItemsMapper;
import com.fanling.dreamland.service.IServiceItemsService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 维修设计保养条目表的业务实现层
 */
@Service
public class ServiceItemsServiceImpl extends ServiceImpl<ServiceItemsMapper,ServiceItems> implements IServiceItemsService {

}
