package com.fanling.dreamland.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.Article;

import java.util.List;

/**
 * 文章信息表的数据层
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * App文章列表
     * @param article
     * @return
     */
    List<Article> appList(Article article);
}