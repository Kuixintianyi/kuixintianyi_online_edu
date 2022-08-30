package com.zrj.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrj.educms.entity.CrmBanner;
import com.zrj.educms.service.CrmBannerService;
import com.zrj.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: BannerAdminController
 * @Description: 后台管理系统
 * @Author Ruijin Zhou
 * @Date 2022/7/30
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    //1.获取banner分页列表
    @ApiOperation(value = "Banner分页列表展示")
    @GetMapping("getPageBanner/{page}/{limit}")
    public Result getPageBanner(@PathVariable long page,@PathVariable long limit) {
        Page<CrmBanner> bannerPage = new Page<>(page, limit);
        bannerService.page(bannerPage, null);
        return Result.ok().data("banners",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }

    //2.新增banner
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return Result.ok();
    }

    //3.根据id查询banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("getBannerById/{bannerId}")
    public Result getBannerById(@PathVariable String bannerId) {
        CrmBanner banner = bannerService.getById(bannerId);
        return Result.ok().data("banner", banner);
    }

    //4.修改banner
    @ApiOperation(value = "修改Banner")
    @PostMapping
    public Result updateBanner(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.ok();
    }

    //5.删除banner
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("removeBanner/{bannerId}")
    public Result removeBanner(@PathVariable String bannerId) {
        bannerService.removeById(bannerId);
        return  Result.ok();
    }
}
