package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.service.ServiceImpl;
import com.fanling.dreamland.entity.SysConfig;
import com.fanling.dreamland.mapper.SysConfigMapper;
import com.fanling.dreamland.service.ISysConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统配置表信息的业务实现层
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

}
