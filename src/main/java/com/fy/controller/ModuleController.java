package com.fy.controller;

import com.fy.pojo.Module;
import com.fy.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String findAll(Model model){

        List<Module> hhModuleList=moduleService.findAll();
         model.addAttribute("hhModuleList",hhModuleList);
        return "/sysadmin/module/jModuleList";
    }


    @RequestMapping("/start")
    public String toStart(@RequestParam(value = "hhModuleId",required = true) String[] hhModuleIds){
            int hhModuleState=1;
        moduleService.UpdateState(hhModuleIds,hhModuleState);
        return "redirect:/sysadmin/module/list";
    }

    @RequestMapping("/stop")
    public String toStop(@RequestParam(value = "hhModuleId",required = true) String[] hhModuleIds){
        int hhModuleState=0;
        moduleService.UpdateState(hhModuleIds,hhModuleState);
        return "redirect:/sysadmin/module/list";
    }

    @RequestMapping("/tocreate")
    public String toCreate(Model model){


        List<Module> parentList = moduleService.findAll();
        model.addAttribute("parentList", parentList);



        return "/sysadmin/module/jModuleCreate";
    }


    @RequestMapping("/save")
    public String saveModule(Module module){

        moduleService.saveModule(module);

        return "redirect:/sysadmin/module/list";
    }


    @RequestMapping("/delete")
    public String Delete(@RequestParam(value = "hhModuleId",required = true) String[] hhModuleIds){

        moduleService.deleteModule(hhModuleIds);

        return "redirect:/sysadmin/module/list";
    }


}
