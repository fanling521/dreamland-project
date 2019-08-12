package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统代码明细表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysCodeItemSearch extends BaseSearch {
    private static final long serialVersionUID = 3073911595573329698L;
    // 代码类型标识
    private String code_type_id;
    // 代码明细键
    private String code_item_key;
    // 代码明细值
    private String code_item_value;
}