package com.fanling.dreamland.auth.service.impl;

import com.fanling.dreamland.auth.entity.AppStaff;
import com.fanling.dreamland.auth.mapper.AppStaffMapper;
import com.fanling.dreamland.auth.service.IAppStaffService;
import com.fanling.dreamland.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 维修人员表的业务实现层
 */
@Service
public class AppStaffServiceImpl extends ServiceImpl<AppStaffMapper, AppStaff> implements IAppStaffService {

}
