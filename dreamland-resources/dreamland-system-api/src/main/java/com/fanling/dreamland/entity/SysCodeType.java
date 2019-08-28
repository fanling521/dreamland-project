package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统代码类别表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysCodeType extends BaseEntity {
    private static final long serialVersionUID = -9148368962596780675L;
    //类别名称
    private String code_type_name;
    //类别值
    private String code_type_value;
    //类别标识
    private String code_type_id;
}
