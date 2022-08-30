package com.zrj.serviceedu.service;

import com.zrj.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);
}
