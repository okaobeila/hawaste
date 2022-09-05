package com.gec.hawaste.controller;


import com.gec.hawaste.service.ISysUserService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/selectByRid/{rid}")
    public ResultBean selectByRid(@PathVariable Long rid){

        return ResultBean.ok(sysUserService.selectByRid(rid));
    }

    @RequestMapping("/selectNoRole/{rid}/{oid}")
    public ResultBean selectNoRole(@PathVariable Long rid,@PathVariable Long oid){
        return  ResultBean.ok(sysUserService.selectNoRole(rid, oid));
    }

}
