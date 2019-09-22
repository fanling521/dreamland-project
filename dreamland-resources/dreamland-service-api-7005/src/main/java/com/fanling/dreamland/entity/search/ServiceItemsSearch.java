package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 维修设计保养条目表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceItemsSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 条目名称
    private String item_name;
    // 主类名称
    private String top_name;
}