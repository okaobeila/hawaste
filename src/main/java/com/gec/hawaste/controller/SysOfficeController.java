package com.gec.hawaste.controller;


import com.gec.hawaste.entity.SysOffice;
import com.gec.hawaste.service.ISysOfficeService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/office")
public class SysOfficeController {
    @Autowired
    private ISysOfficeService sysOfficeService;

    @GetMapping("/selectAll")
    public ResultBean<SysOffice> selectAll(){
        List<SysOffice> list = sysOfficeService.list();
        System.out.println(list);
        return ResultBean.ok(list);
    }
}
