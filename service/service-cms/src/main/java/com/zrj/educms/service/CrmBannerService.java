package com.zrj.educms.service;

import com.zrj.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-30
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanners();
}
