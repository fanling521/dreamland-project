package com.fanling.dreamland.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //作者
    private String author;
    //来源
    private String source;
    //图片1
    private String img_1_path;
    //图片2
    private String img_2_path;
    //图片3
    private String ing_3_path;
    //文章正文（富文本）
    private String content;
    //标题
    private String title;
}
