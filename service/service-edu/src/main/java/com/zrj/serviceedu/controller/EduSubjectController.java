package com.zrj.serviceedu.controller;


import com.zrj.result.Result;
import com.zrj.serviceedu.service.EduSubjectService;
import com.zrj.serviceedu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(tags = "课程分类管理")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //实现添加课程分类接口
    //获取上传的文件并读取
    @ApiOperation(value = "课程分类添加")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.importSubjectData(file, subjectService);
        //判断返回集合是否为空
        return Result.ok();
    }

    //实现树形模式显示课程分类接口
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("nestedList")
    public Result nestedList() {
        //泛型写一级分类即可
        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();
        return Result.ok().data("item", subjectNestedVoList);
    }
}

