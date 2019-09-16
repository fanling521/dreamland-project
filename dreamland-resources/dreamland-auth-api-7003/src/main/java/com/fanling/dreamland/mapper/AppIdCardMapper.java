package com.fanling.dreamland.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.AppIdCard;
import org.apache.ibatis.annotations.Param;

/**
 * 用户身份证表的数据层
 */
public interface AppIdCardMapper extends BaseMapper<AppIdCard> {

    /**
     * 根据用用户标识查询实名信息
     *
     * @param userId
     * @return
     */
    AppIdCard selectByUid(@Param("user_id") String userId);
}
