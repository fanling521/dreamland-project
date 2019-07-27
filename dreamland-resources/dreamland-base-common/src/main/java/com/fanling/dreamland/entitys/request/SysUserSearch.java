package com.fanling.dreamland.entitys.request;

import com.fanling.dreamland.entitys.BaseSearch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserSearch extends BaseSearch {
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 用户名称
    private String user_name;
    // 帐号状态（0正常 1停用）
    private String status;
}
