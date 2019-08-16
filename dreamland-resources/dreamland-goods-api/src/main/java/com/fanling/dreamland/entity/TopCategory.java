package com.fanling.dreamland.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 商品一级分类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TopCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //商品名称
    private String top_goods_name;
    //商品描述
    private String top_goods_desc;
    //商品分类状态
    private String top_goods_status;
    //商品图标
    private String top_goods_icon;
}
