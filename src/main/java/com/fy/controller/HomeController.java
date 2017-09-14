package com.fy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
/*	<property name="prefix" value="/WEB-INF/pages/" />
	<property name="suffix" value=".jsp" />*/
	
	//url:home.action
	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		//经过视图解析器拼接路径   真实路径:/WEB-INF/pages/home/fmain.jsp
		return "/home/fmain";
	}
	
	
	
	//title.action
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	
	//homeLeft.action
	//转向home的左侧页面
	/*@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}*/
	
	
	//@PathVariable(value="moduleName") String moduleName restFul结构传值问题
	@RequestMapping("/{moduleName}/Left")
	public String moduleLeft(@PathVariable String moduleName){
		
		return "/"+moduleName+"/left";
	}
	
	@RequestMapping("/{moduleName}/Main")
	public String moduleMain(@PathVariable String moduleName){
		
		return "/"+moduleName+"/main";
	}
	
	
	
	
	//sysadminLeft.action
	/*@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}
	
	@RequestMapping("/cargoLeft")
	public String cargoLeft(){
		return "/cargo/left";
	}
	
	@RequestMapping("/cargoMain")
	public String cargoMain(){
		return "/cargo/main";
	}*/
	
	
	
}
