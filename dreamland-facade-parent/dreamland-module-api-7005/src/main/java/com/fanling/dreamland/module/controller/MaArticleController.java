package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import com.fanling.dreamland.module.entity.Article;
import com.fanling.dreamland.module.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章信息表")
@RestController
@RequestMapping("/manage/article")
public class MaArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "文章信息表分页查询", notes = "根据条件分页查看文章信息表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "ArticleSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<Article> search) {
        startPage(search);
        Article article = new Article();
        article.setTitle(search.getObj().getTitle());
        List<Article> list = articleService.list(article);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询文章信息表", notes = "根据id查询文章信息表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Article article = articleService.selectById(id);
        if (article == null) {
            return error("数据查找失败！");
        }
        return R.success(article);
    }

    @ApiOperation(value = "新增文章信息表", notes = "根据必填项目新增文章信息表")
    @ApiImplicitParam(name = "article", required = true, value = "要新增的信息", dataType = "Article", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Article article) {
        return toAjax(articleService.insert(article));
    }

    @ApiOperation(value = "修改文章信息表", notes = "修改文章信息表,id必填")
    @ApiImplicitParam(name = "article", required = true, value = "要编辑的信息", dataType = "Article", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Article article) {
        return toAjax(articleService.update(article));
    }

    @ApiOperation(value = "根据id删除文章信息表查询", notes = "根据id删除文章信息表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(articleService.delete(id));
    }

}
