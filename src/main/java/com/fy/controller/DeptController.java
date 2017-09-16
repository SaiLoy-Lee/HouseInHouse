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
@RequestMapping("/sysadmin/dept")
public class DeptController extends BaseController{
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public String findAll(Model model){
        List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);
        return "/sysadmin/dept/jDeptList";

    }

    @RequestMapping("/delete")
    public String toDelete(@RequestParam(value="hhDeptId",required=true)String[] hhDeptIds){
        deptService.deleteDept(hhDeptIds);
        return "redirect:/sysadmin/dept/list";

    }
    @RequestMapping("/start")
    public String toStart(@RequestParam(value="hhDeptId",required=true)String[] hhDeptIds){
        int hhDeptStatus = 1;
        deptService.updateStatus(hhDeptIds, hhDeptStatus);
        return "redirect:/sysadmin/dept/list";
    }

    @RequestMapping("/stop")
    public String toStop(@RequestParam(value="hhDeptId",required=true)String[] hhDeptIds){
        int hhDeptStatus = 0;
        deptService.updateStatus(hhDeptIds, hhDeptStatus);
        return "redirect:/sysadmin/dept/list";
    }

    @RequestMapping("/tocreate")
    public String toCreate(Model model){
       /* List<Dept> deptList = deptService.findAll();
        model.addAttribute("deptList",deptList);*/
        return "/sysadmin/dept/jDeptCreate";
    }
    @RequestMapping("/save")
    public String saveDept(Dept dept){

       deptService.saveDept(dept);
        return "redirect:/sysadmin/dept/list";
    }

    @RequestMapping("toview")
    public String toView(@RequestParam(required = true)String hhDeptId,Model model){
        Dept dept = deptService.findDeptById(hhDeptId);
        model.addAttribute("dept",dept);
        return "/sysadmin/dept/jDeptView";

    }

    @RequestMapping("/toupdate")
    public String toUpdate(@RequestParam(value="hhDeptId",required=true)String hhDeptId,Model model){
        Dept dept = deptService.findDeptById(hhDeptId);
        //List<Dept> parentList = deptService.findParentList(hhDeptId);

        model.addAttribute("dept",dept);
        //model.addAttribute("parentList",parentList);

        return "/sysadmin/dept/jDeptUpdate";

    }
    @RequestMapping("/update")
    public String updateDept(Dept dept){
        deptService.updateDept(dept);
        return "redirect:/sysadmin/dept/list";

    }
}
