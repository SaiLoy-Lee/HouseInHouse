package com.fy.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fy.service.OrderService;
import com.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    //SHD此外还需要自动注入房子的service层

    //转向订单确认页面
    @RequestMapping("/tocreateorder")
    public String toCreateOrder(String vUserId, String vRoomId, Model model, HttpSession session) {
//       if(session.getAttribute("User")==null){
//            return "redirect:login";
//        };
        //User user= userService.findUserById(vUserId);

        // 此外还需要调用房子services查询房子信息

        // model.addAttribute("user",user);
        //model.addAttribute("",)

        return "/sysadmin/order/OrderCreate";
    }

//    @RequestMapping("/sysadmin/order/getImgUrl/${ImgUrl}")
//    public void getImgUrl(@Validated String ImgUrl , HttpServletResponse response) {
//        File img=new File(ImgUrl);
////        FileInputStream is= new FileInputStream(img)
////       OutputStream os= response.getOutputStream();
////        os.close();
//    }
    @RequestMapping("/sysadmin/order/getVerfiy/{mobile}")
    @ResponseBody
    public String getVerfy(@PathVariable String mobile){
        System.out.print(mobile);

        return "1";
    }
}
