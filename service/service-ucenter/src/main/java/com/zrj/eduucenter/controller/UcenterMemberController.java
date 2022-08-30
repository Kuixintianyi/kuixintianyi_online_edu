package com.zrj.eduucenter.controller;


import com.zrj.eduucenter.entity.UcenterMember;
import com.zrj.eduucenter.entity.vo.RegisterVo;
import com.zrj.eduucenter.service.UcenterMemberService;
import com.zrj.jwt.JwtUtils;
import com.zrj.result.Result;
import com.zrj.vo.UcenterMemberOrder;
import com.zrj.vo.UcenterMemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-31
 */
@CrossOrigin
@RestController
@RequestMapping("/eduucenter/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

   //根据token字符串获取用户信息
    @PostMapping("getUserInfo/{id}")
    public UcenterMemberVo getUserInfo(@PathVariable String id) {
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberVo);
        return ucenterMemberVo;
    }

    //根据 id 获取用户信息
    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @PostMapping("login")
    public Result login(@RequestBody UcenterMember member) {
        //返回token值，使用jwt实现
        String token = memberService.login(member);
        return Result.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return Result.ok().data("userInfo",member);
    }

    //查询某一天的注册人数
    @GetMapping("countRegister/{day}")
    public Result countRegister(@PathVariable String day) {
       Integer count = memberService.countRegisterDay(day);
       return Result.ok().data("countRegister", count);
    }

}

