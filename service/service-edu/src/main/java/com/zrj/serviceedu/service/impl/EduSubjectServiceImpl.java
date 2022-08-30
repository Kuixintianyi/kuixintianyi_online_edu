package com.zrj.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.exception.GuliException;
import com.zrj.serviceedu.entity.EduSubject;
import com.zrj.serviceedu.entity.excel.SubjectData;
import com.zrj.serviceedu.listener.SubjectExcelListener;
import com.zrj.serviceedu.mapper.EduSubjectMapper;
import com.zrj.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrj.serviceedu.vo.SubjectNestedVo;
import com.zrj.serviceedu.vo.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zrj
 * @since 2022-07-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService) {
        try {
        //1 获取文件输入流
        InputStream inputStream = file.getInputStream();

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
    }catch(Exception e) {
        e.printStackTrace();
        throw  new GuliException(20002,"添加课程分类失败");

    }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", 0);
        List<EduSubject> subjects = baseMapper.selectList(wrapperOne);

        //查询所有二级分类
        QueryWrapper<EduSubject>  wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", 0);
        List<EduSubject> subSubjects = baseMapper.selectList(wrapperTwo);

        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> finalSubjectList = new ArrayList<>();

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);

            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            finalSubjectList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {

                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){

                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }


        return finalSubjectList;
    }
}
