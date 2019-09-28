package com.fanling.dreamland.auth.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.auth.entity.AppManager;
import com.fanling.dreamland.auth.search.AppManagerSearch;
import com.fanling.dreamland.auth.service.IAppManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理人员表")
@RestController
@RequestMapping("/manage/app/manager")
public class MaAppManagerController extends BaseController {

    @Autowired
    private IAppManagerService appManagerService;

    @ApiOperation(value = "管理人员表分页查询", notes = "根据条件分页查看管理人员表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppManagerSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody AppManagerSearch search) {
        startPage(search);
        AppManager appManager = new AppManager();
        List<AppManager> list = appManagerService.list(appManager);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询管理人员表", notes = "根据id查询管理人员表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppManager appManager = appManagerService.selectById(id);
        if (appManager == null) {
            return error("数据查找失败！");
        }
        return R.success(appManager);
    }

    @ApiOperation(value = "新增管理人员表", notes = "根据必填项目新增管理人员表")
    @ApiImplicitParam(name = "appManager", required = true, value = "要新增的信息", dataType = "AppManager", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppManager appManager) {
        return toAjax(appManagerService.insert(appManager));
    }

    @ApiOperation(value = "修改管理人员表", notes = "修改管理人员表,id必填")
    @ApiImplicitParam(name = "appManager", required = true, value = "要编辑的信息", dataType = "AppManager", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppManager appManager) {
        return toAjax(appManagerService.update(appManager));
    }

    @ApiOperation(value = "根据id删除管理人员表查询", notes = "根据id删除管理人员表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appManagerService.delete(id));
    }

}
