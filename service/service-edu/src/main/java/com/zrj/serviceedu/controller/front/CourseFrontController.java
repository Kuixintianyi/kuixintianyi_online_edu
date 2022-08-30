package com.zrj.serviceedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.jwt.JwtUtils;
import com.zrj.result.Result;
import com.zrj.serviceedu.client.OrderClient;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.entity.chapter.ChapterVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseFrontVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseWebVo;
import com.zrj.serviceedu.service.EduChapterService;
import com.zrj.serviceedu.service.EduCourseService;
import com.zrj.vo.CourseWebVoOrder;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CourseFrontController
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/31
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/eduservice/coursefront")
@Api(tags = "前台课程管理")
@CrossOrigin //跨域
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    //条件分页查询课程
    @PostMapping("getCourseFrontList/{page}/{limit}")
    public Result getCourseFrontList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);

        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        return Result.ok().data(map);
    }

    //2.课程详情接口实现
    @GetMapping("getFrontCourseInfo/{courseId}")
   public Result getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        boolean isBuyCourse = orderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));

        //根据课程id查询章节以及小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuyCourse",isBuyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }



}
