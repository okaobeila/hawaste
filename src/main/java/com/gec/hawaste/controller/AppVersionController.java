package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.entity.AppVersion;
import com.gec.hawaste.service.IAppVersionService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/app")
public class AppVersionController {

    @Autowired
    private IAppVersionService iAppVersionService;

    //App管理
    @GetMapping("/select")
    public ResultBean<Page> select(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        PageInfo<AppVersion> pageInfo = iAppVersionService.page(new PageInfo<AppVersion>(current,size));
        //设置分页导航栏数据
        System.out.println(pageInfo);
        pageInfo.setNavigatePage();
        return ResultBean.ok(pageInfo);
    }
   /* @GetMapping("select")
    public ResultBean<Page> select(@RequestParam("current") Integer current, @RequestParam("size") Integer size){


        PageInfo<AppVersion> pageInfo= iAppVersionService.page(new PageInfo<AppVersion>(current,size));

        //设置分页对象的导航栏
        pageInfo.setNavigatePage();
        return ResultBean.ok(pageInfo);

    }*/

    /**
     * 根据唯一标识查询单条记录（编辑时查询）
     * @param id
     * @return
     */
    @GetMapping("/selectOne")
    public ResultBean<AppVersion> selectOne(Long id){
        AppVersion app = iAppVersionService.getById(id);
        System.out.println("编辑的App："+app);
        return ResultBean.ok(app);
    }


    @RequestMapping("/saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody AppVersion app){

        System.out.println("app唯一标识："+app.getId());
        //根据id唯一标识判断添加还是修改
        iAppVersionService.saveOrUpdate(app);

        return ResultBean.ok();
    }

    @GetMapping("/delete")
    public ResultBean delete(Long id){
        System.out.println("删除的id："+id);
        iAppVersionService.removeById(id);
        return ResultBean.ok();
    }

}
