package com.zrj.edumsm.controller;

import com.zrj.edumsm.service.MsmService;
import com.zrj.edumsm.util.RandomUtil;
import com.zrj.result.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MsmApiController
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/edumsm/msm")
public class MsmApiController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phoneNumber}")
    public Result code(@PathVariable String phoneNumber) {
        //从redis获取验证码
        String code = redisTemplate.opsForValue().get(phoneNumber);
        if(!StringUtils.isEmpty(code)) return Result.ok();
        code = RandomUtil.getFourBitRandom();
        boolean isSend = msmService.send(phoneNumber,code);
        if (isSend) {
            //用redis设置验证码的有效时间
            redisTemplate.opsForValue().set(phoneNumber, code, 5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.error();
        }



    }


}
