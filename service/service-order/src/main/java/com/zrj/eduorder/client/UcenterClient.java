package com.zrj.eduorder.client;

import com.zrj.vo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/eduucenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
