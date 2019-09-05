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
    //手机操作系统版本
    private String phone_version;
    //手机操作系统
    private String phone_type;
    //手机号码
    private String user_phone;
    //用户标识
    private String user_id;
}
