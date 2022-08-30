package com.zrj.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.exception.GuliException;
import com.zrj.serviceedu.entity.EduSubject;
import com.zrj.serviceedu.entity.excel.SubjectData;
import com.zrj.serviceedu.service.EduSubjectService;

/**
 * @ClassName: SubjectExcelListener
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {

    }

    //创建有参数构造，传递subjectService用于操作数据库,因为监听器不能交给spring操作，不能注入其他对象
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData user, AnalysisContext context) {
        if (user == null) {
            throw new GuliException(20001, "添加失败,文件数据为空");
        }
        //一行一行读取，添加一级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, user.getOneSubjectName());
        if (existOneSubject == null) {//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(user.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, user.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(user.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    private EduSubject existOneSubject (EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);
    }

    private EduSubject existTwoSubject (EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
