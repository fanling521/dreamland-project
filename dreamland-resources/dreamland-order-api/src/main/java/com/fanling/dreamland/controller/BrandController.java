package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.entity.Brand;
import com.fanling.dreamland.entity.search.BrandSearch;
import com.fanling.dreamland.service.IBrandService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import com.fanling.dreamland.web.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品品牌表")
@RestController
@RequestMapping("/manager/brand")
public class BrandController extends BaseController {

    @Autowired
    private IBrandService brandService;

    @ApiOperation(value = "商品品牌表分页查询", notes = "根据条件分页查看商品品牌表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "BrandSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BrandSearch search) {
        startPage(search);
        Brand brand = new Brand();
        brand.setB_e_name(search.getB_e_name());
        List<Brand> list = brandService.list(brand);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询商品品牌表", notes = "根据id查询商品品牌表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Brand brand = brandService.selectById(id);
        if (brand == null) {
            return error("数据查找失败！");
        }
        return R.success(brand);
    }

    @ApiOperation(value = "新增商品品牌表", notes = "根据必填项目新增商品品牌表")
    @ApiImplicitParam(name = "brand", required = true, value = "要新增的信息", dataType = "Brand", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Brand brand) {
        return toAjax(brandService.insert(brand));
    }

    @ApiOperation(value = "修改商品品牌表", notes = "修改商品品牌表,id必填")
    @ApiImplicitParam(name = "brand", required = true, value = "要编辑的信息", dataType = "Brand", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Brand brand) {
        return toAjax(brandService.update(brand));
    }

    @ApiOperation(value = "根据id删除商品品牌表查询", notes = "根据id删除商品品牌表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(brandService.delete(id));
    }

}
