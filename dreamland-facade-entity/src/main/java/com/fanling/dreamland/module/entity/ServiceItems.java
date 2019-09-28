package com.fanling.dreamland.module.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 维修设计保养条目表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceItems extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //主类编号
    private String top;
    //分类编号
    private String sub_no;
    //条目名称
    private String item_name;
    //主类名称
    private String top_name;
    //条目图标
    private String item_icon;
}
