package com.fanling.dreamland.module.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 售后处理
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AfterSale extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //订单标识
    private String order_id;
    //订单标题
    private String title;
    //总金额
    private String total_price;
    //用户标识
    private String user_id;
    //维修人员标识
    private String p_user_id;
    //商品标识
    private String goods_id;
    //用户名
    private String user_name;
    //用户手机号
    private String user_phone;
    //投诉原因
    private String complaint;
    //问题解决
    private String reply;
}
