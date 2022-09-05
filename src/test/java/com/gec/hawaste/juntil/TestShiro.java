package com.gec.hawaste.juntil;

import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestShiro {

    @Autowired
    private ISysUserService sysUserService;

    @Test
   public void test1(){
       List<SysUser> list = sysUserService.list();

       list.forEach(sysUser -> {
           String password = "111111";
           Md5Hash md5 = new Md5Hash(password,sysUser.getUsername(),3);
           System.out.println(sysUser.getUsername());
           System.out.println("第"+ sysUser.getUsername() +"次           ："+md5);
           sysUser.setPassword(md5.toString());
           sysUserService.saveOrUpdate(sysUser);
       });
   }

   @Autowired
    private DefaultWebSecurityManager defaultSecurityManager;

   @Test
   public void test2(){
       SecurityUtils.setSecurityManager(defaultSecurityManager);
       //获取主体
       Subject subject =  SecurityUtils.getSubject();
       UsernamePasswordToken token = new UsernamePasswordToken("jacky","111111");
       subject.login(token);

       System.out.println("是否是合法用户:"+subject.isAuthenticated());
       System.out.println("是否有权限user:select:"+subject.isPermitted("user:select"));

       subject.logout();
       System.out.println("是否是合法用户:"+subject.isAuthenticated());
   }
  /* @Autowired
   private DefaultWebSecurityManager securityManager;
    @Test
    public void test2(){

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("13500375617","111111");
        subject.login(token);

        System.out.println("是否是合法用户:"+subject.isAuthenticated());
        System.out.println("是否有权限user:select:"+subject.isPermitted("user:select"));

        subject.logout();
        System.out.println("是否是合法用户:"+subject.isAuthenticated());

    }*/
}
