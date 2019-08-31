package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户移动设备信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppDeviceInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //国际移动设备识别码
    private String IMEI;
    //国际移动用户识别码
    private String IMSI;
    //手机操作系统
    private String phone_os;
    //手机号码
    private String user_phone;
    //用户标识
    private String user_id;
}
