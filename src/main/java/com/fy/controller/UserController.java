package com.fy.controller;

import com.fy.pojo.Dept;
import com.fy.pojo.User;
import com.fy.service.DeptService;
import com.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
@RequestMapping("/pages/sysadmin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @RequestMapping("/list")
    @ResponseBody
    public String findAll(Model model){
//        List<User> userList = userService.findAll();
//        model.addAttribute("userList",userList);
        return "/pages/sysadmin/user/jUserList";
    }
   @RequestMapping("/start")
    public String toStart(@RequestParam(value = "hhUserId",required = true)String[] hhUserIds ){
        int hhUserStatus = 1;
        userService.updateStatus(hhUserIds,hhUserStatus);
        return "redirect:/pages/sysadmin/user/list";
    }
    @RequestMapping("/stop")
    public String toStop(@RequestParam(value = "hhUserId",required = true)String[] hhUserIds ){
        int hhUserStatus = 0;
        userService.updateStatus(hhUserIds,hhUserStatus);
        return "redirect:/pages/sysadmin/user/list";
    }
    @RequestMapping("/delete")
    public String toDelete(@RequestParam(value = "hhUserId",required=true)String hhUserIds){
        userService.deleteUser(hhUserIds);
        return "redirect:/pages/sysadmin/user/list";
    }
    @RequestMapping("/tocreate")
    public String toCreate(Model model){
        List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);


        return "/pages/sysadmin/user/jUserCreate";
    }
    @RequestMapping("/save")
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/pages/sysadmin/user/list";
    }

}
