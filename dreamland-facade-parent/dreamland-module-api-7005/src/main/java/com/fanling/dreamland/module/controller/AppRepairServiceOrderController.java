package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.module.entity.RepairServiceOrder;
import com.fanling.dreamland.module.service.IRepairServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP维修服务表")
@RestController
@RequestMapping("/app/repair/service/order")
public class AppRepairServiceOrderController extends BaseController {
    @Autowired
    private IRepairServiceOrderService repairServiceOrderService;

    @ApiOperation(value = "APP端预定", notes = "通过选择时间，预定维修")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/reserve")
    public R insertReserve(@RequestBody RepairServiceOrder repairServiceOrder) {
        //TODO 预约时间的逻辑判断
        if(!"1".equals(repairServiceOrder.getRepair_type())){
            return error("维修类型选择错误！");
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    @ApiOperation(value = "APP端马上维修", notes = "通过选择时间，APP端马上维修")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/current")
    public R insertCurrent(@RequestBody RepairServiceOrder repairServiceOrder) {
        if(!"2".equals(repairServiceOrder.getRepair_type())){
            return error("维修类型选择错误！");
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    @ApiOperation(value = "APP端申请救援设备", notes = "APP端申请救援设备")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/help")
    public R insertHelp(@RequestBody RepairServiceOrder repairServiceOrder) {
        if(!"3".equals(repairServiceOrder.getRepair_type())){
            return error("维修类型选择错误！");
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }
}
