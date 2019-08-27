package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.web.TableDataInfo;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.entity.search.OrderSearch;
import com.fanling.dreamland.entity.Order;
import com.fanling.dreamland.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "订单表")
@RestController
@RequestMapping("/manager/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "订单表分页查询", notes = "根据条件分页查看订单表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "OrderSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody OrderSearch search) {
        startPage(search);
        Order order = new Order();
        List<Order> list = orderService.list(order);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询订单表", notes = "根据id查询订单表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Order order = orderService.selectById(id);
        if (order == null) {
            return error("数据查找失败！");
        }
        return R.success(order);
    }

    @ApiOperation(value = "新增订单表", notes = "根据必填项目新增订单表")
    @ApiImplicitParam(name = "order", required = true, value = "要新增的信息", dataType = "Order", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Order order) {
        return toAjax(orderService.insert(order));
    }

    @ApiOperation(value = "修改订单表", notes = "修改订单表,id必填")
    @ApiImplicitParam(name = "order", required = true, value = "要编辑的信息", dataType = "Order", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Order order) {
        return toAjax(orderService.update(order));
    }

    @ApiOperation(value = "根据id删除订单表查询", notes = "根据id删除订单表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(orderService.delete(id));
    }

}
