package com.zrj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.exception.GuliException;
import com.zrj.serviceedu.entity.EduCourseDescription;
import com.zrj.serviceedu.entity.vo.CoursePublishVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseFrontVo;
import com.zrj.serviceedu.entity.vo.frontvo.CourseWebVo;
import com.zrj.serviceedu.query.CourseQuery;
import com.zrj.serviceedu.service.EduChapterService;
import com.zrj.serviceedu.service.EduCourseDescriptionService;
import com.zrj.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import com.zrj.serviceedu.entity.EduCourse;
import com.zrj.serviceedu.entity.vo.CourseInfoVo;
import com.zrj.serviceedu.mapper.EduCourseMapper;
import com.zrj.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService; //课程描述注入
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private EduChapterService chapterService;


    /*
     对于一对一关系的表，可以合并二者字段到同一个封装实体类中，然后用这个实体类向两个表添加信息
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表中添加基本信息
        //转换对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new GuliException(20001, "添加失败");
        }

        //获取添加课程之后的id
        String cid = eduCourse.getId();
        //向课程描述表添加课程简介
        //注入课程描述服务类
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    //查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = eduCourseService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        System.out.println(eduCourse.getId());

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.x修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改信息失败");
        }

        //2.修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        boolean updateDescription = courseDescriptionService.updateById(description);
        if (!updateDescription) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVo(String courseId) {
        //调用mapper
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByAsc("gmt_create");
        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, courseQueryWrapper);
            return;
        }
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        if (!StringUtils.isEmpty(title)) {
            courseQueryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            courseQueryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            courseQueryWrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            courseQueryWrapper.ge("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam, courseQueryWrapper);
    }

    @Override
    public void removeCourse(String courseId) {
        //1.先删除小节
        videoService.removeByCourseId(courseId);
        //2.再删除章节
        chapterService.removeByCourseId(courseId);
        //3.再删除课程描述
        courseDescriptionService.removeById(courseId);
        //4.删除课程本身
        int res = baseMapper.deleteById(courseId);
        if (res == 0) {
            throw new GuliException(20001, "删除失败");
        }
    }

    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courseList = baseMapper.selectList(queryWrapper);
        return courseList;

    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            queryWrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse, queryWrapper);

        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
