package com.fy.shiro;

import com.fy.tools.MD5HashPassword;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


public class AuthCredential extends  SimpleCredentialsMatcher{
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken loginToken=(UsernamePasswordToken) token;

		String username = loginToken.getUsername();

		String password = String.valueOf(loginToken.getPassword());

		password= MD5HashPassword.getPassword(username, password);

		loginToken.setPassword(password.toCharArray());
		return super.doCredentialsMatch(token,info);
	}
}
