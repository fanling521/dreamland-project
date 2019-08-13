package com.fanling.dreamland.mapper;

import com.fanling.dreamland.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.SysCodeType;

import java.util.List;
import java.util.Map;

/**
 * 系统代码类别表的数据层
 */
public interface SysCodeTypeMapper extends BaseMapper<SysCodeType> {

    /**
     * 获取代码类型的树形数据
     *
     * @return
     */
    List<Map<String, String>> tree();
}
