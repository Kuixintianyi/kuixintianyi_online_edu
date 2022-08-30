package com.zrj.educms.controller;


import com.zrj.educms.entity.CrmBanner;
import com.zrj.educms.service.CrmBannerService;
import com.zrj.result.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-07-30
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //1.获取banner列表
    @GetMapping("getBannerList")
    public Result getBannerList() {
       List<CrmBanner> bannerList = bannerService.selectAllBanners();
        return Result.ok().data("list", bannerList);
    }


}

