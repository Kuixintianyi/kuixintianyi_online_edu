package com.zrj.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: ReadData
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
@Data
public class ReadData {
    //设置列对应的属性
    @ExcelProperty(index = 0)
    private int sid;

    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;

}
