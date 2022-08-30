package com.zrj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrj.serviceedu.entity.vo.CourseInfoVo;
import com.zrj.serviceedu.entity.vo.CoursePublishVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseFrontVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseWebVo;
import com.zrj.serviceedu.query.CourseQuery;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
public interface EduCourseService extends IService<EduCourse> {
      String saveCourseInfo(CourseInfoVo courseInfoVo);

      CourseInfoVo getCourseInfo(String courseId);

      void updateCourseInfo(CourseInfoVo courseInfoVo);

      CoursePublishVo getCoursePublishVo(String courseId);

      void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

      void removeCourse(String courseId);

      List<EduCourse> selectByTeacherId(String teacherId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
