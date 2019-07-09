package com.fanling.dreamland.project.domain;

import com.fanling.dreamland.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户基础表信息
 */
@Getter
@Setter
public class BaseUser extends BaseEntity {
    //用户标识
    private String userId;
    private String userName;
    private String phone;
    private String sex;
    private String birth;
    private String realStatus;
    private String password;
    private String salt;
}
