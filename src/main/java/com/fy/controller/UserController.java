package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fy.pojo.Dept;
import com.fy.pojo.Role;
import com.fy.pojo.User;
import com.fy.service.DeptService;
import com.fy.service.RoleService;
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
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/list")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "/sysadmin/user/jUserList";
    }
   @RequestMapping("/start")
    public String toStart(@RequestParam(value = "hhUserId",required = true)String[] hhUserIds ){
        int hhUserStatus = 1;
        userService.updateStatus(hhUserIds,hhUserStatus);
        return "redirect:/sysadmin/user/list";
    }
    @RequestMapping("/stop")
    public String toStop(@RequestParam(value = "hhUserId",required = true)String[] hhUserIds ){
        int hhUserStatus = 0;
        userService.updateStatus(hhUserIds,hhUserStatus);
        return "redirect:/sysadmin/user/list";
    }
    @RequestMapping("/delete")
    public String toDelete(@RequestParam(value = "hhUserId",required=true)String[] hhUserIds){
        userService.deleteUser(hhUserIds);
        return "redirect:/sysadmin/user/list";
    }
    @RequestMapping("/tocreate")
    public String toCreate(Model model){
        List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);
        return "/sysadmin/user/jUserCreate";
    }
    @RequestMapping("/save")
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/sysadmin/user/list";
    }
    @RequestMapping("/toupdate")
    public String toUpdate(@RequestParam(required = true) String hhUserId,Model model){

        //查询需要修改的数据  表示当前需要修改的数据
        User user = userService.findUserById(hhUserId);
        //准备部门列表信息
        List<Dept> deptList = deptService.findAll();
       // List<UserInfo> parentList = userService.findParentList();

        model.addAttribute("user", user);
        model.addAttribute("deptList", deptList);
       // model.addAttribute("parentList", parentList);
        return "/sysadmin/user/jUserUpdate";
    }

    @RequestMapping("/update")
    public String updateUser(User user){

        userService.updateUser(user);
        return "redirect:/sysadmin/user/list";
    }
    @RequestMapping("/toview")
    public String toView(@RequestParam(required = true)String hhUserId,Model model){
        User user = userService.findUserById(hhUserId);
        model.addAttribute("user",user);
        return "/sysadmin/user/jUserView";

    }
    @RequestMapping("/checkUsernameAjax")
    @ResponseBody
    public String toCheck(String hhUserUsername){
      User user= userService.findUserByUsername(hhUserUsername);
        System.out.println(user);
        if (user != null){

            return "false";
        }else{
            return "true";
        }


    }

    @RequestMapping("/userRole")
    public String userRole(@RequestParam(required = true)String hhUserId,Model model) throws JsonProcessingException {
        List<String> uRoleList = userService.findRoleIdList(hhUserId);
        List<Role> roleList = roleService.findAll();
        for (Role role : roleList) {
            if(uRoleList.contains(role.getHhRoleId())){
                role.setChecked(true);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();

        //将list集合转化为JSON串
        String zTreeJSON = objectMapper.writeValueAsString(roleList);

        model.addAttribute("zTreeJSON", zTreeJSON);
        model.addAttribute("hhUserId", hhUserId);
        return "/sysadmin/user/jUserRole";
    }
    //保存用户角色信息
    @RequestMapping("/saveUserRole")
    public String saveUserRole(String hhUserId,String[] hhRoleIds){

        userService.saveUserRole(hhUserId,hhRoleIds);

        //跳转到用户列表页面
        return "redirect:/sysadmin/user/list";
    }
}
