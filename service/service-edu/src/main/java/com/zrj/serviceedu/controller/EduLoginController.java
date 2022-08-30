package com.zrj.serviceedu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zrj.result.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: EduLoginController
 * @Description: 负责用户本地登录
 * @Author Ruijin Zhou
 * @Date 2022/7/19
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/eduservice/user")
public class EduLoginController {
    //login
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles","[admin]").data("name", "admin").data("avatar", "https://s2.loli.net/2022/07/20/b725hcqIikTYasL.jpg");
    }
}
