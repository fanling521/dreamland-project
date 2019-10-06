package com.fanling.dreamland.module.feign.fallback;

import com.fanling.dreamland.module.feign.AppUserFeign;
import com.fanling.dreamland.module.feign.entity.AppUserResp;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/manage/app/user/fallback")
public class AppUserFallBack implements AppUserFeign {
    @Override
    public AppUserResp selectById(String id) {
        AppUserResp appUserResp = new AppUserResp();
        appUserResp.setCode(300);
        appUserResp.setMsg("远程调用失败!");
        return appUserResp;
    }
}
