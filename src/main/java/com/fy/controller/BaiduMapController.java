package com.fy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ryp on 2017/9/16.
 */

@Controller
public class BaiduMapController {

    @RequestMapping("/openMap1")
    public String openMap1(Model model){
        return "/baidumap/map1";
    }

    @RequestMapping("/openMap2")
    public String openMap2(Model model){
        return "/baidumap/map2";
    }


}
