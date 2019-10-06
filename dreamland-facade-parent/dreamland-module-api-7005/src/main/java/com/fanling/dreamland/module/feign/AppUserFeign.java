package com.fanling.dreamland.module.feign;

import com.fanling.dreamland.module.feign.entity.AppUserResp;
import com.fanling.dreamland.module.feign.fallback.AppUserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "auth-api", fallback = AppUserFallBack.class)
@RequestMapping("/manage/app/user")
public interface AppUserFeign {

    @GetMapping("/select/{id}")
    AppUserResp selectById(@PathVariable("id") String id);
}
