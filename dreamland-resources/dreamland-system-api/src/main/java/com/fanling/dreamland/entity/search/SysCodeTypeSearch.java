package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统代码类别表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysCodeTypeSearch extends BaseSearch {
    private static final long serialVersionUID = 1604985544729845358L;
    // 类别名称
    private String code_type_name;
    // 类别值
    private String code_type_value;
}