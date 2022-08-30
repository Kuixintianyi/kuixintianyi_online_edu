package com.zrj.eduorder.controller;


import com.zrj.eduorder.service.TPayLogService;
import com.zrj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-08-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/log")
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;

    //生成微信二维码
    @GetMapping("createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNatvie(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return Result.ok().data(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return Result.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中");

    }
}

