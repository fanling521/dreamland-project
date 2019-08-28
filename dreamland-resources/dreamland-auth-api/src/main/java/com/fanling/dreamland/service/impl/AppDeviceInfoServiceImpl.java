package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.entity.AppDeviceInfo;
import com.fanling.dreamland.mapper.AppDeviceInfoMapper;
import com.fanling.dreamland.service.IAppDeviceInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户移动设备信息表的业务实现层
 */
@Service
public class AppDeviceInfoServiceImpl extends ServiceImpl<AppDeviceInfoMapper,AppDeviceInfo> implements IAppDeviceInfoService {

}
