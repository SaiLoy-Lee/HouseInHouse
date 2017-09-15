package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/15.
 */

@Controller
@RequestMapping("/chart")
class EchartController {

    //统计每季度房源概况
    @RequestMapping("/chartMultiX")
    public String chartMultiX(Model model) throws JsonProcessingException {


        Integer[] arr1 = new Integer[]{102, 105, 119, 126, 128, 170, 175, 182, 148, 118, 106, 102};
        Integer[] arr2 = new Integer[]{83, 85, 91, 98, 108, 109, 131, 96, 85, 88, 80, 80};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arr1);
        String data2 = objectMapper.writeValueAsString(arr2);
        Integer year = 2017;
        String season = "二";

        model.addAttribute("year", year);
        model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "chart/echartsMultiX";
    }

    //统计租金占比
    @RequestMapping("/chartRadar")
    public String chartRadar(Model model) throws JsonProcessingException {


        Integer[] arr1 = new Integer[]{73, 200, 380, 350, 500, 290};
        Integer[] arr2 = new Integer[]{50, 140, 280, 310, 420, 210};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arr1);
        String data2 = objectMapper.writeValueAsString(arr2);
        Integer year = 2017;
        String season = "一";

        model.addAttribute("year", year);
        model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "chart/echartsRadar";
    }

    //统计每季度房屋来源占比
    @RequestMapping("/chartBars")
    public String chartBars(Model model) throws JsonProcessingException {


        Integer[] arr1 = new Integer[]{320, 302, 301, 334, 390, 330, 320, 302, 301, 334, 390, 330};
        Integer[] arr2 = new Integer[]{120, 132, 101, 134, 90, 230, 210, 302, 301, 334, 390, 330};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arr1);
        String data2 = objectMapper.writeValueAsString(arr2);
        Integer year = 2016;
        String season = "四";

        model.addAttribute("year", year);
        model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "chart/echartsBars";
    }




}
