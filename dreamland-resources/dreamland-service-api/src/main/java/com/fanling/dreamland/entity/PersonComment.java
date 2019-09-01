package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 维修人员评价表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //订单标识
    private String order_id;
    //维修人员标识
    private String p_user_id;
    //星级评分（5）
    private String star_lv;
    //额外评价
    private String remark;
    //用户标识
    private String user_id;
}
