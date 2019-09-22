package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 营销广告表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Advertisement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    // 标题
    private String title;
    //广告地址
    private String url;
    //图片路径
    private String img_path;
    //广告位置
    private String position;
    //广告状态
    private String adv_status;
}
