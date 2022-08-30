package com.zrj.oss.controller;

import com.zrj.oss.service.FileService;
import com.zrj.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssController
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/eduoss/fileoss")
@Api(tags= "阿里云文件管理")
public class OssController {
    @Autowired
    FileService fileService;

    //上传头像的方法
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result uploadOssFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        //返回上传到oss的文件路径
        String uploadUrl = fileService.upload(file);
        //获取上传的文件
        return Result.ok().data("url", uploadUrl);
    }
}
