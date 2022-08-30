package com.zrj.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: SubjectData
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
