package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.module.entity.Advertisement;
import com.fanling.dreamland.module.service.IAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP营销广告表")
@RestController
@RequestMapping("/app/marketing")
public class AppAdvertisementController extends BaseController {
    @Autowired
    private IAdvertisementService advertisementService;

    @ApiOperation(value = "获取指定位置的营销广告", notes = "获取指定位置的营销广告")
    @ApiImplicitParam(name = "position", required = true, value = "广告位置", dataType = "String", paramType = "path")
    @PostMapping("/select/{position}")
    public R selectByPosition(@PathVariable("position") String position) {
        if (StringUtils.isEmpty(position)) {
            return error("参数\"position\"不能为空！");
        }
        Advertisement bean = new Advertisement();
        bean.setPosition(position);
        return getDatalist(advertisementService.list(bean));
    }
}
