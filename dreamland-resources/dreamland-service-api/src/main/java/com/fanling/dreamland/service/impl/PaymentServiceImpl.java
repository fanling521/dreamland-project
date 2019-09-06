package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.Payment;
import com.fanling.dreamland.mapper.PaymentMapper;
import com.fanling.dreamland.service.IPaymentService;
import org.springframework.stereotype.Service;

/**
 * 支付表的业务实现层
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper,Payment> implements IPaymentService {

}