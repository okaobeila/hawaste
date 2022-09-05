package com.gec.hawaste.controller;

import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.utils.ResponseStatus;
import com.gec.hawaste.utils.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainContrpller {

    @PostMapping("/doLogin")
    @ResponseBody
    public ResultBean doLogin(@RequestBody Map<String,Object> params, HttpSession session){

        String username = (String) params.get("username");
        String password = (String) params.get("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if (subject.isAuthenticated()){
            SysUser sysUser = (SysUser) subject.getPrincipal();
            session.setAttribute("loginUser",sysUser);

            HashMap<String,Object> map = new HashMap<>();
            map.put("loginUser",sysUser);
            return ResultBean.ok(map);
        }
        return ResultBean.fail(ResponseStatus.USERNAME_PASS_ERROR);

    }

    @GetMapping("/logout")
    public String logout(){
        //SecurityUtils.getSubject().logout();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        System.out.println("退出11111111111111111111111");
        return "redirect:login.html";
    }

}
