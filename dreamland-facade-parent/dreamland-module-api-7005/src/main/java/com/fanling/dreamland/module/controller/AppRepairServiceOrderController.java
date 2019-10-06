package com.fanling.dreamland.module.controller;

import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.utils.DateUtils;
import com.fanling.dreamland.common.utils.StringUtils;
import com.fanling.dreamland.common.web.BaseController;
import com.fanling.dreamland.module.entity.RepairServiceOrder;
import com.fanling.dreamland.module.entity.ServiceItems;
import com.fanling.dreamland.module.feign.AppUserFeign;
import com.fanling.dreamland.module.feign.entity.AppUserResp;
import com.fanling.dreamland.module.service.IRepairServiceOrderService;
import com.fanling.dreamland.module.service.IServiceItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Api(tags = "APP维修服务表")
@RestController
@RequestMapping("/app/repair/service/order")
public class AppRepairServiceOrderController extends BaseController {
    @Autowired
    private IRepairServiceOrderService repairServiceOrderService;

    @Autowired
    private AppUserFeign appUserFeign;

    @Autowired
    private IServiceItemsService serviceItemsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    @ApiOperation(value = "APP端预定", notes = "通过选择时间，预定维修")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/reserve")
    public R insertReserve(@RequestHeader("x-access-token") String token,
                           @RequestBody RepairServiceOrder repairServiceOrder) {
        if (!"3".equals(repairServiceOrder.getRepair_type())) {
            return error("维修类型选择错误！");
        }
        R error = initValue(token, repairServiceOrder);
        if (error != null) {
            return error;
        }
        // 预约时间的逻辑判断
        if (repairServiceOrder.getReservation_date() == null) {
            return error("预约时间不能为空！");
        }
        //时间不能低于当前时间1天时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 12);
        if (repairServiceOrder.getReservation_date().before(calendar.getTime())) {
            return error("预约时间需要当前时间12小时以后！");
        }
        //判断在工作时间范围内
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = null;
        Date endTime = null;
        try {
            String yy = ft1.format(repairServiceOrder.getReservation_date());
            startTime = ft2.parse(yy + " 09:00:00");
            endTime = ft2.parse(yy + " 18:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!DateUtils.isEffectiveDate(repairServiceOrder.getReservation_date(), startTime, endTime)) {
            return error("预定时间不在工作范围内，请选择每天09点-18点时间段内！");
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    @ApiOperation(value = "APP端马上维修", notes = "通过选择时间，APP端马上维修")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/current")
    public R insertCurrent(@RequestHeader("x-access-token") String token,
                           @RequestBody RepairServiceOrder repairServiceOrder) {
        if (!"1".equals(repairServiceOrder.getRepair_type())) {
            return error("维修类型选择错误！");
        }
        R error = initValue(token, repairServiceOrder);
        if (error != null) {
            return error;
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    @ApiOperation(value = "APP端申请救援设备", notes = "APP端申请救援设备")
    @ApiImplicitParam(name = "repairServiceOrder", required = true, value = "要新增的信息", dataType = "RepairServiceOrder", paramType = "body")
    @PostMapping("/insert/help")
    public R insertHelp(@RequestHeader("x-access-token") String token,
                        @RequestBody RepairServiceOrder repairServiceOrder) {
        if (!"2".equals(repairServiceOrder.getRepair_type())) {
            return error("维修类型选择错误！");
        }
        R error = initValue(token, repairServiceOrder);
        if (error != null) {
            return error;
        }
        return toAjax(repairServiceOrderService.insert(repairServiceOrder));
    }

    /**
     * 初始化信息
     *
     * @param token
     * @param repairServiceOrder
     * @return
     */
    private R initValue(String token, RepairServiceOrder repairServiceOrder) {
        String uid = ops.get("app_token_" + token);
        if (StringUtils.isEmpty(uid)) {
            return error("用户没有登录，请重新登录！");
        }
        repairServiceOrder.setUser_id(uid);
        //获取用户信息
        AppUserResp appUserResp = appUserFeign.selectById(uid);
        if (appUserResp.getCode() == 0) {
            repairServiceOrder.setUser_name(appUserResp.getData().getUser_name());
        } else {
            return error("查询用户失败！");
        }
        //获取商品信息
        ServiceItems serviceItems = serviceItemsService.selectBySubNo(repairServiceOrder.getGood_id());
        if (serviceItems == null) {
            return error("查询服务信息出错！");
        }
        repairServiceOrder.setTotal_price(Double.toString(serviceItems.getPrice()));
        return null;
    }
}
