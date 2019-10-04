package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import com.fanling.dreamland.module.entity.Advertisement;
import com.fanling.dreamland.module.service.IAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "营销广告表")
@RestController
@RequestMapping("/manage/marketing")
public class MaAdvertisementController extends BaseController {

    @Autowired
    private IAdvertisementService advertisementService;

    @ApiOperation(value = "营销广告表分页查询", notes = "根据条件分页查看营销广告表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AdvertisementSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<Advertisement> search) {
        startPage(search);
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(search.getObj().getTitle());
        List<Advertisement> list = advertisementService.list(advertisement);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询营销广告表", notes = "根据id查询营销广告表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Advertisement advertisement = advertisementService.selectById(id);
        if (advertisement == null) {
            return error("数据查找失败！");
        }
        return R.success(advertisement);
    }

    @ApiOperation(value = "新增营销广告表", notes = "根据必填项目新增营销广告表")
    @ApiImplicitParam(name = "advertisement", required = true, value = "要新增的信息", dataType = "Advertisement", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Advertisement advertisement) {
        return toAjax(advertisementService.insert(advertisement));
    }

    @ApiOperation(value = "修改营销广告表", notes = "修改营销广告表,id必填")
    @ApiImplicitParam(name = "advertisement", required = true, value = "要编辑的信息", dataType = "Advertisement", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Advertisement advertisement) {
        return toAjax(advertisementService.update(advertisement));
    }

    @ApiOperation(value = "根据id删除营销广告表查询", notes = "根据id删除营销广告表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(advertisementService.delete(id));
    }

}
