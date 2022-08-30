package com.zrj.serviceedu.entity.vo.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CourseFrontVo
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/31
 * @Version 1.0
 */
@Data
public class CourseFrontVo {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
