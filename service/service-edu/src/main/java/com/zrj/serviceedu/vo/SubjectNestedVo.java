package com.zrj.serviceedu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SubjectNestedVo
 * @Description: 一级分类
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
@Data

public class SubjectNestedVo {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}

