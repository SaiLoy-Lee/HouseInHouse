package com.fy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ryp on 2017/9/16.
 */

@Controller
public class BaiduMapController {

    @RequestMapping("/mapShow")
    public String mapShow(Model model){
        return "/baidumap/mapShow";
    }

    @RequestMapping("/mapOutdoorScene")
    public String mapOutdoorScene(Model model){
        return "/baidumap/mapOutdoorScene";
    }

    @RequestMapping("/mapLongitudeAndLatitudeFind")
    public String mapLongitudeAndLatitudeFind(Model model){
        return "/baidumap/mapLongitudeAndLatitudeFind";
    }

    @RequestMapping("/mapFindLongitudeAndLatitude")
    public String mapFindLongitudeAndLatitude(Model model){
        return "/baidumap/mapFindLongitudeAndLatitude";
    }

    @RequestMapping("/mapAAAA")
    public String mapAAAA(Model model){
        return "/baidumap/MapAAAA";
    }


}
