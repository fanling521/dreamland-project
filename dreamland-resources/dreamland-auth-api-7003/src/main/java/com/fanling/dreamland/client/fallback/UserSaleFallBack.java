package com.fanling.dreamland.client.fallback;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.client.UserSaleClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/fallback/manager/application")
public class UserSaleFallBack extends BaseController implements UserSaleClient {
    @Override
    public R selectById(String appId) {
        return error();
    }
}
