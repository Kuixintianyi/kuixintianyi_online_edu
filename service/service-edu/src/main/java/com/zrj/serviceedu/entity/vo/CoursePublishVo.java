package com.zrj.serviceedu.entity.vo;

import lombok.Data;


/**
 * @ClassName: CoursePublishVo
 * @Description: 课程发布进行查询时需要的属性封装的实体类
 * @Author Ruijin Zhou
 * @Date 2022/7/26
 * @Version 1.0
 */
@Data
public class CoursePublishVo {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
