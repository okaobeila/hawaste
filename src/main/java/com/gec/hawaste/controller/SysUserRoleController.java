package com.gec.hawaste.controller;


import com.gec.hawaste.service.ISysUserRoleService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/role")
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @RequestMapping("/deleteBatch")
    public ResultBean deleteBatch(Long rid,Long[] ids){
        return ResultBean.ok(sysUserRoleService.deleteBatch(rid, ids));
    }

    @RequestMapping("/insertBatch")
    private ResultBean insertBatch(Long rid,Long[] ids){
        return ResultBean.ok(sysUserRoleService.inserBatch(rid, Arrays.asList(ids)));
    }
}
