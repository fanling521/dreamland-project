package com.fanling.dreamland.client;

import com.fanling.common.R;
import com.fanling.dreamland.client.fallback.UserSaleFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-api", fallback = UserSaleFallBack.class)
@RequestMapping("/app/user/sale")
public interface UserSaleClient {
    @PostMapping("/select/{id}")
    R selectById(@PathVariable("id") String id);
}
