package com.zrj.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.result.Result;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.entity.vo.CourseInfoVo;
import com.zrj.serviceedu.entity.vo.CoursePublishVo;
import com.zrj.serviceedu.query.CourseQuery;
import com.zrj.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器,包含课程简介
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //课程列表功能
    //TODO：完善条件查询带分页功能实现
    @ApiOperation(value = "分页课程列表")
    @GetMapping("getCourseList/{page}/{limit}")
    public Result getCourseList(@ApiParam(name = "page", value = "当前页码", required = true)
                                @PathVariable Long page,
                                @ApiParam(name = "limit", value = "每页记录数", required = true)
                                @PathVariable Long limit,
                                @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                                        CourseQuery courseQuery) {
        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        courseService.pageQuery(eduCoursePage, courseQuery);
        List<EduCourse> records = eduCoursePage.getRecords();
        long total = eduCoursePage.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    //添加课程信息
    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
                                @RequestBody CourseInfoVo courseInfoVo) {
        //需要返回添加之后的课程id，为之后添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);

        return Result.ok().data("id", id);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                @PathVariable String courseId) {
        CourseInfoVo courseInfo = courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfo", courseInfo);
    }

    //修改课程信息
    @ApiOperation(value = "更新课程")
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
                                   @RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    //查询课程发布信息
    //注意：
    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("getPublishCourseInfo/{courseId}")
    public Result getPublishCourseInfo(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                       @PathVariable String courseId) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(courseId);
        return Result.ok().data("coursePublish", coursePublishVo);
    }

    //课程最终发布
    @PostMapping("publishCourse/{courseId}")
    public Result finalPublish(@PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return Result.ok();
    }

    //删除课程
    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("deleteCourse/{courseId}")
    public Result deleteCourse(@ApiParam(name = "courseId", value = "课程ID", required = true)
                               @PathVariable String courseId) {

        courseService.removeCourse(courseId);
        return Result.ok();
    }
}

