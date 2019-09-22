package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.common.web.TableDataInfo;
import com.fanling.dreamland.entity.Payment;
import com.fanling.dreamland.entity.search.PaymentSearch;
import com.fanling.dreamland.service.IPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "修保设条目支付表")
@RestController
@RequestMapping("/manage/payment")
public class MaPaymentController extends BaseController {

    @Autowired
    private IPaymentService paymentService;

    @ApiOperation(value = "支付表分页查询", notes = "根据条件分页查看支付表")
    @ApiImplicitParam(name = "search", required = true, value = "查询条件", dataType = "PaymentSearch", paramType = "body")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PaymentSearch search) {
        startPage(search);
        Payment payment = new Payment();
        List<Payment> list = paymentService.list(payment);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据id查询支付表", notes = "根据id查询支付表")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @PostMapping("/select/{id}")
    public R selectById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        Payment payment = paymentService.selectById(id);
        if (payment == null) {
            return error("数据查找失败！");
        }
        return R.success(payment);
    }

    @ApiOperation(value = "新增支付表", notes = "根据必填项目新增支付表")
    @ApiImplicitParam(name = "payment", required = true, value = "要新增的信息", dataType = "Payment", paramType = "body")
    @PostMapping("/insert")
    public R insert(@RequestBody Payment payment) {
        return toAjax(paymentService.insert(payment));
    }

    @ApiOperation(value = "修改支付表", notes = "修改支付表,id必填")
    @ApiImplicitParam(name = "payment", required = true, value = "要编辑的信息", dataType = "Payment", paramType = "body")
    @PostMapping("/update")
    public R update(@RequestBody Payment payment) {
        return toAjax(paymentService.update(payment));
    }

    @ApiOperation(value = "根据id删除支付表查询", notes = "根据id删除支付表查询")
    @ApiImplicitParam(name = "id", required = true, value = "唯一标识", dataType = "String", paramType = "path")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return error("id不能为空！");
        }
        return toAjax(paymentService.delete(id));
    }

}
