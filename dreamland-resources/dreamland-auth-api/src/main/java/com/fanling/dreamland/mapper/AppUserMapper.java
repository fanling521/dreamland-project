package com.fanling.dreamland.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.AppUser;
import org.apache.ibatis.annotations.Param;

/**
 * APP用户表的数据层
 */
public interface AppUserMapper extends BaseMapper<AppUser> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @param key
     * @return
     */
    AppUser selectByLogin(@Param("user_phone") String phone, @Param("role_key") String key);
}
