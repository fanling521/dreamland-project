package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.AppStaff;
import com.fanling.dreamland.mapper.AppStaffMapper;
import com.fanling.dreamland.service.IAppStaffService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 维修人员表的业务实现层
 */
@Service
public class AppStaffServiceImpl extends ServiceImpl<AppStaffMapper,AppStaff> implements IAppStaffService {

}
