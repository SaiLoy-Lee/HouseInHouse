package com.fy.shiro;


import com.fy.pojo.User;
import com.fy.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class AuthRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uPToken=(UsernamePasswordToken) token;
		String username = uPToken.getUsername();
		User user=userService.findUserByUsername(username);
		AuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;

	}
}
