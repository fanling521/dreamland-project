package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品一级分类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TopCategorySearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    //商品名称
    private String top_goods_name;
}