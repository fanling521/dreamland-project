package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.module.entity.Article;
import com.fanling.dreamland.module.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "文章信息表")
@RestController
@RequestMapping("/app/article")
public class AppArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "获取指定位置的文章信息", notes = "获取指定位置的文章信息")
    @ApiImplicitParam(name = "position", required = true, value = "广告位置", dataType = "String", paramType = "path")
    @PostMapping("/select/{source}")
    public R selectByType(@PathVariable("source") String source) {
        Article bean = new Article();
        bean.setSource(source);
        return getDatalist(articleService.list(bean));
    }

    @ApiOperation(value = "根据id查询文章信息表", notes = "根据id查询文章信息表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/detail/{id}")
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
}
