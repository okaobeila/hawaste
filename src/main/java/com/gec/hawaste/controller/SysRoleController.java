package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.custom.SysRoleDo;
import com.gec.hawaste.entity.SysRole;
import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.service.ISysRoleService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/select/{current}/{size}")
    public ResultBean select(@PathVariable Integer current, @PathVariable Integer size, @RequestParam Map<String,Object> params){
        PageInfo<SysRoleDo> page = (PageInfo<SysRoleDo>) sysRoleService.selectByCondition(new PageInfo<SysRoleDo>(current,size),params);
        page.setNavigatePage();
        System.out.println(page);
        return ResultBean.ok(page);
    }

    @PostMapping("/saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody SysRoleDo sysRole, HttpSession session){
        System.out.println(sysRole);
        sysRole.setCreateBy("gec");
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (sysRole.getId()==null){
            sysRole.setCreateBy(loginUser.getName());
        }
        //无异常直接返回，出异常由统一异常处理Controller处理返回
        //公共serviceImpl生成的添加或更新方法  有主键是更新，无主键值是添加
        sysRoleService.updateById(sysRole);
        return ResultBean.ok();
    }

    @GetMapping("/selectOne/{id}")
    public ResultBean selectOne(@PathVariable Long id){
        SysRoleDo sysRoleDo = sysRoleService.selectOne(id);
        return  ResultBean.ok(sysRoleDo);
    }

}
