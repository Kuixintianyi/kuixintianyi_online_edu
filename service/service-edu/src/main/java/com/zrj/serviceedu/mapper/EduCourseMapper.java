package com.zrj.serviceedu.mapper;

import com.zrj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrj.serviceedu.entity.vo.CoursePublishVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
