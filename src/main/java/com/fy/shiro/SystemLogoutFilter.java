package com.fy.shiro;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

public class SystemLogoutFilter extends LogoutFilter{

	@Override
	protected boolean preHandle(ServletRequest req, ServletResponse res) throws Exception {
		Subject subject=getSubject(req, res);
		String redirectUrl=getRedirectUrl(req, res, subject);
		try {
			subject.logout();

		} catch (Exception e) {
			e.printStackTrace();
		}
		issueRedirect(req, res, redirectUrl);
		return false;
	}
}
