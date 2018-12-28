package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName Hello
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/25 19:03
 * @Version:
 */
@Controller
public class Hello {
    /**
     * 1.使用@RequestMapping 注解来映射请求的url   除了修饰方法还可以修饰类
     * 2.返回值会通过视图解析器解析为实际的物理视图,对于InternalResourceViewResolver 视图,会做如下解析:
     * 通过prefix + returnVal + 后缀 这样的方式得到实际的物理视图,然后做转发操作
     * /WEB-INF/views/success.jsp
     *  @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("成功");
        return "success";
    }
}
