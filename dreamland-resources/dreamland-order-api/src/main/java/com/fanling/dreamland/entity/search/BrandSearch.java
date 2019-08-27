package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BrandSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 品牌英文名称
    private String b_e_name;
}