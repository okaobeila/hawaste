package com.gec.hawaste.juntil;



import com.gec.hawaste.custom.ExamineDo;
import com.gec.hawaste.custom.WorkOrderDo;
import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.service.IExamineService;
import com.gec.hawaste.service.ISysUserService;
import com.gec.hawaste.service.IWorkOrderService;
import com.gec.hawaste.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Test {
    @Autowired
    private IExamineService examineService;

    @Autowired
    private IWorkOrderService iWorkOrderService;

    @Autowired
    private ISysUserService iSysUserService;

    @org.junit.jupiter.api.Test
    public void test1(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("officeId",56);
        params.put("type",1);
        params.put("name","人员");
        PageInfo<ExamineDo> page = new PageInfo<>(1,5);
        PageInfo<ExamineDo> pageInfo = (PageInfo<ExamineDo>) examineService.selectByCondition(page,params);
        pageInfo.getRecords().forEach(examineDo -> {
            System.out.println(examineDo);
        });
    }

    @org.junit.jupiter.api.Test
    public  void test2(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("status",1);
        params.put("startDate","2016-01-01");
        params.put("endDate","2016-12-31");
        params.put("officeId",56);

        PageInfo<WorkOrderDo> pageInfo = (PageInfo<WorkOrderDo>) iWorkOrderService.selectByCondition(new PageInfo<>(1,5),params);
        pageInfo.getRecords().forEach(workOrderDo->{
            System.out.println(workOrderDo);
        });
    }

    @org.junit.jupiter.api.Test
    public void test3(){
        System.out.println(iWorkOrderService.selectDetailById(4));
    }

    @org.junit.jupiter.api.Test
    public void test4(){
        List<SysUser> list1 = iSysUserService.selectByRid(24l);
        list1.forEach(sysUser -> {
            System.out.println("list1");
            System.out.println(sysUser);
        });

        List<SysUser> list2 = iSysUserService.selectNoRole(52l,24l);
        list2.forEach(sysUser -> {
            System.out.println("list2");
            System.out.println(sysUser);
        });
    }

}
