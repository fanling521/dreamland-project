package com.fanling.dreamland.module.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 标题
    private String title;
}