package com.fy.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/13.
 */
public class SystemLogoutFilter extends LogoutFilter {
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject=getSubject(request,response);
        String redirectUrl=getRedirectUrl(request,response,subject);
        HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session = req.getSession();
        try {
            subject.logout();
            session.invalidate();
        }catch (Exception e){
            e.printStackTrace();
        }
        issueRedirect(request,response,redirectUrl);
        return false;
    }
}
