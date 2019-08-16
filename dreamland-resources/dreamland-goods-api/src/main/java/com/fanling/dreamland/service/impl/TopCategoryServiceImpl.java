package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.TopCategory;
import com.fanling.dreamland.mapper.TopCategoryMapper;
import com.fanling.dreamland.service.ITopCategoryService;
import com.fanling.dreamland.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品一级分类的业务实现层
 */
@Service
public class TopCategoryServiceImpl extends ServiceImpl<TopCategoryMapper,TopCategory> implements ITopCategoryService {

}
