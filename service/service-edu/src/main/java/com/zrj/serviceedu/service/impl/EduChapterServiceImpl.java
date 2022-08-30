package com.zrj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrj.exception.GuliException;
import com.zrj.serviceedu.entity.EduChapter;
import com.zrj.serviceedu.entity.EduVideo;
import com.zrj.serviceedu.entity.chapter.ChapterVo;
import com.zrj.serviceedu.entity.chapter.VideoVo;
import com.zrj.serviceedu.mapper.EduChapterMapper;
import com.zrj.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrj.serviceedu.service.EduVideoService;
import com.zrj.serviceedu.vo.SubjectNestedVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.查询所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapperChapter);
        //2.查询所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //3.//最终要的到的数据列表封装
        ArrayList<ChapterVo> finalList = new ArrayList<>();
        //4.填充章节数据
        for (EduChapter chapter : eduChapters) {
            //创建章节类别vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finalList.add(chapterVo);
            //5.填充小节数据
            //创建集合用于封装章节中的小节
            ArrayList<VideoVo> videoList = new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                if (eduVideo.getChapterId().equals(chapter.getId())) {
                    VideoVo video = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, video);
                    videoList.add(video);
                }
            }
            chapterVo.setChildren(videoList);
        }

        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        int res;
        //根据章节id查询是否有小节，如果有查询到数据，不进行删除
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", chapterId);
        //这个方法是看能查询多少条记录。因为我们只需要知道有无记录，所以用这个方法更方便
        int count = videoService.count(videoQueryWrapper);
        if (count > 0) {
            //说明存在小节
            throw new GuliException(20001, "不能删除");
        } else {
            res = baseMapper.deleteById(chapterId);

        }
        return res > 0;
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        baseMapper.delete(chapterQueryWrapper);
    }
}
