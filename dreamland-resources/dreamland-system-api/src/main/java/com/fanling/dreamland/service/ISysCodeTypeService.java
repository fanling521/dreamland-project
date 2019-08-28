package com.fanling.dreamland.service;

import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.SysCodeType;

import java.util.List;
import java.util.Map;

/**
 * 系统代码类别表的业务层
 */
public interface ISysCodeTypeService extends IService<SysCodeType> {

    /**
     * 获取代码类型的树形数据
     * @return
     */
    List<Map<String, String>> tree();
}
