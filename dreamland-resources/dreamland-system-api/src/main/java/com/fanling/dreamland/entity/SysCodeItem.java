package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统代码明细表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysCodeItem extends BaseEntity {
    private static final long serialVersionUID = -4485434856869312167L;
    //代码类别标识
    private String code_type_id;
    //代码明细键
    private String code_item_key;
    //代码明细标识
    private String code_item_id;
    //代码明细值
    private String code_item_value;
}
