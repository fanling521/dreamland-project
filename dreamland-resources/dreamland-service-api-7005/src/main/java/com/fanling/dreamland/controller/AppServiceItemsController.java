package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.dreamland.entity.ServiceItems;
import com.fanling.dreamland.service.IServiceItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "APP获取商品信息")
@RestController
@RequestMapping("/app/service/items")
public class AppServiceItemsController {
    @Autowired
    private IServiceItemsService serviceItemsService;

    @ApiOperation(value = "获取全部商品信息", notes = "获取全部商品信息")
    @PostMapping("/get_items")
    public R getItems() {
        List<ServiceItems> list = serviceItemsService.list(null);
        return null;
    }
}
