package com.fanling.dreamland.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置表信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //配置描述
    private String config_name;
    //配置名称
    private String config_key;
    //配置值
    private String config_value;
}
