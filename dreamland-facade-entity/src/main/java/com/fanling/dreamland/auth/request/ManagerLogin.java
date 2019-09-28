package com.fanling.dreamland.auth.request;

import lombok.Data;

@Data
public class ManagerLogin {
    private String account;
    private String password;
}
