package com.fy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	//转向欢迎页面
	@RequestMapping("/home")
	public String home() {
		return "/home/fmain";
	}


	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title() {
		return "/home/title";
	}


	@RequestMapping("/{moduleName}/Left")
	public String moduleLeft(@PathVariable String moduleName) {
		return "/" + moduleName + "/left";
	}

	@RequestMapping("/{moduleName}/Main")
	public String moduleMain(@PathVariable String moduleName) {
		return "/" + moduleName + "/main";
	}

}
