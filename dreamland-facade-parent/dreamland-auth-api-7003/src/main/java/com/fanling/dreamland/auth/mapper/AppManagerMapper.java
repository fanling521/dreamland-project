package com.fanling.dreamland.auth.mapper;

import com.fanling.common.mapper.BaseMapper;
import com.fanling.dreamland.auth.entity.AppManager;
import org.apache.ibatis.annotations.Param;

/**
 * 管理人员表的数据层
 */
public interface AppManagerMapper extends BaseMapper<AppManager> {
    /**
     * 登录信息获取
     *
     * @param phone
     * @param password
     * @return
     */
    AppManager selectByLogin(@Param("phone") String phone, @Param("password") String password);
}
