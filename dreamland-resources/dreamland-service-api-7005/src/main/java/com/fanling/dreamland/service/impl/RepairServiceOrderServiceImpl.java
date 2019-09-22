package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.RepairServiceOrder;
import com.fanling.dreamland.mapper.RepairServiceOrderMapper;
import com.fanling.dreamland.service.IRepairServiceOrderService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 维修服务表的业务实现层
 */
@Service
public class RepairServiceOrderServiceImpl extends ServiceImpl<RepairServiceOrderMapper,RepairServiceOrder> implements IRepairServiceOrderService {

}
