package com.fanling.dreamland.module.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.module.entity.RepairServiceOrder;
import com.fanling.dreamland.module.mapper.RepairServiceOrderMapper;
import com.fanling.dreamland.module.service.IRepairServiceOrderService;
import org.springframework.stereotype.Service;

/**
 * 维修服务表的业务实现层
 */
@Service
public class RepairServiceOrderServiceImpl extends ServiceImpl<RepairServiceOrderMapper, RepairServiceOrder> implements IRepairServiceOrderService {

}
