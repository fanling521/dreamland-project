package com.fanling.dreamland.auth.feign;

import com.fanling.dreamland.auth.feign.fallback.UserSaleFallBack;
import com.fanling.dreamland.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "module-api", fallback = UserSaleFallBack.class)
@RequestMapping("/app/user/sale")
public interface UserSaleFeign {
    @PostMapping("/select/{id}")
    R selectById(@PathVariable("id") String id);
}
