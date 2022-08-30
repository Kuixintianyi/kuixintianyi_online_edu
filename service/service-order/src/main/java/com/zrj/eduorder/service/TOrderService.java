package com.zrj.eduorder.service;

import com.zrj.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-08-01
 */
public interface TOrderService extends IService<TOrder> {

    String addOrder(String courseId, String memberId);


}
