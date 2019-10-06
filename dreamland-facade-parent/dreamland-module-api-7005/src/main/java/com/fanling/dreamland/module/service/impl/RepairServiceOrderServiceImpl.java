package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.config.ModuleEnum;
import com.fanling.dreamland.module.entity.RepairServiceOrder;
import com.fanling.dreamland.module.mapper.RepairServiceOrderMapper;
import com.fanling.dreamland.module.service.IRepairServiceOrderService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 维修服务表的业务实现层
 */
@Service
public class RepairServiceOrderServiceImpl extends ServiceImpl<RepairServiceOrderMapper, RepairServiceOrder> implements IRepairServiceOrderService {
    /**
     * 新增t表的信息
     *
     * @param repairServiceOrder
     * @return
     */
    @Override
    public int insert(RepairServiceOrder repairServiceOrder) {
        //设置订单号
        Random random = new Random();
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyyMMddHHmmssSSS" + random.nextInt(10000));
        repairServiceOrder.setOrder_no(ft1.format(new Date()));
        //待付款--1
        //待维修--2
        //待评价--3
        //已完成--4
        repairServiceOrder.setStatus(ModuleEnum.NEED_TO_PAY.getCode());
        return super.insert(repairServiceOrder);
    }
}
