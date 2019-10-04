package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.common.web.TableDataInfo;
import com.fanling.dreamland.module.entity.Comment;
import com.fanling.dreamland.module.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "修保设条目评论表")
@RestController
@RequestMapping("/manage/items/comment")
public class MaCommentController extends BaseController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "商品评论表分页查询", notes = "根据条件分页查看商品评论表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "CommentSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSearch<Comment> search) {
        startPage(search);
        Comment comment = new Comment();
        List<Comment> list = commentService.list(comment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询商品评论表", notes = "根据id查询商品评论表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Comment comment = commentService.selectById(id);
        if (comment == null) {
            return error("数据查找失败！");
        }
        return R.success(comment);
    }

    @ApiOperation(value = "新增商品评论表", notes = "根据必填项目新增商品评论表")
    @ApiImplicitParam(name = "comment", required = true, value = "要新增的信息", dataType = "Comment", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Comment comment) {
        return toAjax(commentService.insert(comment));
    }

    @ApiOperation(value = "修改商品评论表", notes = "修改商品评论表,id必填")
    @ApiImplicitParam(name = "comment", required = true, value = "要编辑的信息", dataType = "Comment", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Comment comment) {
        return toAjax(commentService.update(comment));
    }

    @ApiOperation(value = "根据id删除商品评论表查询", notes = "根据id删除商品评论表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(commentService.delete(id));
    }

}
