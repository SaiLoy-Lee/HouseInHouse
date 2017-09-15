package com.fy.shiro;


import com.fy.pojo.User;
import com.fy.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class AuthRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		List<String> pList=new ArrayList<String>();
		pList.add("模块控制");
		pList.add("");


		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(pList);

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uPToken=(UsernamePasswordToken) token;
		String username = uPToken.getUsername();
		User user=new User();
		user.setHhUserName("大司马");
		AuthenticationInfo info=new SimpleAuthenticationInfo(user, "admin", this.getName());
		return info;

	}
}
