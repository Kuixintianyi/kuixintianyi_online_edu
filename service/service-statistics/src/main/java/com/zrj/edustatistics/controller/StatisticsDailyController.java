package com.zrj.edustatistics.controller;


import com.zrj.edustatistics.service.StatisticsDailyService;
import com.zrj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author zrj
 * @since 2022-08-02
 */
@CrossOrigin
@RestController
@RequestMapping("/edustatistics/statistics")
public class StatisticsDailyController {
       @Autowired
      private StatisticsDailyService dailyService;

       //1.创建统计信息
       @PostMapping("createStatisticsByDate/{day}")
       public Result createStatisticsByDate(@PathVariable String day) {
           dailyService.createStatisticsByDay(day);
           return Result.ok();
       }

       //展示统计数据：利用Echart
       @GetMapping("showChart/{begin}/{end}/{type}")
       public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
           Map<String, Object> map = dailyService.getChartData(begin, end, type);
           return Result.ok().data(map);
       }
}

