package com.zrj.eduorder.client;

import com.zrj.vo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName: EduClient
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/8/1
 * @Version 1.0
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    //根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
