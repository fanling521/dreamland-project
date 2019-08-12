package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP角色表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppRoleSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 角色名称
    private String role_name;
}