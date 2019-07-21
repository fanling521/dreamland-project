package com.fanling.dreamland.entitys.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegForm {
    private String phone;
    private String captcha;
    private String reg_ip;
}
