package com.zrj.serviceedu.controller;


import com.zrj.result.Result;
import com.zrj.serviceedu.client.VodClient;
import com.zrj.serviceedu.entity.EduChapter;
import com.zrj.serviceedu.entity.EduVideo;
import com.zrj.serviceedu.service.EduVideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节：
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    //根据id查询小节
    @GetMapping("getVideoInfo/{videoId}")
    public Result getById(
            @PathVariable String videoId) {
        EduVideo video = videoService.getById(videoId);
        return Result.ok().data("video", video);
    }

    //修改小节
    @PostMapping("updateVideo")
    public Result updateById(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return Result.ok();
    }

    //删除小节
    //TODO:之后还需要删除视频
    @DeleteMapping("deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable String videoId) {
        //1.根据 小节id获取视频id，调用方法实现删除功能
        EduVideo eduVideo = videoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //2.还需要判断视频是否存在
        if (!StringUtils.isEmpty(videoSourceId)) {
            //说明存在视频，进行删除
            vodClient.removeAliVideo(videoId);
        }
        //然后删除小节
        videoService.removeById(videoId);
        return Result.ok();
    }
}

