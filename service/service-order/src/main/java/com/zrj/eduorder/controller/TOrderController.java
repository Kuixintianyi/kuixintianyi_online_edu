package com.zrj.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.eduorder.entity.TOrder;
import com.zrj.eduorder.service.TOrderService;
import com.zrj.jwt.JwtUtils;
import com.zrj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-08-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //根据课程id和用户id生成订单,需要生成订单的id
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.addOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.ok().data("orderId", orderId);
    }

    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderId);
        TOrder order = orderService.getOne(queryWrapper);
        return Result.ok().data("order", order);
    }

    //根据课程id和用户id查询订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId) {

        int count = orderService.
                count(new QueryWrapper<TOrder>().eq("member_id", memberId).
                        eq("course_id", courseId).
                        eq("status", 1));
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}

