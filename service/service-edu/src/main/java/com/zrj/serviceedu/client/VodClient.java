package com.zrj.serviceedu.client;

import com.zrj.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class) //需要调用的名称
@Component
public interface VodClient {
    @DeleteMapping(value = "/eduvod/video/removeAliVideo/{videoId}")
    public Result removeAliVideo(@PathVariable("videoId") String videoId);

    //删除多个视频,一定要在List中指明泛型！
    @DeleteMapping(value = "/eduvod/video/removeAlivideoList")
    public Result removeAlivideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
