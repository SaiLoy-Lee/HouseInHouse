package com.fy.controller;

import com.fy.pojo.Role;
import com.fy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findAll(Model model){
       List<Role> hhroleList=roleService.findAll();
        model.addAttribute("hhroleList",hhroleList);
        return "/sysadmin/role/jRoleList";
    }

    @RequestMapping("/delete")
    public String deleteRole(@RequestParam(value = "hhroleId",required = true) String[]hhroleIds){

        roleService.deletehhroleId(hhroleIds);

        return "redirect:/sysadmin/role/list";

    }

    @RequestMapping("/start")
    public String Start(@RequestParam(value ="hhroleId" ,required=true) String[]hhroleIds,int hhroleStatus){
         hhroleStatus =1;
        roleService.toStart(hhroleIds,hhroleStatus);
         return "redirect:/sysadmin/role/list";
    }

    @RequestMapping("/stop")
    public String Stop(@RequestParam(value ="hhroleId" ,required=true) String[]hhroleIds,int hhroleStatus){
        hhroleStatus =0;
        roleService.toStop(hhroleIds,hhroleStatus);
        return "redirect:/sysadmin/role/list";
    }


   @RequestMapping("/tocreate")
    public String CreateRole(){

       return "/sysadmin/role/jRoleCreate";
    }

    @RequestMapping("/save")
    public String SaveRole(Role role){

        roleService.SaveRole(role);

        return "redirect:/sysadmin/role/list";
    }

}
