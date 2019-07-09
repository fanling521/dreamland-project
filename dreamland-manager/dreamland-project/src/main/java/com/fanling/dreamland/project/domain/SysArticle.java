package com.fanling.dreamland.project.domain;

import com.fanling.dreamland.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysArticle extends BaseEntity {

    private String artId;
    private String artType;
    private String artTitle;
    private String artContent;
    private String artAuthor;
    private String artDate;
}
