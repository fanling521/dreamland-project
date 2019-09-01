package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 维修人员评价表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonCommentSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 星级评分（5）
    private String star_lv;
}