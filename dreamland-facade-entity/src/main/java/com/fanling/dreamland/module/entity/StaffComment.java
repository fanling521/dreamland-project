package com.fanling.dreamland.module.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StaffComment extends BaseEntity {
    //订单
    private String order_id;
    //订单标题
    private String title;
    //维修人员id
    private String user_id;
    //维修人员名称
    private String user_name;
    //评价统计
    private String count;
    //维修人员的评价
    private String person_comment;
}
