package com.fy.shiro;


import com.fy.mapper.OrderMapper;
import com.fy.pojo.Role;
import com.fy.pojo.User;
import com.fy.service.OrderService;
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
	@Autowired
	private UserService userService;
	@Autowired
	private OrderMapper orderMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		User user=(User) pc.getPrimaryPrincipal();
		String userId=user.getHhUserId();
		List<Role> rList=orderMapper.findRolesByUserId(userId);
		List<String> pList=new ArrayList<String>();
		for (Role role:rList) {
			pList.add(role.getHhRoleName());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(pList);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uPToken=(UsernamePasswordToken) token;
		String username = uPToken.getUsername();
		User user=userService.findUserByUsername(username);
		AuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getHhUserPassword(), this.getName());
		return info;

	}
}
