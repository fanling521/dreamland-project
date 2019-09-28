package com.fanling.dreamland.module.controller;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.module.entity.DesignServiceOrder;
import com.fanling.dreamland.module.service.IDesignServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "App端设计服务表")
@RestController
@RequestMapping("/app/design/service/order")
public class AppDesignServiceOrderController extends BaseController {
    @Autowired
    private IDesignServiceOrderService designServiceOrderService;

    @ApiOperation(value = "新增设计服务表", notes = "根据必填项目新增设计服务表")
    @ApiImplicitParam(name = "designServiceOrder", required = true, value = "要新增的信息", dataType = "DesignServiceOrder", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody DesignServiceOrder designServiceOrder) {
        return toAjax(designServiceOrderService.insert(designServiceOrder));
    }
}
