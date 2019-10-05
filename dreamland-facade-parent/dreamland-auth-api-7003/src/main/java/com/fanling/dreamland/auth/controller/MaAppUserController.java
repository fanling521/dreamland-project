package com.fanling.dreamland.auth.controller;

import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.auth.service.IAppUserService;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "APP用户表")
@RestController
@RequestMapping("/manage/app/user")
public class MaAppUserController extends BaseController {

    @Autowired
    private IAppUserService appUserService;

    @ApiOperation(value = "APP用户表分页查询", notes = "根据条件分页查看APP用户表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppUserSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<AppUser> search) {
        startPage(search);
        AppUser appUser = new AppUser();
        List<AppUser> list = appUserService.list(appUser);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询APP用户表", notes = "根据id查询APP用户表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @GetMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppUser appUser = appUserService.selectById(id);
        if (appUser == null) {
            return error("数据查找失败！");
        }
        return R.success(appUser);
    }

    @Ignore
    @ApiOperation(value = "新增APP用户表", notes = "根据必填项目新增APP用户表")
    @ApiImplicitParam(name = "appUser", required = true, value = "要新增的信息", dataType = "AppUser", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppUser appUser) {
        return toAjax(appUserService.insert(appUser));
    }

    @Ignore
    @ApiOperation(value = "修改APP用户表", notes = "修改APP用户表,id必填")
    @ApiImplicitParam(name = "appUser", required = true, value = "要编辑的信息", dataType = "AppUser", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppUser appUser) {
        return toAjax(appUserService.update(appUser));
    }

    @Ignore
    @ApiOperation(value = "根据id删除APP用户表查询", notes = "根据id删除APP用户表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appUserService.delete(id));
    }

}
