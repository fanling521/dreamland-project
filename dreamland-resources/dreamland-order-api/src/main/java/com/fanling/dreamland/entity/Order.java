package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 订单表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //用户标识
    private String user_id;
    //商品标识
    private String goods_id;
    //维修人员标识
    private String p_user_id;
    //订单状态
    private String order_status;
    //订单号
    private String order_no;
}
