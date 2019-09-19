package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.AfterSale;
import com.fanling.dreamland.entity.search.AfterSaleSearch;
import com.fanling.dreamland.service.IAfterSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "售后处理")
@RestController
@RequestMapping("/manager/after_sale")
public class AfterSaleController extends BaseController {

    @Autowired
    private IAfterSaleService afterSaleService;

    @ApiOperation(value = "售后处理分页查询", notes = "根据条件分页查看售后处理")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "AfterSaleSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody AfterSaleSearch search) {
        startPage(search);
        AfterSale afterSale = new AfterSale();
        List<AfterSale> list = afterSaleService.list(afterSale);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询售后处理", notes = "根据id查询售后处理")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        AfterSale afterSale = afterSaleService.selectById(id);
        if (afterSale == null) {
            return error("数据查找失败！");
        }
        return R.success(afterSale);
    }

    @ApiOperation(value = "新增售后处理", notes = "根据必填项目新增售后处理")
    @ApiImplicitParam(name = "afterSale", required = true, value = "要新增的信息", dataType = "AfterSale", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody AfterSale afterSale) {
        return toAjax(afterSaleService.insert(afterSale));
    }

    @ApiOperation(value = "修改售后处理", notes = "修改售后处理,id必填")
    @ApiImplicitParam(name = "afterSale", required = true, value = "要编辑的信息", dataType = "AfterSale", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody AfterSale afterSale) {
        return toAjax(afterSaleService.update(afterSale));
    }

    @ApiOperation(value = "根据id删除售后处理查询", notes = "根据id删除售后处理查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(afterSaleService.delete(id));
    }

}