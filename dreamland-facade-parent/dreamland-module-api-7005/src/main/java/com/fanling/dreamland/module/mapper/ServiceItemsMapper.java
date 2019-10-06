package com.fanling.dreamland.module.mapper;

import com.fanling.dreamland.common.mapper.BaseMapper;
import com.fanling.dreamland.module.entity.ServiceItems;
import org.apache.ibatis.annotations.Param;

/**
 * 维修设计保养条目表的数据层
 */
public interface ServiceItemsMapper extends BaseMapper<ServiceItems> {

    /**
     * 根据商品号查询
     * @param subNo
     * @return
     */
    ServiceItems selectBySubNo(@Param("sub_no") String subNo);
}
