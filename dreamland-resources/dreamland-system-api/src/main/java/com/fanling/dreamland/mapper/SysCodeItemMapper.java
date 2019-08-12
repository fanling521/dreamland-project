package com.fanling.dreamland.mapper;

import com.fanling.dreamland.common.BaseMapper;
import com.fanling.dreamland.entity.SysCodeItem;
import com.fanling.dreamland.entity.SysCodeMain;

import java.util.List;

/**
 * 系统代码明细表的数据层
 */
public interface SysCodeItemMapper extends BaseMapper<SysCodeItem> {

    /**
     * 获取所有的代码项-缓存
     *
     * @return
     */
    List<SysCodeMain> codeMainItem();
}
