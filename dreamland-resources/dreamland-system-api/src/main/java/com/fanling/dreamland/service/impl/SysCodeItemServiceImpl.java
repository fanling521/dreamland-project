package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.ServiceImpl;
import com.fanling.dreamland.config.CodeMainCache;
import com.fanling.dreamland.entity.SysCodeItem;
import com.fanling.dreamland.entity.SysCodeMain;
import com.fanling.dreamland.mapper.SysCodeItemMapper;
import com.fanling.dreamland.service.ISysCodeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 系统代码明细表的业务实现层
 */
@Service
public class SysCodeItemServiceImpl extends ServiceImpl<SysCodeItemMapper, SysCodeItem> implements ISysCodeItemService {

    @Autowired
    private SysCodeItemMapper sysCodeItemMapper;

    /**
     * 新增系统代码明细表的信息
     *
     * @param sysCodeItem
     * @return
     */
    @Override
    public int insert(SysCodeItem sysCodeItem) {
        sysCodeItem.setCode_item_id(UUID.randomUUID().toString());
        int row = sysCodeItemMapper.insert(sysCodeItem);
        //重建缓存
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 修改系统代码明细表的信息
     *
     * @param sysCodeItem
     * @return
     */
    @Override
    public int update(SysCodeItem sysCodeItem) {
        int row = sysCodeItemMapper.update(sysCodeItem);
        //重建缓存
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 根据id删除系统代码明细表的信息
     *
     * @param codeItemId
     * @return
     */
    @Override
    public int delete(String codeItemId) {
        //重建缓存
        int row = sysCodeItemMapper.delete(codeItemId);
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 获取所有的代码项-缓存专用
     *
     * @return
     */
    @Override
    public List<SysCodeMain> codeMainItem() {
        return sysCodeItemMapper.codeMainItem();
    }
}
