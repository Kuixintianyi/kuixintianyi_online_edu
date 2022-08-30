package com.zrj.serviceedu.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName: VideoVo
 * @Description: 小节
 * @Author Ruijin Zhou
 * @Date 2022/7/26
 * @Version 1.0
 */
@Data
@ApiModel(value = "课时信息")
public class VideoVo {
    private String id;
    private String title;

    private String videoSourceId;
}
