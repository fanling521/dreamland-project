package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.R;
import com.fanling.dreamland.auth.annotations.UseJwtToken;
import com.fanling.dreamland.entitys.request.SysRoleSearch;
import com.fanling.dreamland.entitys.system.SysRole;
import com.fanling.dreamland.service.ISysRoleService;
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

@Api(tags = "管理员-角色管理")
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation(value = "获取角色列表", notes = "获取全部角色的信息列表，可查询，可分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数目", dataType = "Integer", defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Integer", defaultValue = "1", paramType = "query")
    })
    @UseJwtToken
    @PostMapping("/list")
    public TableDataInfo list(SysRoleSearch sysRoleSearch) {
        startPage();
        SysRole sysRole = new SysRole();
        sysRole.setRole_key(sysRoleSearch.getRole_key());
        sysRole.setRole_name(sysRoleSearch.getRole_name());
        List<SysRole> list = sysRoleService.selectRoleList(sysRole);
        return getDataTable(list);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色信息", code = 0)
    @ApiImplicitParam(name = "sysRole", value = "角色信息", dataType = "SysRole", paramType = "body")
    @UseJwtToken
    @PostMapping("/add")
    public R addSave(@RequestBody SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getRole_name())) {
            return error("角色名称不能为空！");
        }
        if (StringUtils.isEmpty(sysRole.getRole_key())) {
            return error("角色值不能为空!");
        }
        if (sysRoleService.checkRoleKeyUnique(sysRole.getRole_key())) {
            return error("角色值已经存在！");
        }
        if (sysRoleService.checkRoleNameUnique(sysRole.getRole_name())) {
            return error("角色名称已经存在！");
        }
        return toAjax(sysRoleService.insertRole(sysRole));
    }

    @ApiOperation(value = "修改角色", notes = "修改角色信息", code = 0)
    @ApiImplicitParam(name = "sysRole", value = "角色信息", dataType = "SysRole", paramType = "body")
    @UseJwtToken
    @PostMapping("/edit")
    public R editSave(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.updateRole(sysRole));
    }

    @ApiOperation(value = "获取角色信息", notes = "根据角色id获取角色信息", code = 0)
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/get/{roleId}")
    public R getUser(@PathVariable("roleId") String roleId) {
        return R.success(sysRoleService.selectRoleById(roleId));
    }

    @ApiOperation(value = "删除角色信息", notes = "根据角色id删除角色信息", code = 0)
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query")
    @UseJwtToken
    @PostMapping("/remove")
    public R remove(String roleId) {
        if ("d92a9960-dc74-4a55-b3bd-46ec909205e0".equals(roleId)) {
            return error("默认角色不可删除");
        }
        //查看是否分配了用户信息
        if(sysRoleService.checkUserCount(roleId)){
            return  error("角色已经分配了用户，无法删除！");
        }
        return toAjax(sysRoleService.deleteRoleById(roleId));
    }

    @ApiOperation(value = "获取所有角色列表", notes = "获取所有角色列表", code = 0)
    @UseJwtToken
    @PostMapping("/select_all_role")
    public R selectAllRole() {
        return R.success(sysRoleService.selectRoleAll());
    }

    @ApiOperation(value = "根据用户id查询角色信息", notes = "根据用户id查询角色信息", code = 0)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/select_roles/{userId}")
    public R selectRoleByUserId(@PathVariable String userId) {
        return R.success(sysRoleService.selectRolesByUserId(userId));
    }
}