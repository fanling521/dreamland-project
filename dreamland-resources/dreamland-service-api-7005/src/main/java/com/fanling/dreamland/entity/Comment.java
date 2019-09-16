package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 商品评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //用户标识
    private String user_id;
    //是否匿名
    private String anonymous;
    //订单标识
    private String order_id;
    //用户评论
    private String remark;
}
