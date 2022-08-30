package com.zrj.serviceedu.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ChapterVo
 * @Description: 章节
 * @Author Ruijin Zhou
 * @Date 2022/7/26
 * @Version 1.0
 */
@Data
@ApiModel(value = "章节信息")
public class ChapterVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
