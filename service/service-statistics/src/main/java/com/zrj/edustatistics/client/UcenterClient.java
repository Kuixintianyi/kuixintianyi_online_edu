package com.zrj.edustatistics.client;

import com.zrj.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: UcenterClient
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/8/2
 * @Version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/eduucenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable String day);
}
