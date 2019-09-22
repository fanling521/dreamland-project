package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.search.ServiceItemsSearch;
import com.fanling.dreamland.entity.ServiceItems;
import com.fanling.dreamland.service.IServiceItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "维修设计保养条目表")
@RestController
@RequestMapping("/manage/service/items")
public class MaServiceItemsController extends BaseController {

    @Autowired
    private IServiceItemsService serviceItemsService;

    @ApiOperation(value = "维修设计保养条目表分页查询", notes = "根据条件分页查看维修设计保养条目表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "ServiceItemsSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceItemsSearch search) {
        startPage(search);
        ServiceItems serviceItems = new ServiceItems();
        serviceItems.setItem_name(search.getItem_name());
        serviceItems.setTop_name(search.getTop_name());
        List<ServiceItems> list = serviceItemsService.list(serviceItems);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询维修设计保养条目表", notes = "根据id查询维修设计保养条目表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        ServiceItems serviceItems = serviceItemsService.selectById(id);
        if (serviceItems == null) {
            return error("数据查找失败！");
        }
        return R.success(serviceItems);
    }

    @ApiOperation(value = "新增维修设计保养条目表", notes = "根据必填项目新增维修设计保养条目表")
    @ApiImplicitParam(name = "serviceItems", required = true, value = "要新增的信息", dataType = "ServiceItems", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody ServiceItems serviceItems) {
        return toAjax(serviceItemsService.insert(serviceItems));
    }

    @ApiOperation(value = "修改维修设计保养条目表", notes = "修改维修设计保养条目表,id必填")
    @ApiImplicitParam(name = "serviceItems", required = true, value = "要编辑的信息", dataType = "ServiceItems", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody ServiceItems serviceItems) {
        return toAjax(serviceItemsService.update(serviceItems));
    }

    @ApiOperation(value = "根据id删除维修设计保养条目表查询", notes = "根据id删除维修设计保养条目表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(serviceItemsService.delete(id));
    }

}
