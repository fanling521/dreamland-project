package com.fanling.dreamland.entitys.moudle;

import com.fanling.dreamland.entitys.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRole extends BaseEntity {
    private String role_id;
    private String role_key;
    private String role_name;
}
