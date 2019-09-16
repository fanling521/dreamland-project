package com.fanling.dreamland.controller.app;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.entity.Article;
import com.fanling.dreamland.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "文章信息表")
@RestController
@RequestMapping("/app/article")
public class AppArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "获取指定位置的营销广告", notes = "获取指定位置的营销广告")
    @ApiImplicitParam(name = "position", required = true, value = "广告位置", dataType = "String", paramType = "path")
    @PostMapping("/select/{type}")
    public R selectByType(@PathVariable("type") String type) {
        if (StringUtils.isEmpty(type)) {
            return error("参数\"position\"不能为空！");
        }
        Article bean = new Article();
        bean.setSource(type);
        List<Article> advertisement = articleService.appList(bean);
        assert advertisement != null;
        return R.success(advertisement);
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
}
