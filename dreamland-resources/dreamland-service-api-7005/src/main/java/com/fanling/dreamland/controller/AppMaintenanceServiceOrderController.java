package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.entity.MaintenanceServiceOrder;
import com.fanling.dreamland.service.IMaintenanceServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "保养服务表")
@RestController
@RequestMapping("/app/maintenance/service/order")
public class AppMaintenanceServiceOrderController extends BaseController {

    @Autowired
    private IMaintenanceServiceOrderService maintenanceServiceOrderService;

    @ApiOperation(value = "新增保养服务表", notes = "根据必填项目新增保养服务表")
    @ApiImplicitParam(name = "maintenanceServiceOrder", required = true, value = "要新增的信息", dataType = "MaintenanceServiceOrder", paramType = "body")
    @PostMapping("/insert/reserve")
    public R insert(@RequestBody MaintenanceServiceOrder maintenanceServiceOrder) {
        return toAjax(maintenanceServiceOrderService.insert(maintenanceServiceOrder));
    }
}
