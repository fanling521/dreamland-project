package com.fanling.dreamland.entitys.moudle;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户地址管理
 */
@Getter
@Setter
public class UserAddr {
    private String addr_id;
    private String user_id;
    private String name;
    private String phone;
    private String province;
    private String city_name;
    private String county_name;
    private String address;
    private String post_code;
}
