package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.MaintenanceServiceOrder;
import com.fanling.dreamland.mapper.MaintenanceServiceOrderMapper;
import com.fanling.dreamland.service.IMaintenanceServiceOrderService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 保养服务表的业务实现层
 */
@Service
public class MaintenanceServiceOrderServiceImpl extends ServiceImpl<MaintenanceServiceOrderMapper,MaintenanceServiceOrder> implements IMaintenanceServiceOrderService {

}
