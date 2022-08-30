package com.zrj.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.result.Result;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.entity.EduTeacher;
import com.zrj.serviceedu.service.EduCourseService;
import com.zrj.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.hamcrest.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: teacherFrontController
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/31
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/eduservice/teacherfront")
@Api(tags = "前台讲师管理")
@CrossOrigin //跨域
public class teacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //1 分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.pageListWeb(pageTeacher);
        //返回分页所有数据
        return Result.ok().data(map);
    }

    //2.讲师详情
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId) {
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return Result.ok().data("teacher", eduTeacher).data("courseList",courseList);
    }

}
