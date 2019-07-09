package com.fanling.dreamland.project.domain;

import com.fanling.dreamland.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvManager extends BaseEntity {
    private String advId;
    private String advWidth;
    private String advHeight;
    private String advPrice;
    private String advPosition;
}
