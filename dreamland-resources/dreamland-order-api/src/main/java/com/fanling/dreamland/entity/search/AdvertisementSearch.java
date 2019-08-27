package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
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
    private String width;
}