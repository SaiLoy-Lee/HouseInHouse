package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fy.pojo.HouseInfo;
import com.fy.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/18.
 */
@Controller
public class FrontController {

    @Autowired
    private FrontService frontService;


    @RequestMapping("/houseInfo")
    @ResponseBody
    public String findHouseInfoById(String hhHouseId){

        HouseInfo houseInfo = frontService.findHouseInfoById(hhHouseId);

        ObjectMapper objectMapper =new ObjectMapper();
        String jsonStr="";
        try {
            jsonStr=objectMapper.writeValueAsString(houseInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;

    }


}
