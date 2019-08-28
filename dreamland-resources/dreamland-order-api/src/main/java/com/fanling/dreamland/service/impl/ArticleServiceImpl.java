package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.Article;
import com.fanling.dreamland.mapper.ArticleMapper;
import com.fanling.dreamland.service.IArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章信息表的业务实现层
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements IArticleService {

}
