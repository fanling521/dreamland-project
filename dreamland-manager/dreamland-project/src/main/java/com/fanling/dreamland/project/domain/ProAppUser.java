package com.fanling.dreamland.project.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 维修人员信息
 */
@Getter
@Setter
public class ProAppUser extends BaseUser {

    private String realStatus;
    private String idCardNo;
}
