package com.fy.controller;

import com.fy.pojo.Dept;
import com.fy.service.DeptService;
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
@RequestMapping("/pages/sysadmin/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public String findAll(Model model){
        List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);
        return "/pages/sysadmin/dept/jDeptList";

    }

    @RequestMapping("/delete")
    public String toDelete(@RequestParam(value="hhDeptId",required=true)String[] hhDeptIds){
        deptService.deleteDept(hhDeptIds);
        return "redirect:/pages/sysadmin/dept/list";

    }
    @RequestMapping("/start")
    public String toStart(@RequestParam(value="hhDeptId",required=true)String[] hhDeptIds){
        int hhDeptStatus = 1;
        deptService.updateState(hhDeptIds, hhDeptStatus);
        return "redirect:/pages/sysadmin/dept/list";
    }

    @RequestMapping("/stop")
    public String toStop(@RequestParam(value="hhDeptIds",required=true)String[] hhDeptIds){
        int hhDeptStatus = 0;
        deptService.updateState(hhDeptIds, hhDeptStatus);
        return "redirect:/pages/sysadmin/dept/list";
    }

    @RequestMapping("/tocreate")
    public String toCreate(Model model){
        List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);
        return "/pages/sysadmin/dept/jDeptCreate";
    }
    @RequestMapping("/save")
    public String saveDept(Dept dept){
        deptService.saveDept(dept);
        return "redirect:/pages/sysadmin/dept/list";
    }

    @RequestMapping("toview")
    public String toView(String hhDeptId,Model model){
        Dept dept = deptService.findDeptById(hhDeptId);
        model.addAttribute("dept",dept);
        return "/pages/sysadmin/dept/jDeptView";

    }

    @RequestMapping("/toupdate")
    public String toUpdate(@RequestParam(value="hhDeptId",required=true)String hhDeptId,Model model){
        Dept dept = deptService.findDeptById(hhDeptId);
        //List<Dept> parentList = deptService.findParentList(hhDeptId);

        model.addAttribute("dept",dept);
        //model.addAttribute("parentList",parentList);

        return "/pages/sysadmin/dept/jDeptUpdate";

    }
    @RequestMapping("/update")
    public String updateDept(Dept dept){
        deptService.updateDept(dept);
        return "redirect:/pages/sysadmin/dept/list";

    }
}
