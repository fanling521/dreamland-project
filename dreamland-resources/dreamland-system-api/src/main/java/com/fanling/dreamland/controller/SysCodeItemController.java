package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.auth.annotations.UseJwtToken;
import com.fanling.dreamland.config.CodeMainCache;
import com.fanling.dreamland.entity.search.SysCodeItemSearch;
import com.fanling.dreamland.entity.SysCodeItem;
import com.fanling.dreamland.service.ISysCodeItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统代码明细表")
@RestController
@RequestMapping("/system/code_item")
public class SysCodeItemController extends BaseController {

    @Autowired
    private ISysCodeItemService sysCodeItemService;

    @ApiOperation(value = "系统代码明细表分页查询", notes = "根据条件分页查看系统代码明细表")
    @ApiImplicitParam(name = "search", value = "查询条件", dataType = "SysCodeItemSearch", paramType = "body")
    @UseJwtToken
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysCodeItemSearch search) {
        startPage(search);
        SysCodeItem sysCodeItem = new SysCodeItem();
        sysCodeItem.setCode_item_key(search.getCode_item_key());
        sysCodeItem.setCode_item_value(search.getCode_item_value());
        sysCodeItem.setCode_type_id(search.getCode_type_id());
        List<SysCodeItem> list = sysCodeItemService.list(sysCodeItem);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询系统代码明细表", notes = "根据id查询系统代码明细表，codeItemId必填")
    @ApiImplicitParam(name = "code_item_id", value = "唯一标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/select/{code_item_id}")
    public R selectById(@PathVariable("code_item_id") String codeItemId) {
        if (StringUtils.isEmpty(codeItemId)) {
            return error("code_item_id不能为空！");
        }
        SysCodeItem sysCodeItem = sysCodeItemService.selectById(codeItemId);
        if (sysCodeItem == null) {
            return error("数据查找失败！");
        }
        return R.success(sysCodeItem);
    }

    @ApiOperation(value = "新增系统代码明细表", notes = "根据必填项目新增系统代码明细表")
    @ApiImplicitParam(name = "sysCodeItem", value = "要新增的信息", dataType = "SysCodeItem", paramType = "body")
    @UseJwtToken
    @PostMapping("/insert")
    public R insert(@RequestBody SysCodeItem sysCodeItem) {
        return toAjax(sysCodeItemService.insert(sysCodeItem));
    }

    @ApiOperation(value = "修改系统代码明细表", notes = "修改系统代码明细表,code_item_id必填")
    @ApiImplicitParam(name = "sysCodeItem", value = "要编辑的信息", dataType = "SysCodeItem", paramType = "body")
    @UseJwtToken
    @PostMapping("/update")
    public R update(@RequestBody SysCodeItem sysCodeItem) {
        return toAjax(sysCodeItemService.update(sysCodeItem));
    }

    @ApiOperation(value = "根据id删除系统代码明细表查询", notes = "根据id删除系统代码明细表查询,codeItemId必填")
    @ApiImplicitParam(name = "code_item_id", value = "唯一标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @DeleteMapping("/remove/{code_item_id}")
    public R remove(@PathVariable("code_item_id") String codeItemId) {
        if (StringUtils.isEmpty(codeItemId)) {
            return error("code_item_id不能为空！");
        }
        return toAjax(sysCodeItemService.delete(codeItemId));
    }

    @ApiOperation(value = "查询所有的代码项", notes = "查询所有的代码项，返回代码项明细")
    @UseJwtToken(required = false)
    @PostMapping("/get_code_main")
    public R getCodeMain() {
        return R.success(CodeMainCache.getCodeMainList());
    }
}
