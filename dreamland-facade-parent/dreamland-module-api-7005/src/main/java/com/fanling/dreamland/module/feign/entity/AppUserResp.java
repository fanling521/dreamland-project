package com.fanling.dreamland.module.feign.entity;

import com.fanling.dreamland.auth.entity.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AppUserResp {
    private int code;
    private String msg;
    private Data data;

    @EqualsAndHashCode(callSuper = true)
    @lombok.Data
    public static class Data extends AppUser{

    }
}
