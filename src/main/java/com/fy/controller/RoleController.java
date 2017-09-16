package com.fy.controller;

import com.fy.pojo.Role;
import com.fy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findAll(Model model){
       List<Role> hhRoleList=roleService.findAll();
        model.addAttribute("hhRoleList",hhRoleList);
        return "/sysadmin/role/jRoleList";
    }

    @RequestMapping("/delete")
    public String deleteRole(@RequestParam(value = "hhRoleId",required = true) String[]hhRoleIds){

        roleService.deletehhRoleId(hhRoleIds);

        return "redirect:/sysadmin/role/list";

    }

    @RequestMapping("/start")
    public String Start(@RequestParam(value ="hhRoleId" ,required=true) String[]hhRoleIds,int hhRoleStatus){
         hhRoleStatus =1;
        roleService.toStart(hhRoleIds,hhRoleStatus);
         return "redirect:/sysadmin/role/list";
    }

    @RequestMapping("/stop")
    public String Stop(@RequestParam(value ="hhRoleId" ,required=true) String[]hhRoleIds,int hhRoleStatus){
        hhRoleStatus =0;
        roleService.toStop(hhRoleIds,hhRoleStatus);
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

    @RequestMapping("/toupdate")
    public String updateRole(String hhRoleId,Model model){

       Role role=roleService.updateRole(hhRoleId);
        model.addAttribute("role",role);

        return "/sysadmin/role/jRoleUpdate";
    }


    @RequestMapping("/update")
    public  String update(Role role){

           roleService.update(role);
        return "redirect:/sysadmin/role/list";
    }

    @RequestMapping("/toview")
    public  String toView(String hhRoleId,Model model){
         Role role=roleService.toview(hhRoleId);
          model.addAttribute("role",role);
        return "/sysadmin/role/jRoleView";
    }







}
