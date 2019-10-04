package com.fanling.dreamland.auth.controller;

import com.fanling.dreamland.auth.entity.AppStaff;
import com.fanling.dreamland.auth.service.IAppStaffService;
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

@Api(tags = "维修人员表")
@RestController
@RequestMapping("/manage/app/staff")
public class MaAppStaffController extends BaseController {

    @Autowired
    private IAppStaffService appStaffService;

    @ApiOperation(value = "维修人员表分页查询", notes = "根据条件分页查看维修人员表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppStaffSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<AppStaff> search) {
        startPage(search);
        AppStaff appStaff = new AppStaff();
        List<AppStaff> list = appStaffService.list(appStaff);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询维修人员表", notes = "根据id查询维修人员表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppStaff appStaff = appStaffService.selectById(id);
        if (appStaff == null) {
            return error("数据查找失败！");
        }
        return R.success(appStaff);
    }

    @ApiOperation(value = "新增维修人员表", notes = "根据必填项目新增维修人员表")
    @ApiImplicitParam(name = "appStaff", required = true, value = "要新增的信息", dataType = "AppStaff", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppStaff appStaff) {
        return toAjax(appStaffService.insert(appStaff));
    }

    @ApiOperation(value = "修改维修人员表", notes = "修改维修人员表,id必填")
    @ApiImplicitParam(name = "appStaff", required = true, value = "要编辑的信息", dataType = "AppStaff", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppStaff appStaff) {
        return toAjax(appStaffService.update(appStaff));
    }

    @ApiOperation(value = "根据id删除维修人员表查询", notes = "根据id删除维修人员表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appStaffService.delete(id));
    }

}
