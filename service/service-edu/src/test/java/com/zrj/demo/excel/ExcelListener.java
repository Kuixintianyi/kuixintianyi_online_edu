package com.zrj.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExcelListener
 * @Description: 因为是一行一行地都，所以需要一个监听器
 * @Author Ruijin Zhou
 * @Date 2022/7/25
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    //创建list集合封装最终的数据
    List<DemoData> list = new ArrayList<>();
    //一行一行去读取excel内容
    @Override
    public void invoke(DemoData user, AnalysisContext analysisContext) {
        System.out.println("***"+user);
        list.add(user);
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
