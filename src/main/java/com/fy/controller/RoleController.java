package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fy.pojo.Module;
import com.fy.pojo.Role;
import com.fy.service.ModuleService;
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

    @Autowired
    private ModuleService moduleService;

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
    public String Start(@RequestParam(value ="hhRoleId" ,required=true) String[]hhRoleIds){
         int hhRoleStatus =1;
        roleService.UpdateState(hhRoleIds,hhRoleStatus);
         return "redirect:/sysadmin/role/list";
    }

    @RequestMapping("/stop")
    public String Stop(@RequestParam(value ="hhRoleId" ,required=true) String[]hhRoleIds){
        int hhRoleStatus =0;
        roleService.UpdateState(hhRoleIds,hhRoleStatus);
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

    @RequestMapping("/roleModule")
    public String roleModule(String hhRoleId,Model model) throws JsonProcessingException {

        //2.根据roleId查询全部模块信息
        List<String> rModuleList = moduleService.findModuleListByRoleId(hhRoleId);

        //查询全部的模块信息
        List<Module> moduleList = moduleService.findAll();

        //全部模块信息列表
        for (Module module : moduleList) {

            if(rModuleList.contains(module.gethhModuleId())){
                module.setChecked(true);
            }

        }

        //将数据转化为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String zTreeJSON = objectMapper.writeValueAsString(moduleList);


        model.addAttribute("zTreeJSON", zTreeJSON);
        model.addAttribute("hhRoleId", hhRoleId);
        //跳转到模块展现页面
        return "/sysadmin/role/jRoleModule";
    }

    @RequestMapping("/saveRoleModule")
    public String saveRoleModule(String hhRoleId,String[] hhModuleIds){

        roleService.saveRoleModules(hhRoleId,hhModuleIds);

        //跳转到角色列表页面
        return "redirect:/sysadmin/role/list";
    }







}
