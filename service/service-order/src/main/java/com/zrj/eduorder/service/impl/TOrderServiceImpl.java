package com.zrj.eduorder.service.impl;

import com.zrj.eduorder.client.EduClient;
import com.zrj.eduorder.client.UcenterClient;
import com.zrj.eduorder.entity.TOrder;
import com.zrj.eduorder.mapper.TOrderMapper;
import com.zrj.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrj.eduorder.util.OrderNoUtil;
import com.zrj.vo.CourseWebVoOrder;
import com.zrj.vo.UcenterMemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zrj
 * @since 2022-08-01
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    //注入两个远程调用客户端
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String addOrder(String courseId, String memberId) {
        //需要远程调用得到课程id以及用户id
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
