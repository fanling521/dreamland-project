package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.entity.MaintenanceServiceOrder;
import com.fanling.dreamland.module.mapper.MaintenanceServiceOrderMapper;
import com.fanling.dreamland.module.service.IMaintenanceServiceOrderService;
import org.springframework.stereotype.Service;

/**
 * 保养服务表的业务实现层
 */
@Service
public class MaintenanceServiceOrderServiceImpl extends ServiceImpl<MaintenanceServiceOrderMapper, MaintenanceServiceOrder> implements IMaintenanceServiceOrderService {

}
