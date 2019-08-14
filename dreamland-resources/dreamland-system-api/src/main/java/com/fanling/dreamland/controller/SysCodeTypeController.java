package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.common.annotations.UseJwtToken;
import com.fanling.dreamland.entity.search.SysCodeTypeSearch;
import com.fanling.dreamland.entity.SysCodeType;
import com.fanling.dreamland.service.ISysCodeTypeService;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统代码类别表")
@RestController
@RequestMapping("/system/code_type")
public class SysCodeTypeController extends BaseController {

    @Autowired
    private ISysCodeTypeService sysCodeTypeService;

    @ApiOperation(value = "系统代码类别表分页查询", notes = "根据条件分页查看系统代码类别表")
    @ApiImplicitParam(name = "search", value = "查询条件", dataType = "SysCodeTypeSearch", paramType = "body")
    @UseJwtToken
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysCodeTypeSearch search) {
        startPage(search);
        SysCodeType sysCodeType = new SysCodeType();
        sysCodeType.setCode_type_name(search.getCode_type_name());
        sysCodeType.setCode_type_value(search.getCode_type_value());
        List<SysCodeType> list = sysCodeTypeService.list(sysCodeType);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询系统代码类别表", notes = "根据id查询系统代码类别表，codeTypeId必填")
    @ApiImplicitParam(name = "code_type_id", value = "唯一标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/select/{code_type_id}")
    public R selectById(@PathVariable("code_type_id") String codeTypeId) {
        if (StringUtils.isEmpty(codeTypeId)) {
            return error("code_type_id不能为空！");
        }
        SysCodeType sysCodeType = sysCodeTypeService.selectById(codeTypeId);
        if (sysCodeType == null) {
            return error("数据查找失败！");
        }
        return R.success(sysCodeType);
    }

    @ApiOperation(value = "新增系统代码类别表", notes = "根据必填项目新增系统代码类别表")
    @ApiImplicitParam(name = "sysCodeType", value = "要新增的信息", dataType = "SysCodeType", paramType = "body")
    @UseJwtToken
    @PostMapping("/insert")
    public R insert(@RequestBody SysCodeType sysCodeType) {
        return toAjax(sysCodeTypeService.insert(sysCodeType));
    }

    @ApiOperation(value = "修改系统代码类别表", notes = "修改系统代码类别表,code_type_id必填")
    @ApiImplicitParam(name = "sysCodeType", value = "要编辑的信息", dataType = "SysCodeType", paramType = "body")
    @UseJwtToken
    @PostMapping("/update")
    public R update(@RequestBody SysCodeType sysCodeType) {
        return toAjax(sysCodeTypeService.update(sysCodeType));
    }

    @ApiOperation(value = "根据id删除系统代码类别表查询", notes = "根据id删除系统代码类别表查询,codeTypeId必填")
    @ApiImplicitParam(name = "code_type_id", value = "唯一标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @DeleteMapping("/remove/{code_type_id}")
    public R remove(@PathVariable("code_type_id") String codeTypeId) {
        if (StringUtils.isEmpty(codeTypeId)) {
            return error("code_type_id不能为空！");
        }
        return toAjax(sysCodeTypeService.delete(codeTypeId));
    }

    @ApiOperation(value = "获取代码类型的树", notes = "获取代码类型的树")
    @UseJwtToken
    @PostMapping("/tree")
    public R tree() {
        return R.success(sysCodeTypeService.tree());
    }

}
