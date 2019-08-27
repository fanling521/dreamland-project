package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.entity.TopCategory;
import com.fanling.dreamland.entity.search.TopCategorySearch;
import com.fanling.dreamland.service.ITopCategoryService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品一级分类")
@RestController
@RequestMapping("/manager/goods/top_category")
public class TopCategoryController extends BaseController {

    @Autowired
    private ITopCategoryService topCategoryService;

    @ApiOperation(value = "商品一级分类分页查询", notes = "根据条件分页查看商品一级分类")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "TopCategorySearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody TopCategorySearch search) {
        startPage(search);
        TopCategory topCategory = new TopCategory();
        topCategory.setTop_goods_name(search.getTop_goods_name());
        List<TopCategory> list = topCategoryService.list(topCategory);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询商品一级分类", notes = "根据id查询商品一级分类")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        TopCategory topCategory = topCategoryService.selectById(id);
        if (topCategory == null) {
            return error("数据查找失败！");
        }
        return R.success(topCategory);
    }

    @ApiOperation(value = "新增商品一级分类", notes = "根据必填项目新增商品一级分类")
    @ApiImplicitParam(name = "topCategory", required = true, value = "要新增的信息", dataType = "TopCategory", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody TopCategory topCategory) {
        return toAjax(topCategoryService.insert(topCategory));
    }

    @ApiOperation(value = "修改商品一级分类", notes = "修改商品一级分类,id必填")
    @ApiImplicitParam(name = "topCategory", required = true, value = "要编辑的信息", dataType = "TopCategory", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody TopCategory topCategory) {
        return toAjax(topCategoryService.update(topCategory));
    }

    @ApiOperation(value = "根据id删除商品一级分类查询", notes = "根据id删除商品一级分类查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(topCategoryService.delete(id));
    }

}
