package com.zrj.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.result.Result;
import com.zrj.serviceedu.entity.EduTeacher;
import com.zrj.serviceedu.query.TeacherQuery;
import com.zrj.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-19
 */
@RestController
@RequestMapping(value = "/eduservice/teacher")
@Api(tags = "讲师管理")
@CrossOrigin //跨域
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;




    //查询所有数据
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result list() {
        List<EduTeacher> list = teacherService.list(null);
        return Result.ok().data("items", list);
    }

    //2.逻辑删除讲师:利用swagger，不需要postman
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("findQueryPage/{page}/{limit}")
    //注意添加@RequestBody后，需要改成post形式发送数据
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }

    //新增讲师接口
    //考虑到我们需要传入json数据来实现增加功能，因此采用@@RequestBody来实现，注意默认不为空
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                       @RequestBody EduTeacher teacher) {
        boolean isSave = teacherService.save(teacher);
        if (isSave) {
            return Result.ok();
        } else {
            return Result.error();
        }

    }

    //修改讲师信息接口-根据id查询
    @ApiOperation(value = "根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result getById(@PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return Result.ok().data("item", teacher);
    }

    //修改讲师信息接口-最终实现
    @ApiOperation(value = "修改最终实现")
    @PostMapping("updateTeacher")
    public Result update(@RequestBody EduTeacher teacher) {
        boolean isUpdate = teacherService.updateById(teacher);
        if (isUpdate) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //批量删除接口
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        teacherService.removeByIds(idList);
        return Result.ok();
    }


}

