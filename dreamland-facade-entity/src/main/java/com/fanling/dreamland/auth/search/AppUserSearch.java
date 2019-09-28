package com.fanling.dreamland.auth.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP用户表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppUserSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 用户名
    private String user_name;
    // 手机号
    private String user_phone;
}