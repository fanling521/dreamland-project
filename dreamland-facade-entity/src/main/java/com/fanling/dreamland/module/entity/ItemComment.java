package com.fanling.dreamland.module.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemComment extends BaseEntity {
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
    //维修人员的评价
    private String person_comment;
    //维修人员评星
    private String staff_star;
    //用户评论
    private String remark;
}
