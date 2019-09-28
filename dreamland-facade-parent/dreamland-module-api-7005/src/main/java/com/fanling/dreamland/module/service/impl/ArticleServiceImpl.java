package com.fanling.dreamland.module.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.module.entity.Article;
import com.fanling.dreamland.module.mapper.ArticleMapper;
import com.fanling.dreamland.module.service.IArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章信息表的业务实现层
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
}
