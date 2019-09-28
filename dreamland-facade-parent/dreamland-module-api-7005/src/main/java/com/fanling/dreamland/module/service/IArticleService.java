package com.fanling.dreamland.module.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.module.entity.Article;

import java.util.List;

/**
 * 文章信息表的业务层
 */
public interface IArticleService extends IService<Article> {
    /**
     * App文章列表
     * @param article
     * @return
     */
    List<Article> appList(Article article);
}
