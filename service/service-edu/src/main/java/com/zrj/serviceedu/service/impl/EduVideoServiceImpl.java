package com.zrj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.serviceedu.client.VodClient;
import com.zrj.serviceedu.entity.EduVideo;
import com.zrj.serviceedu.mapper.EduVideoMapper;
import com.zrj.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    //TODO:删除小节时同时删除对应的视频:done
    @Override
    public void removeByCourseId(String courseId) {

        //1.先删除视频，因此需要查明课程所有视频id
        QueryWrapper<EduVideo> wrapperVideos = new QueryWrapper<>();
        wrapperVideos.eq("course_id", courseId);
        //2.由于我们只需要查询视频id，因此需要查询对应指定列的方法
        wrapperVideos.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideos);

        //需要变换list的泛型为string
        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if (StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }

        }
        //3.删除这些视频,但也需要判断有无视频，否则没必要远程调用这个方法
        if (StringUtils.isEmpty(videoIds)) {
            vodClient.removeAlivideoList(videoIds);
        }

       //4.之后再删小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        baseMapper.delete(videoQueryWrapper);
    }
}
