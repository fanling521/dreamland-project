package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.auth.annotations.UseJwtToken;
import com.fanling.dreamland.entitys.request.SysUserReq;
import com.fanling.dreamland.entitys.request.SysUserSearch;
import com.fanling.dreamland.entitys.system.SysUser;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理员-用户管理")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;


    @ApiOperation(value = "获取用户列表", notes = "获取全部管理员用户的信息列表，可查询，可分页")
    @ApiImplicitParam(name = "search", value = "搜索", dataType = "SysUserSearch", paramType = "body")
    @UseJwtToken
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysUserSearch search) {
        startPage(search);
        SysUser sysUser = new SysUser();
        sysUser.setEmail(search.getEmail());
        sysUser.setPhone(search.getPhone());
        sysUser.setStatus(search.getStatus());
        sysUser.setUser_name(search.getUser_name());
        List<SysUser> list = sysUserService.selectUserList(sysUser);
        return getDataTable(list);
    }

    @ApiOperation(value = "新增用户", notes = "管理员填写相关信息，新增管理员信息", code = 0)
    @ApiImplicitParam(name = "sysUserReq", value = "系统用户信息", dataType = "SysUserReq", paramType = "body")
    @UseJwtToken
    @PostMapping("/add")
    public R addSave(@RequestBody SysUserReq sysUserReq) {
        return toAjax(sysUserService.insertUser(sysUserReq));
    }

    @ApiOperation(value = "修改用户信息", notes = "管理员修改管理员的信息", code = 0)
    @ApiImplicitParam(name = "sysUserReq", value = "系统用户信息", dataType = "SysUserReq", paramType = "body")
    @UseJwtToken
    @PostMapping("/edit")
    public R editSave(@RequestBody SysUserReq sysUserReq) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(sysUserReq.getUser_id())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(sysUserService.updateUser(sysUserReq));
    }

    @ApiOperation(value = "获取用户的信息", notes = "根据用户id获取用户的信息", code = 0)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/get/{userId}")
    public R getUser(@PathVariable("userId") String userId) {
        return R.success(sysUserService.selectUserById(userId));
    }

    @ApiOperation(value = "删除用户信息", notes = "根据用户id删除用户信息", code = 0)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "path")
    @UseJwtToken
    @DeleteMapping("/remove/{userId}")
    public R remove(@PathVariable("userId") String userId) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(userId)) {
            return error("不允许删除超级管理员用户");
        }
        return toAjax(sysUserService.deleteUserById(userId));
    }
}
