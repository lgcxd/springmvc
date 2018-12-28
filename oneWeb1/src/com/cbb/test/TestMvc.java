package com.cbb.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName TestMvc
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/7/27 13:37
 * @Version:
 */
@Controller
public class TestMvc {
    @RequestMapping(value = "/testMethod",method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("测试固定方法的方法");
        return "success";
    }
    /**
     * @Author: ChenBJ
     * @Description:  简单的测试mvc
     * @Date: 2018/7/27 14:12
     * @Param:
     * @return:
     */
    @RequestMapping("/helloTest")
    public String testHello(){
        System.out.println("测试成功1");
        return "success";
    }
}
