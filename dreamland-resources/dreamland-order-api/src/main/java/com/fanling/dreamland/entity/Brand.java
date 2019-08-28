package com.fanling.dreamland.entity;

import com.fanling.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Brand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //品牌英文名称
    private String b_e_name;
    //品牌中文名称
    private String b_c_name;
    //品牌图标地址
    private String b_img_path;
    //品牌分类
    private String b_type;
    //品牌显隐
    private String b_display;
}
