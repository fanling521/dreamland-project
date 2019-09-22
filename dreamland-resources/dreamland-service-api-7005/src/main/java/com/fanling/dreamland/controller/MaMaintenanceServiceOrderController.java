package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.search.MaintenanceServiceOrderSearch;
import com.fanling.dreamland.entity.MaintenanceServiceOrder;
import com.fanling.dreamland.service.IMaintenanceServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "保养服务表")
@RestController
@RequestMapping("/manage/maintenance/service/order")
public class MaMaintenanceServiceOrderController extends BaseController {

    @Autowired
    private IMaintenanceServiceOrderService maintenanceServiceOrderService;

    @ApiOperation(value = "保养服务表分页查询", notes = "根据条件分页查看保养服务表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "MaintenanceServiceOrderSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody MaintenanceServiceOrderSearch search) {
        startPage(search);
        MaintenanceServiceOrder maintenanceServiceOrder = new MaintenanceServiceOrder();
        List<MaintenanceServiceOrder> list = maintenanceServiceOrderService.list(maintenanceServiceOrder);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询保养服务表", notes = "根据id查询保养服务表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        MaintenanceServiceOrder maintenanceServiceOrder = maintenanceServiceOrderService.selectById(id);
        if (maintenanceServiceOrder == null) {
            return error("数据查找失败！");
        }
        return R.success(maintenanceServiceOrder);
    }

    @ApiOperation(value = "新增保养服务表", notes = "根据必填项目新增保养服务表")
    @ApiImplicitParam(name = "maintenanceServiceOrder", required = true, value = "要新增的信息", dataType = "MaintenanceServiceOrder", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody MaintenanceServiceOrder maintenanceServiceOrder) {
        return toAjax(maintenanceServiceOrderService.insert(maintenanceServiceOrder));
    }

    @ApiOperation(value = "修改保养服务表", notes = "修改保养服务表,id必填")
    @ApiImplicitParam(name = "maintenanceServiceOrder", required = true, value = "要编辑的信息", dataType = "MaintenanceServiceOrder", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody MaintenanceServiceOrder maintenanceServiceOrder) {
        return toAjax(maintenanceServiceOrderService.update(maintenanceServiceOrder));
    }

    @ApiOperation(value = "根据id删除保养服务表查询", notes = "根据id删除保养服务表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(maintenanceServiceOrderService.delete(id));
    }

}
