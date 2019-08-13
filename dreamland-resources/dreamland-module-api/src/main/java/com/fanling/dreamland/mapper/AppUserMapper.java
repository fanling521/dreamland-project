package com.fanling.dreamland.mapper;

import com.fanling.dreamland.common.mapper.BaseMapper;
import com.fanling.dreamland.entity.AppUser;
import org.apache.ibatis.annotations.Param;

/**
 * APP用户表的数据层
 */
public interface AppUserMapper extends BaseMapper<AppUser> {

    /**
     * 检查用户是否重复注册
     *
     * @param phone 手机号
     * @param key   角色（用户类型）
     * @return
     */
    boolean checkUserExists(@Param("user_phone") String phone, @Param("role_key") String key);
}
