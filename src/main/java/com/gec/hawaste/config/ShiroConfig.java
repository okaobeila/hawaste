package com.gec.hawaste.config;

import com.gec.hawaste.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    // 加密器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        // 加密算法
        matcher.setHashAlgorithmName("md5");
        // 散列次数
        matcher.setHashIterations(3);
        return matcher;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 MyRealm，
     */
    @Bean
    public MyRealm myRealm(HashedCredentialsMatcher credentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    //DefaultWebSecurityManager shiro的核心管理器 注意设置bean名字  ShiroRedisson组件已自动创建
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);// 设置realm
        return securityManager;
    }

    // 配置ShiroFilter  过滤器链配置
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //过滤器链按 从上向下顺序执行，一般将 /**放在最为下边
        // 认证 放行
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/doLogin", "anon");
        chainDefinition.addPathDefinition("/login.html", "anon");
        chainDefinition.addPathDefinition("/notlogin.html", "anon");
        chainDefinition.addPathDefinition("/ace/**", "anon");
        chainDefinition.addPathDefinition("/bootstrap/**", "anon");
        chainDefinition.addPathDefinition("/chosen/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/fonts/**", "anon");
        chainDefinition.addPathDefinition("/images/**", "anon");
        chainDefinition.addPathDefinition("/img/**", "anon");
        chainDefinition.addPathDefinition("/jquery/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/layer/**", "anon");
        chainDefinition.addPathDefinition("/switch/**", "anon");
        chainDefinition.addPathDefinition("/ueditor/**", "anon");
        chainDefinition.addPathDefinition("/uploads/**", "anon");
        chainDefinition.addPathDefinition("/validate/**", "anon");
        chainDefinition.addPathDefinition("/vue/**", "anon");
        chainDefinition.addPathDefinition("/ztree/**", "anon");
        //放行 aj请求
        chainDefinition.addPathDefinition("/captcha/**", "anon");
        chainDefinition.addPathDefinition("/checkCaptcha", "anon");
        //其他请求要认证  通过注解认证
        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }

}
