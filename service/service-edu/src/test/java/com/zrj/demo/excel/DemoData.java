package com.zrj.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * @ClassName: DemoData
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty("学生编号")
    private Integer sno;

    //设置表头名称
    @ExcelProperty("学生姓名")
    private String sname;


}
