package com.zrj.serviceedu.client;

import com.zrj.result.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: VodFileDegradeFeignClient
 * @Description: 熔断
 * @Author Ruijin Zhou
 * @Date 2022/7/29
 * @Version 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{

    @Override
    public Result removeAliVideo(String videoId) {
        return Result.error().message("time out");
    }

    @Override
    public Result removeAlivideoList(List<String> videoIdList) {
        return Result.error().message("time out");
    }
}
