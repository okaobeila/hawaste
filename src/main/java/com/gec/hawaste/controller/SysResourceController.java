package com.gec.hawaste.controller;


import com.gec.hawaste.entity.SysResource;
import com.gec.hawaste.service.ISysResourceService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/menu")
public class SysResourceController {

    @Autowired
    ISysResourceService resourceService;

    @RequestMapping("list")
    public ResultBean<List<SysResource>> select(){
        return ResultBean.ok(resourceService.list());
    }

}
