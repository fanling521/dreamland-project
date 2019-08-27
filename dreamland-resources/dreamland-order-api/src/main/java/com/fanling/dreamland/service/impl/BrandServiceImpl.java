package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.service.ServiceImpl;
import com.fanling.dreamland.entity.Brand;
import com.fanling.dreamland.mapper.BrandMapper;
import com.fanling.dreamland.service.IBrandService;
import org.springframework.stereotype.Service;

/**
 * 商品品牌表的业务实现层
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper,Brand> implements IBrandService {

}
