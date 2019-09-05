package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.entity.AppManager;
import com.fanling.dreamland.mapper.AppManagerMapper;
import com.fanling.dreamland.service.IAppManagerService;
import com.fanling.common.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 管理人员表的业务实现层
 */
@Service
public class AppManagerServiceImpl extends ServiceImpl<AppManagerMapper,AppManager> implements IAppManagerService {

}
