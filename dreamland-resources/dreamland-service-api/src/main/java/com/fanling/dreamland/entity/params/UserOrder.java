package com.fanling.dreamland.entity.params;

import lombok.Data;

@Data
public class UserOrder {
    //待付款
    private String need_to_pay;
    //待维修
    private String need_to_be_served;
    //待评论
    private String need_to_comment;
    //售后
    private String after_sale_count;
}
