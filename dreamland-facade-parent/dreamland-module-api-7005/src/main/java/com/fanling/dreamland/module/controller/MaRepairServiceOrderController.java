package com.fanling.dreamland.module.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.module.entity.RepairServiceOrder;
import com.fanling.dreamland.module.search.RepairServiceOrderSearch;
import com.fanling.dreamland.module.service.IRepairServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "维修服务表")
@RestController
@RequestMapping("/manage/repair/service/order")
public class MaRepairServiceOrderController extends BaseController {

    @Autowired
    private IRepairServiceOrderService repairServiceOrderService;

    @ApiOperation(value = "维修服务表分页查询", notes = "根据条件分页查看维修服务表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "RepairServiceOrderSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody RepairServiceOrderSearch search) {
        startPage(search);
        RepairServiceOrder repairServiceOrder = new RepairServiceOrder();
        List<RepairServiceOrder> list = repairServiceOrderService.list(repairServiceOrder);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询维修服务表", notes = "根据id查询维修服务表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        RepairServiceOrder repairServiceOrder = repairServiceOrderService.selectById(id);
        if (repairServiceOrder == null) {
            return error("数据查找失败！");
        }
        return R.success(repairServiceOrder);
    }

    @ApiOperation(value = "新增维修服务表", notes = "根据必填项目新增维修服务表")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody RepairServiceOrder repairServiceOrder) {
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    @ApiOperation(value = "修改维修服务表", notes = "修改维修服务表,id必填")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要编辑的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody RepairServiceOrder repairServiceOrder) {
        return toAjax(repairServiceOrderService.update(repairServiceOrder));
    }

    @ApiOperation(value = "根据id删除维修服务表查询", notes = "根据id删除维修服务表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(repairServiceOrderService.delete(id));
    }

}
