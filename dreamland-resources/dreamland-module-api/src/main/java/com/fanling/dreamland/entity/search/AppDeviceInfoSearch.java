package com.fanling.dreamland.entity.search;

import com.fanling.dreamland.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户移动设备信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppDeviceInfoSearch extends BaseSearch {
    private static final long serialVersionUID = 1L;
}