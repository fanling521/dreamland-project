package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.Order;
import com.fanling.dreamland.mapper.OrderMapper;
import com.fanling.dreamland.service.IOrderService;
import com.fanling.dreamland.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单表的业务实现层
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {

}
