package com.zrj.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.result.Result;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.entity.EduTeacher;
import com.zrj.serviceedu.service.EduCourseService;
import com.zrj.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: IndexFrontController
 * @Description: 查询课程名名师接口
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，4未名师
    @GetMapping("getIndex")
    public Result index() {
        //查询前8热门课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByAsc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(courseQueryWrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return Result.ok().data("courseList", eduList).data("teacherList", teacherList);
    }
}
