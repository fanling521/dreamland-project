package com.fanling.dreamland.auth.service.impl;

import com.fanling.dreamland.auth.entity.AppDeviceInfo;
import com.fanling.dreamland.auth.mapper.AppDeviceInfoMapper;
import com.fanling.dreamland.auth.service.IAppDeviceInfoService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户移动设备信息表的业务实现层
 */
@Service
public class AppDeviceInfoServiceImpl extends ServiceImpl<AppDeviceInfoMapper, AppDeviceInfo> implements IAppDeviceInfoService {

}
