package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.entity.SysConfig;
import com.fanling.dreamland.entity.search.SysConfigSearch;
import com.fanling.dreamland.service.ISysConfigService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统配置表信息")
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfigService sysConfigService;

    @ApiOperation(value = "系统配置表信息分页查询", notes = "根据条件分页查看系统配置表信息")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "SysConfigSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysConfigSearch search) {
        startPage(search);
        SysConfig sysConfig = new SysConfig();
        List<SysConfig> list = sysConfigService.list(sysConfig);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询系统配置表信息", notes = "根据id查询系统配置表信息")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        SysConfig sysConfig = sysConfigService.selectById(id);
        if (sysConfig == null) {
            return error("数据查找失败！");
        }
        return R.success(sysConfig);
    }

    @ApiOperation(value = "新增系统配置表信息", notes = "根据必填项目新增系统配置表信息")
    @ApiImplicitParam(name = "sysConfig", required = true, value = "要新增的信息", dataType = "SysConfig", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.insert(sysConfig));
    }

    @ApiOperation(value = "修改系统配置表信息", notes = "修改系统配置表信息,id必填")
    @ApiImplicitParam(name = "sysConfig", required = true, value = "要编辑的信息", dataType = "SysConfig", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.update(sysConfig));
    }

    @ApiOperation(value = "根据id删除系统配置表信息查询", notes = "根据id删除系统配置表信息查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(sysConfigService.delete(id));
    }

}
