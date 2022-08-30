package com.zrj.serviceedu.controller;


import com.zrj.result.Result;
import com.zrj.serviceedu.entity.EduChapter;
import com.zrj.serviceedu.entity.chapter.ChapterVo;
import com.zrj.serviceedu.service.EduChapterService;
import com.zrj.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@CrossOrigin
@Api(tags = "课程章节管理")
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表，根据课程id进行查询
    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("GetChapterVideo/{courseId}")
    public Result getChapterVideo(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                  @PathVariable String courseId) {
        List<ChapterVo> chapterVideoByCourseId = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("list", chapterVideoByCourseId);
    }

    //增加课程章节
    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public Result addChapter(@ApiParam(name = "chapter", value = "章节对象", required = true)
                             @RequestBody EduChapter chapter) {
        chapterService.save(chapter);
        return Result.ok();
    }

    //根据id查询
    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public Result getById(
            @ApiParam(name = "chapterId", value = "章节ID", required = true)
            @PathVariable String chapterId) {
        EduChapter chapter = chapterService.getById(chapterId);
        return Result.ok().data("chapter", chapter);
    }

    //修改章节
    @ApiOperation(value = "根据ID修改章节")
    @PostMapping("updateChapter")
    public Result updateById(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter) {

        chapterService.updateById(chapter);
        return Result.ok();
    }

    //删除
    @DeleteMapping("deleteChapter/{chapterId}")
    public Result deleteChapter(@ApiParam(name = "chapterId", value = "章节ID", required = true)
                                @PathVariable String chapterId) {
        boolean res = chapterService.deleteChapter(chapterId);
        if (res) {
            return Result.ok();
        }else {
            return Result.error().message("删除失败！");
        }
    }
}

