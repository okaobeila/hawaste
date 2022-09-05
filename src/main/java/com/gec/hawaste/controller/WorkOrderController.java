package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.custom.WorkOrderDetailDo;
import com.gec.hawaste.custom.WorkOrderDo;
import com.gec.hawaste.service.IWorkOrderService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/work")
public class WorkOrderController {

    @Autowired
    private IWorkOrderService workOrderService;

    @GetMapping("/select/{current}/{size}")
    @RequiresPermissions("center:select")
    public ResultBean<Page> select(@PathVariable Integer current,@PathVariable Integer size,@RequestParam Map<String,Object> params){
        //PageInfo<WorkOrderDo> pageInfo = (PageInfo<WorkOrderDo>) workOrderService.selectByCondition(new PageInfo<WorkOrderDo> (current,size),params);
        PageInfo<WorkOrderDo> pageInfo = (PageInfo<WorkOrderDo>) workOrderService.selectByCondition(new PageInfo<>(current,size),params);
        System.out.println(pageInfo);
        //System.out.println("=================================================\n=======================================================\n===========================================");
        pageInfo.setNavigatePage(); //设置分页导航栏数据
        return ResultBean.ok(pageInfo);
    }


    @GetMapping("/selectOne/{oid}")
    @RequiresPermissions("user:select")
    public ResultBean selectOne(@PathVariable Long oid){
        WorkOrderDetailDo workOrderDetailDo = workOrderService.selectDetailById(oid);
        return  ResultBean.ok(workOrderDetailDo);
    }

}
