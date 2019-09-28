package com.fanling.dreamland.module.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.module.entity.Payment;
import com.fanling.dreamland.module.mapper.PaymentMapper;
import com.fanling.dreamland.module.service.IPaymentService;
import org.springframework.stereotype.Service;

/**
 * 支付表的业务实现层
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}
