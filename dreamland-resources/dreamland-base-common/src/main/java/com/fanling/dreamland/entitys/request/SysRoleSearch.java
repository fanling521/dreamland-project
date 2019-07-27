package com.fanling.dreamland.entitys.request;

import com.fanling.dreamland.entitys.BaseSearch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoleSearch extends BaseSearch {
    private String role_name;
    private String role_key;
}
