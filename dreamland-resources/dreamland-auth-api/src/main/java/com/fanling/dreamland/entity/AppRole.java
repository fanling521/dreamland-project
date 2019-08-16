package com.fanling.dreamland.entity;

import com.fanling.dreamland.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP角色表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //角色名称
    private String role_name;
    //角色描述
    private String role_desc;
}
