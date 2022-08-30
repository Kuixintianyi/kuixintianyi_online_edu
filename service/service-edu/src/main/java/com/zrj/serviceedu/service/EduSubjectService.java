package com.zrj.serviceedu.service;

import com.zrj.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrj.serviceedu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-25
 */
public interface EduSubjectService extends IService<EduSubject> {
     void importSubjectData(MultipartFile file, EduSubjectService subjectService);
     List<SubjectNestedVo> nestedList();
}
