package com.zrj.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.jwt.JwtUtils;
import com.zrj.result.Result;
import com.zrj.serviceedu.client.UcenterClient;
import com.zrj.serviceedu.entity.EduComment;
import com.zrj.serviceedu.service.EduCommentService;
import com.zrj.vo.UcenterMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-08-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;

    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id分页查询评论
    @GetMapping("/getCommentPage/{page}/{limit}")
    public Result getCommentPage(@PathVariable Long page, @PathVariable Long limit, String courseId) {
        Page<EduComment> commentPage = new Page<>(page, limit);

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();

        //判断课程id是否为空
        if (!StringUtils.isEmpty(courseId)) {
            wrapper.eq("course_id", courseId);
        }

        //按最新排序
        wrapper.orderByDesc("gmt_create");

        //数据会被封装到commentPage中
        eduCommentService.page(commentPage, wrapper);

        List<EduComment> commentList = commentPage.getRecords();
        long current = commentPage.getCurrent();//当前页
        long size = commentPage.getSize();//一页记录数
        long total = commentPage.getTotal();//总记录数
        long pages = commentPage.getPages();//总页数
        boolean hasPrevious = commentPage.hasPrevious();//是否有上页
        boolean hasNext = commentPage.hasNext();//是否有下页

        HashMap<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("size", size);
        map.put("total", total);
        map.put("pages", pages);
        map.put("hasPrevious", hasPrevious);
        map.put("hasNext", hasNext);
        map.put("list", commentList);

        return Result.ok().data(map);
    }

    //添加评论功能实现
    @PostMapping("addComment")
    public Result addComment(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return Result.error().message("请登录！");
        }
        comment.setMemberId(memberId);
        //远程调用ucenter根据用户id获取用户信息
        UcenterMemberVo memberVo = ucenterClient.getUserInfo(memberId);
        comment.setAvatar(memberVo.getAvatar());
        comment.setNickname(memberVo.getNickname());

        //保存评论
        eduCommentService.save(comment);

        return Result.ok();
    }

}

