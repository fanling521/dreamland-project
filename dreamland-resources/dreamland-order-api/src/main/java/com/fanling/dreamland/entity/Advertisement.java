package com.fanling.dreamland.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
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
    //宽度
    private String width;
    //高度
    private String height;
    //图片路径
    private String img_path;
    //广告位置
    private String position;
    //广告状态
    private String adv_status;
}
