package com.fanling.dreamland.service.impl;

import com.fanling.common.service.ServiceImpl;
import com.fanling.dreamland.config.CodeMainCache;
import com.fanling.dreamland.entity.SysCodeType;
import com.fanling.dreamland.mapper.SysCodeTypeMapper;
import com.fanling.dreamland.service.ISysCodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统代码类别表的业务实现层
 */
@Service
public class SysCodeTypeServiceImpl extends ServiceImpl<SysCodeTypeMapper, SysCodeType> implements ISysCodeTypeService {

    @Autowired
    private SysCodeTypeMapper sysCodeTypeMapper;

    /**
     * 新增系统代码类别表的信息
     *
     * @param sysCodeType
     * @return
     */
    @Override
    public int insert(SysCodeType sysCodeType) {
        sysCodeType.setCode_type_id(UUID.randomUUID().toString());
        //重建缓存
        int row = sysCodeTypeMapper.insert(sysCodeType);
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 修改系统代码类别表的信息
     *
     * @param sysCodeType
     * @return
     */
    @Override
    public int update(SysCodeType sysCodeType) {
        //重建缓存
        int row = sysCodeTypeMapper.update(sysCodeType);
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 根据id删除系统代码类别表的信息
     *
     * @param codeTypeId
     * @return
     */
    @Override
    public int delete(String codeTypeId) {
        //重建缓存
        int row = sysCodeTypeMapper.delete(codeTypeId);
        new CodeMainCache().rebuild();
        return row;
    }

    /**
     * 获取代码类型的树形数据
     *
     * @return
     */
    @Override
    public List<Map<String, String>> tree() {
        return sysCodeTypeMapper.tree();
    }
}
