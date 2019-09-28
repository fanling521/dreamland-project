package com.fanling.dreamland.module.entity;

import com.fanling.common.entity.BaseEntity;
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
    //图片
    private String img_path;
    //文章正文（富文本）
    private String content;
    //标题
    private String title;
}
