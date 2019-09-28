package com.fanling.dreamland.module.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Payment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //支付状态
    private String pay_status;
    //订单标识
    private String order_id;
    //商品标识
    private String goods_id;
    //用户标识
    private String user_id;
    //维修人员标识
    private String p_user_id;
    //付款金额
    private String money;
    //支付订单号
    private String payment_no;
    //支付类型
    private String pay_type;
    //支付流水号
    private String payment_seq_no;
}
