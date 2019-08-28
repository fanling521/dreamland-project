package com.fanling.dreamland.entity.search;

import com.fanling.common.entity.BaseSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserSearch extends BaseSearch {
    private static final long serialVersionUID = -7638763824119143064L;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 用户名称
    private String user_name;
    // 帐号状态（0正常 1停用）
    private String status;
}
