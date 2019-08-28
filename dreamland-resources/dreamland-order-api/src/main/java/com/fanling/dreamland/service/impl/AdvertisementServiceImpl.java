package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.Advertisement;
import com.fanling.dreamland.mapper.AdvertisementMapper;
import com.fanling.dreamland.service.IAdvertisementService;
import org.springframework.stereotype.Service;

/**
 * 营销广告表的业务实现层
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper,Advertisement> implements IAdvertisementService {

}
