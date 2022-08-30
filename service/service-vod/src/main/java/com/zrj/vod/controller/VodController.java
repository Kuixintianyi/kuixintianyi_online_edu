package com.zrj.vod.controller;

import com.aliyuncs.DefaultAcsClient;

import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zrj.exception.GuliException;
import com.zrj.result.Result;

import com.zrj.vod.service.VodService;
import com.zrj.vod.util.AliyunUploadVideoSdkUtils;
import com.zrj.vod.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: VodController
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/27
 * @Version 1.0
 */
@Api(tags = "阿里云视频上传")
@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云服务器中
    @ApiOperation(value = "分页课程列表")
    @PostMapping("uploadVideo")
    public Result uploadVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideo(file);
        return Result.ok().data("videoId", videoId);
    }

    //从云端删除视频
    @DeleteMapping("removeAliVideo/{videoId}")
    public Result removeAliVideo(@PathVariable  String videoId) {
        vodService.removeVideo(videoId);
        return Result.ok();
    }

    //删除多个视频的方法
    @DeleteMapping("removeAlivideoList")
    public Result removeAlivideoList(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeAliVideoList(videoIdList);
        return Result.ok();
    }

    //获取视频凭证
    @GetMapping("getVideoPlayAuth/{videoId}")
    public Result getVideoPlayAuth(@PathVariable String videoId)  {

        try {
            //获取阿里云存储相关常量
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

            //初始化
            DefaultAcsClient client = AliyunUploadVideoSdkUtils.initVodClient(accessKeyId, accessKeySecret);

            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);

            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();

            //返回结果
            return Result.ok().data("playAuth", playAuth);
        } catch (Exception e) {
           throw new GuliException(20001, "获取凭证失败");
        }

    }

}
