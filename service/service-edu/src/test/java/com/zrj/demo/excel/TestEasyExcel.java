package com.zrj.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestEasyExcel
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //写操作实现
        //1.写入文件夹路径及excel文件名称
       /* String fileName = "D:\\write.xlsx";
*/
        //2.// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        //write方法两个参数分别是文件路径名称和实体类
        /*EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());*/
        //读操作实现
        String fileName = "D:\\write.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("田所浩二" + i);
            list.add(data);
        }
        return list;
    }
}
