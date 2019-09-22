package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.search.DesignServiceOrderSearch;
import com.fanling.dreamland.entity.DesignServiceOrder;
import com.fanling.dreamland.service.IDesignServiceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "设计服务表")
@RestController
@RequestMapping("/manage/design/service/order")
public class MaDesignServiceOrderController extends BaseController {

    @Autowired
    private IDesignServiceOrderService designServiceOrderService;

    @ApiOperation(value = "设计服务表分页查询", notes = "根据条件分页查看设计服务表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "DesignServiceOrderSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody DesignServiceOrderSearch search) {
        startPage(search);
        DesignServiceOrder designServiceOrder = new DesignServiceOrder();
        List<DesignServiceOrder> list = designServiceOrderService.list(designServiceOrder);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询设计服务表", notes = "根据id查询设计服务表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        DesignServiceOrder designServiceOrder = designServiceOrderService.selectById(id);
        if (designServiceOrder == null) {
            return error("数据查找失败！");
        }
        return R.success(designServiceOrder);
    }

    @ApiOperation(value = "新增设计服务表", notes = "根据必填项目新增设计服务表")
    @ApiImplicitParam(name = "designServiceOrder", required = true, value = "要新增的信息", dataType = "DesignServiceOrder", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody DesignServiceOrder designServiceOrder) {
        return toAjax(designServiceOrderService.insert(designServiceOrder));
    }

    @ApiOperation(value = "修改设计服务表", notes = "修改设计服务表,id必填")
    @ApiImplicitParam(name = "designServiceOrder", required = true, value = "要编辑的信息", dataType = "DesignServiceOrder", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody DesignServiceOrder designServiceOrder) {
        return toAjax(designServiceOrderService.update(designServiceOrder));
    }

    @ApiOperation(value = "根据id删除设计服务表查询", notes = "根据id删除设计服务表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(designServiceOrderService.delete(id));
    }

}
