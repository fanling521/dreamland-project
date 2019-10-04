package com.fanling.dreamland.auth.controller;

import com.fanling.dreamland.auth.entity.AppDeviceInfo;
import com.fanling.dreamland.auth.service.IAppDeviceInfoService;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户移动设备信息表")
@RestController
@RequestMapping("/manage/app/device")
public class MaAppDeviceInfoController extends BaseController {

    @Autowired
    private IAppDeviceInfoService appDeviceInfoService;

    @ApiOperation(value = "用户移动设备信息表分页查询", notes = "根据条件分页查看用户移动设备信息表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppDeviceInfoSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<AppDeviceInfo> search) {
        startPage(search);
        AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
        List<AppDeviceInfo> list = appDeviceInfoService.list(appDeviceInfo);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询用户移动设备信息表", notes = "根据id查询用户移动设备信息表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppDeviceInfo appDeviceInfo = appDeviceInfoService.selectById(id);
        if (appDeviceInfo == null) {
            return error("数据查找失败！");
        }
        return R.success(appDeviceInfo);
    }

    @ApiOperation(value = "新增用户移动设备信息表", notes = "根据必填项目新增用户移动设备信息表")
    @ApiImplicitParam(name = "appDeviceInfo", required = true, value = "要新增的信息", dataType = "AppDeviceInfo", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppDeviceInfo appDeviceInfo) {
        return toAjax(appDeviceInfoService.insert(appDeviceInfo));
    }

    @ApiOperation(value = "修改用户移动设备信息表", notes = "修改用户移动设备信息表,id必填")
    @ApiImplicitParam(name = "appDeviceInfo", required = true, value = "要编辑的信息", dataType = "AppDeviceInfo", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppDeviceInfo appDeviceInfo) {
        return toAjax(appDeviceInfoService.update(appDeviceInfo));
    }

    @ApiOperation(value = "根据id删除用户移动设备信息表查询", notes = "根据id删除用户移动设备信息表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appDeviceInfoService.delete(id));
    }

}
