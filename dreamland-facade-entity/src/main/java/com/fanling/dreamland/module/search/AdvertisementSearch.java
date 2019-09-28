package com.fanling.dreamland.module.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 营销广告表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdvertisementSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 宽度
    private String title;
}