package com.fanling.dreamland.auth.feign.fallback;

import com.fanling.dreamland.auth.feign.UserSaleFeign;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.web.BaseController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/fallback/manager/application")
public class UserSaleFallBack extends BaseController implements UserSaleFeign {
    @Override
    public R selectById(String appId) {
        return error();
    }
}
