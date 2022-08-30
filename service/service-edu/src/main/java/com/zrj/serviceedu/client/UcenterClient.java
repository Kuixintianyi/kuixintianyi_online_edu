package com.zrj.serviceedu.client;

import com.zrj.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/eduucenter/member/getUserInfo/{memberId}")
    public UcenterMemberVo getUserInfo(@PathVariable String memberId);

}
