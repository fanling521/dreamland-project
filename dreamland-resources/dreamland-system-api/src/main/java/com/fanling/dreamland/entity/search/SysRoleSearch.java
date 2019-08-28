package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleSearch extends BaseSearch {
    private static final long serialVersionUID = 8456737801778247892L;
    private String role_name;
    private String role_key;
}
