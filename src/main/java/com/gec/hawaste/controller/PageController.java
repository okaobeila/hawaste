package com.gec.hawaste.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    /**
     * 一级路径页面跳转、
     * @return
     */
    @GetMapping("{url}.html")
    public String module1(@PathVariable String url){
        return url;
    }

    /**
     * 二级路径跳转
     * @return
     */
    @GetMapping("{module}/{url}.html")
    public String module2(@PathVariable("module") String module,@PathVariable("url") String url){
        return module + "/" + url;
    }

    /**
     * 三级路径页面跳转  /manager/app/app.html
     * @return
     */
    @GetMapping("{module}/{classify}/{url}.html")
    public String module3(@PathVariable("module") String module,
                          @PathVariable("classify") String classify,
                          @PathVariable("url") String url) {
        return module + "/" +classify+ "/" + url;
    }

    /**
     * 三级路径页面跳转  /manager/app/app.html
     * @return
     */
    @GetMapping("{module}/{classify}/{classifys}/{url}.html")
    public String module4(@PathVariable("module") String module,
                          @PathVariable("classify") String classify,
                          @PathVariable("classifys") String classifys,
                          @PathVariable("url") String url) {
        return module + "/" +classify+ "/" + classifys +"/" +url;
    }

}
