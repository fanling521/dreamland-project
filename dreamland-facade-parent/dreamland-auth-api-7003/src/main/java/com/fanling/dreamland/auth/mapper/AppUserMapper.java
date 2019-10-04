package com.fanling.dreamland.auth.mapper;

import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * APP用户表的数据层
 */
public interface AppUserMapper extends BaseMapper<AppUser> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @return
     */
    AppUser selectByLogin(@Param("user_phone") String phone);
}
