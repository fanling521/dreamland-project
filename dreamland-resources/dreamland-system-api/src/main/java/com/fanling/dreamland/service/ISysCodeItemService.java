package com.fanling.dreamland.service;


import com.fanling.common.service.IService;
import com.fanling.dreamland.entity.SysCodeItem;
import com.fanling.dreamland.entity.SysCodeMain;

import java.util.List;

/**
 * 系统代码明细表的业务层
 */
public interface ISysCodeItemService extends IService<SysCodeItem> {
    /**
     * 获取所有的代码项-缓存
     *
     * @return
     */
    List<SysCodeMain> codeMainItem();
}
