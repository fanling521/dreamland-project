package com.fanling.dreamland.entitys.moudle.member;

import lombok.Getter;
import lombok.Setter;

/**
 * 专业维修用户表
 */
@Getter
@Setter
public class AppProUser extends BaseAppUser {
    private String real_name;
    private String id_card_no;
    private String real_status;
}
