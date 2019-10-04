package com.fanling.dreamland.module.service.impl;

import com.fanling.dreamland.common.service.impl.ServiceImpl;
import com.fanling.dreamland.module.entity.Advertisement;
import com.fanling.dreamland.module.mapper.AdvertisementMapper;
import com.fanling.dreamland.module.service.IAdvertisementService;
import org.springframework.stereotype.Service;

/**
 * 营销广告表的业务实现层
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements IAdvertisementService {

}
