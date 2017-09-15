package com.fy.controller;

import com.fy.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
public class LoginController {
    @RequestMapping("/tologin")
    //@ResponseBody
    public  String toLogin(){

        return "/sysadmin/login/login";
    }


    @RequestMapping("/login")
    public String login(String userName, String password, Model model){


        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            model.addAttribute("errorInfo","用户名或密码不能为空");
            return "/sysadmin/login/login";

        }

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);

        try{
            subject.login(token);

            User user = (User) subject.getPrincipal();
            Session session = subject.getSession();
            session.setAttribute("SessionUser",user);

            return "/home/fmain";

        } catch (AuthenticationException a){
            a.printStackTrace();
            model.addAttribute("errorInfo","用户名或密码错误");
            return "/sysadmin/login/login";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorInfo","未知错误，请联系管理员");
            return "/sysadmin/login/login";
        }
    }

}
