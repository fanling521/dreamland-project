package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.common.annotations.UseJwtToken;
import com.fanling.dreamland.config.SystemEnum;
import com.fanling.dreamland.entity.SysUser;
import com.fanling.dreamland.entity.search.SysUserSearch;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数目", dataType = "Integer", defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Integer", defaultValue = "1", paramType = "query")
    })
    @UseJwtToken
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysUserSearch search) {
        startPage(search);
        SysUser sysUser = new SysUser();
        sysUser.setUser_name(search.getUser_name());
        sysUser.setPhone(search.getPhone());
        sysUser.setEmail(search.getEmail());
        sysUser.setStatus(search.getStatus());
        List<SysUser> list = sysUserService.list(sysUser);
        return getDataTable(list);
    }

    @ApiOperation(value = "新增用户", notes = "管理员填写相关信息，新增管理员信息", code = 0)
    @ApiImplicitParam(name = "sysUser", value = "系统用户信息", dataType = "SysUser", paramType = "body")
    @UseJwtToken
    @PostMapping("/add")
    public R addSave(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getPhone())) {
            return error("手机号必填");
        }
        if (StringUtils.isEmpty(sysUser.getEmail())) {
            return error("邮箱地址必填");
        }
        if (sysUserService.checkPhoneUnique(sysUser.getPhone())) {
            return error("手机号已经被注册了！");
        }
        if (sysUserService.checkEmailUnique(sysUser.getEmail())) {
            return error("邮箱地址已经被注册了！");
        }
        return toAjax(sysUserService.insert(sysUser));
    }

    @ApiOperation(value = "修改用户信息", notes = "管理员修改管理员的信息", code = 0)
    @ApiImplicitParam(name = "sysUserReq", value = "系统用户信息", dataType = "SysUserReq", paramType = "body")
    @UseJwtToken
    @PostMapping("/edit")
    public R editSave(@RequestBody SysUser sysUser) {
        if (SystemEnum.SYSTEM_USER.getCode().equals(sysUser.getUser_id())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(sysUserService.update(sysUser));
    }

    @ApiOperation(value = "获取用户的信息", notes = "根据用户id获取用户的信息", code = 0)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/get/{userId}")
    public R getUser(@PathVariable("userId") String userId) {
        return R.success(sysUserService.selectById(userId));
    }

    @ApiOperation(value = "删除用户信息", notes = "根据用户id删除用户信息", code = 0)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query")
    @UseJwtToken
    @DeleteMapping("/remove/{userId}")
    public R remove(@PathVariable("userId") String userId) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(userId)) {
            return error("不允许删除超级管理员用户");
        }
        return toAjax(sysUserService.delete(userId));
    }
}
