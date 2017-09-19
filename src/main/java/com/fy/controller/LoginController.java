package com.fy.controller;

import com.fy.mapper.OrderMapper;
import com.fy.pojo.Role;
import com.fy.pojo.User;
import com.fy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/tologin")
    //@ResponseBody
    public  String toLogin(){

        return "/sysadmin/login/login";
    }

    @RequestMapping("/ajax/login")
    @ResponseBody
    public String toAjax(HttpSession session){
        User user = (User) session.getAttribute("SessionUser");
        if(user==null){
            return "<a  href='/tologin.action'><i class='glyphicon glyphicon-user'>登录</i>";
        }else{
            return " <a  href='/logout'>"+user.getHhUserName()+" | 退出</a> <a href='/home'> | 后台</a>";
        }
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
            List<Role> roles = orderMapper.findRolesByUserId(user.getHhUserId());
            session.setAttribute("UserRole",roles);
            return "redirect:/index.html";

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
