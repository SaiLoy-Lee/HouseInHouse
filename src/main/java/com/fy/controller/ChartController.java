package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fy.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */

@Controller
public class ChartController {

    @Autowired
    private ChartService chartService;

    //此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
    //将日期格式修改成中文的格式yyyy-MM-dd
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/chart/chartList")
    public String chartList(){

        return "/chart/chartList";
    }

    //统计每季度房源概况
    @RequestMapping("/chart/chartMultiX")
    public String chartMultiX(Model model) throws JsonProcessingException {


        //Date date1 = new Date(117,5,1);
        //Date date2 = new Date(117,8,1);

        Date date2 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(cal.MONTH, -3);
        Date date1 = cal.getTime();



        Integer[] dayHouse = chartService.findDayHouseNum(date1, date2);  //每天的房源发布数目
        Integer[] dayOrder = chartService.findDayOrderNum(date1,date2);   //每天的订单入住数目

        Integer[] arrHouse = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};  //每周的房源发布数目
        Integer[] arrOrder = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};  //每周的订单入住数目
        int m = -1;
        for( int i=0; i<dayHouse.length;i++){
            if(i%7==0){
                m++;
                if(m>=12){
                    break;
                }
            }
            arrHouse[m] = arrHouse[m]+dayHouse[i];
        }
        int n = -1;
        for( int i=0; i<dayOrder.length;i++){
            if(i%7==0&&n<12){
                n++;
                if(n>=12){
                    break;
                }
            }
            arrOrder[n] += dayOrder[i];
        }
        //System.out.print(arr1);
        //System.out.print(arr2);

        //Integer[] arr1 = new Integer[]{102, 105, 119, 126, 128, 170, 175, 182, 148, 118, 106, 102};
        //Integer[] arr2 = new Integer[]{83, 85, 91, 98, 108, 109, 131, 96, 85, 88, 80, 80};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arrHouse);
        String data2 = objectMapper.writeValueAsString(arrOrder);
        Integer year1 = date1.getYear()+1900;
        Integer year2 = date2.getYear()+1900;

        Integer month1 = date1.getMonth()+1;
        Integer month2 = date2.getMonth()+1;
        //String season = date1.getMonth()/3;

        model.addAttribute("year1", year1);
        model.addAttribute("year2", year2);
        model.addAttribute("month1", month1);
        model.addAttribute("month2", month2);
        //model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "/chart/echartsMultiX";
    }

    //统计租金分布占比
    @RequestMapping("/chart/chartRadar")
    public String chartRadar( Model model) throws JsonProcessingException {

        //Date date1 = new Date(117,5,1);
        //Date date2 = new Date(117,8,1);

        Date date2 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(cal.MONTH, -3);
        Date date1 = cal.getTime();


        Integer[] HouseRents = chartService.findHouseRents(date1,date2);    //查询每个的房源发布的租金
        Integer[] OrderRents = chartService.findOrderRents(date1,date2);    //查询每个的订单的租金

        Integer[] arrHouse = new Integer[]{0,0,0,0,0,0};  //每段的房源发布租金
        Integer[] arrOrder = new Integer[]{0,0,0,0,0,0};  //每段的订单入住租金

        for( int i=0; i<HouseRents.length;i++) {

            if (HouseRents[i] >= 0 && HouseRents[i] < 1000) {
                arrHouse[0]++;
            } else if (HouseRents[i] >= 1000 && HouseRents[i] < 2000) {
                arrHouse[1]++;
            } else if (HouseRents[i] >= 2000 && HouseRents[i] < 3000) {
                arrHouse[2]++;
            } else if (HouseRents[i] >= 3000 && HouseRents[i] < 4000) {
                arrHouse[3]++;
            } else if (HouseRents[i] >= 4000 && HouseRents[i] < 5000) {
                arrHouse[4]++;
            } else if(HouseRents[i] >= 5000) {
                arrHouse[5]++;
            }
        }

        for( int i=0; i<OrderRents.length;i++) {

            if (OrderRents[i] >= 0 && OrderRents[i] < 1000) {
                arrOrder[0]++;
            } else if (OrderRents[i] >= 1000 && OrderRents[i] < 2000) {
                arrOrder[1]++;
            } else if (OrderRents[i] >= 2000 && OrderRents[i] < 3000) {
                arrOrder[2]++;
            } else if (OrderRents[i] >= 3000 && OrderRents[i] < 4000) {
                arrOrder[3]++;
            } else if (OrderRents[i] >= 4000 && OrderRents[i] < 5000) {
                arrOrder[4]++;
            } else if(OrderRents[i] >= 5000) {
                arrOrder[5]++;
            }
        }


        //Integer[] arr1 = new Integer[]{73, 200, 380, 350, 500, 290};
        //Integer[] arr2 = new Integer[]{50, 140, 280, 310, 420, 210};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arrHouse);
        String data2 = objectMapper.writeValueAsString(arrOrder);
        Integer year1 = date1.getYear()+1900;
        Integer year2 = date2.getYear()+1900;

        Integer month1 = date1.getMonth()+1;
        Integer month2 = date2.getMonth()+1;
        //String season = date1.getMonth()/3;

        model.addAttribute("year1", year1);
        model.addAttribute("year2", year2);
        model.addAttribute("month1", month1);
        model.addAttribute("month2", month2);
        //model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "/chart/echartsRadar";
    }

    //统计每季度房屋来源占比
    @RequestMapping("/chart/chartBars")
    public String chartBars( Model model) throws JsonProcessingException {

        //Date date1 = new Date(117,5,1);
        //Date date2 = new Date(117,8,1);

        Date date2 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(cal.MONTH, -3);
        Date date1 = cal.getTime();

        Integer[] adminNum = chartService.findAdminDayNum(date1,date2);    //查询每个的房源发布的租金
        Integer[] UserNum = chartService.findUserDayNum(date1,date2);    //查询每个的订单的租金

        Integer[] arrHouse = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};  //每周的房源发布数目
        Integer[] arrOrder = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};  //每周的订单入住数目

        int m = -1;
        for( int i=0; i<adminNum.length;i++){
            if(i%7==0){
                m++;
                if(m>=12){
                    break;
                }
            }
            arrHouse[m] = arrHouse[m]+adminNum[i];
        }
        int n = -1;
        for( int i=0; i<UserNum.length;i++){
            if(i%7==0&&n<12){
                n++;
                if(n>=12){
                    break;
                }
            }
            arrOrder[n] += UserNum[i];
        }


        //Integer[] arr1 = new Integer[]{320, 302, 301, 334, 390, 330, 320, 302, 301, 334, 390, 330};
        //Integer[] arr2 = new Integer[]{120, 132, 101, 134, 90, 230, 210, 302, 301, 334, 390, 330};

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String data1 = objectMapper.writeValueAsString(arrHouse);
        String data2 = objectMapper.writeValueAsString(arrOrder);
        Integer year1 = date1.getYear()+1900;
        Integer year2 = date2.getYear()+1900;

        Integer month1 = date1.getMonth()+1;
        Integer month2 = date2.getMonth()+1;
        //String season = date1.getMonth()/3;

        model.addAttribute("year1", year1);
        model.addAttribute("year2", year2);
        model.addAttribute("month1", month1);
        model.addAttribute("month2", month2);
        //model.addAttribute("season", season);
        model.addAttribute("data1", data1);
        model.addAttribute("data2", data2);

        return "/chart/echartsBars";
    }



}
