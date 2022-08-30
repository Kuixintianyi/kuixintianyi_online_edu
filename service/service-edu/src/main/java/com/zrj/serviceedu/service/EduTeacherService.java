package com.zrj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.query.TeacherQuery;
import com.zrj.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-19
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);



}
