package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 保养服务表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MaintenanceServiceOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //客户姓名
    private String user_name;
    //实际付款金额
    private String user_pay;
    //坐标位置
    private String position;
    //小区室号
    private String community_room;
    //省市区街道
    private String region;
    //预约时间
    private Date reservation_date;
    //小区名称
    private String community_name;
    //服务费用
    private String total_price;
    //维修类型
    private String repair_type;
    //商品id
    private String good_id;
    //标题
    private String title;
    //订单编号
    private String order_no;
    //订单状态
    private String status;
    //客户标识
    private String user_id;
    //备注
    private String remark;
}
