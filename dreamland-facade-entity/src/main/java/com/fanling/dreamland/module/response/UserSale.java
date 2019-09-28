package com.fanling.dreamland.module.response;

import lombok.Data;

@Data
public class UserSale {
    //待付款
    private int need_to_pay;
    //待维修
    private int need_to_be_served;
    //待评论
    private int need_to_comment;
    //售后
    private int after_sale_count;
}
