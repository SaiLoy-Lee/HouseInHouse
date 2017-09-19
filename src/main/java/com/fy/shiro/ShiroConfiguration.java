package com.fy.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/9/14.
 */

@Configuration
public class ShiroConfiguration {


    @Bean(name = "AuthRealm")
    public AuthRealm getShiroRealm() {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(authCredential());
        return authRealm;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }



    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(getShiroRealm());
        return dwsm;
    }
    /*@Bean(name = "logoutFilter")
    public LogoutFilter getLogoutFilter(){
       SystemLogoutFilter logoutFilter=new SystemLogoutFilter();
        logoutFilter.setRedirectUrl("/index");
        return logoutFilter;
    }*/

    @Bean
    public AuthCredential authCredential(){
        AuthCredential authCredential=new AuthCredential();
        return authCredential;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/index.jsp");

        Map<String, Filter> filterChains = new LinkedHashMap<String, Filter>();
        SystemLogoutFilter logoutFilter = new SystemLogoutFilter();
        logoutFilter.setRedirectUrl("/index.html");
        filterChains.put("logout",logoutFilter);
        shiroFilterFactoryBean.setFilters(filterChains);

        //filterChainDefinitionMap.put("","anon");
        filterChainDefinitionMap.put("/*.html","anon");
        filterChainDefinitionMap.put("/*/*.js","anon");
        filterChainDefinitionMap.put("/*/*.jpg","anon");
        filterChainDefinitionMap.put("/*/*.png","anon");
        filterChainDefinitionMap.put("/*/*.ttf","anon");
        filterChainDefinitionMap.put("/*/*.eot","anon");
        filterChainDefinitionMap.put("/*/*.svg","anon");
        filterChainDefinitionMap.put("/*/*.woff","anon");
        filterChainDefinitionMap.put("/*/*.woff2","anon");
        filterChainDefinitionMap.put("/css/*.css","anon");

        filterChainDefinitionMap.put("/staticfile/**", "anon");
        filterChainDefinitionMap.put("/tologin.action","anon");

        filterChainDefinitionMap.put("/login.action", "anon");
        filterChainDefinitionMap.put("/logout*","logout");

        filterChainDefinitionMap.put("/", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
