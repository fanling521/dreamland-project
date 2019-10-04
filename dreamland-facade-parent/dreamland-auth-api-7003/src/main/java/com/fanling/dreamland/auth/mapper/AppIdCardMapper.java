package com.fanling.dreamland.auth.mapper;

import com.fanling.dreamland.auth.entity.AppIdCard;
import com.fanling.dreamland.common.mapper.BaseMapper;
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
