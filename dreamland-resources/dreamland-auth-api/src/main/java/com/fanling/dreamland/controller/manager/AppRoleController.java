package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.AppRole;
import com.fanling.dreamland.entity.search.AppRoleSearch;
import com.fanling.dreamland.service.IAppRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "APP角色表")
@RestController
@RequestMapping("/manager/app/role")
public class AppRoleController extends BaseController {

    @Autowired
    private IAppRoleService appRoleService;

    @ApiOperation(value = "APP角色表分页查询", notes = "根据条件分页查看APP角色表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppRoleSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody AppRoleSearch search) {
        startPage(search);
        AppRole appRole = new AppRole();
        List<AppRole> list = appRoleService.list(appRole);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询APP角色表", notes = "根据id查询APP角色表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppRole appRole = appRoleService.selectById(id);
        if (appRole == null) {
            return error("数据查找失败！");
        }
        return R.success(appRole);
    }

    @ApiOperation(value = "新增APP角色表", notes = "根据必填项目新增APP角色表")
    @ApiImplicitParam(name = "appRole", required = true, value = "要新增的信息", dataType = "AppRole", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppRole appRole) {
        return toAjax(appRoleService.insert(appRole));
    }

    @ApiOperation(value = "修改APP角色表", notes = "修改APP角色表,id必填")
    @ApiImplicitParam(name = "appRole", required = true, value = "要编辑的信息", dataType = "AppRole", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppRole appRole) {
        return toAjax(appRoleService.update(appRole));
    }

    @ApiOperation(value = "根据id删除APP角色表查询", notes = "根据id删除APP角色表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appRoleService.delete(id));
    }

}
