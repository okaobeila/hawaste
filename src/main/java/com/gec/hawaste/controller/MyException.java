package com.gec.hawaste.controller;

import com.gec.hawaste.utils.ResultBean;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {

    @ExceptionHandler({IncorrectCredentialsException.class, UnknownAccountException.class})
    public ResultBean handlerException1(Exception e){
        ResultBean error = ResultBean.error();
        error.setMsg("账号或密码错误");
        return error;
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultBean handlerException2(AuthorizationException e){
        ResultBean error = ResultBean.error();
        error.setMsg("该用户无权访问该功能");
        return error;
    }
}
