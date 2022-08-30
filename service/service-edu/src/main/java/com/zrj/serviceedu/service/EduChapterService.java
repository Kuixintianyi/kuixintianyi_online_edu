package com.zrj.serviceedu.service;

import com.zrj.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrj.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zrj
 * @since 2022-07-26
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
