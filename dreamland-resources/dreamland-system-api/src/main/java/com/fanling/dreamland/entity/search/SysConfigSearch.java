package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统配置表信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysConfigSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
    // 配置描述
    private String config_name;
}