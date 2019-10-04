package com.fanling.dreamland.auth.controller;

import com.fanling.dreamland.auth.entity.AppIdCard;
import com.fanling.dreamland.auth.service.IAppIdCardService;
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

@Api(tags = "用户身份证表")
@RestController
@RequestMapping("/manage/id/card")
public class MaAppIdCardController extends BaseController {

    @Autowired
    private IAppIdCardService appIdCardService;

    @ApiOperation(value = "用户身份证表分页查询", notes = "根据条件分页查看用户身份证表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AppIdCardSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<AppIdCard> search) {
        startPage(search);
        AppIdCard appIdCard = new AppIdCard();
        appIdCard.setReal_name(search.getObj().getReal_name());
        appIdCard.setId_card_num(search.getObj().getId_card_num());
        List<AppIdCard> list = appIdCardService.list(appIdCard);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询用户身份证表", notes = "根据id查询用户身份证表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AppIdCard appIdCard = appIdCardService.selectById(id);
        if (appIdCard == null) {
            return error("数据查找失败！");
        }
        return R.success(appIdCard);
    }

    @ApiOperation(value = "新增用户身份证表", notes = "根据必填项目新增用户身份证表")
    @ApiImplicitParam(name = "appIdCard", required = true, value = "要新增的信息", dataType = "AppIdCard", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AppIdCard appIdCard) {
        return toAjax(appIdCardService.insert(appIdCard));
    }

    @ApiOperation(value = "修改用户身份证表", notes = "修改用户身份证表,id必填")
    @ApiImplicitParam(name = "appIdCard", required = true, value = "要编辑的信息", dataType = "AppIdCard", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AppIdCard appIdCard) {
        return toAjax(appIdCardService.update(appIdCard));
    }

    @ApiOperation(value = "根据id删除用户身份证表查询", notes = "根据id删除用户身份证表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(appIdCardService.delete(id));
    }

}
