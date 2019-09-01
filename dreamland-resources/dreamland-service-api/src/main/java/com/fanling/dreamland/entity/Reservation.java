package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务订购/预约表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Reservation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //坐标位置
    private String position;
    //小区室号
    private String room_info;
    //省市区街道
    private String area;
    //预约时间
    private String reservation_date;
    //小区名称
    private String community_name;
    //服务费用
    private String price;
    //预约标记
    private String reservation_type;
    //(服务项目)商品id
    private String goods_id;
}
