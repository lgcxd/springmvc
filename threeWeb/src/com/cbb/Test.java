package com.cbb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName Test
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/7/6 11:43
 * @Version:
 */
@Controller
public class Test {
    @Autowired
    private UserService userService;
    public Test(){
        System.out.println("test.........");
    }
    @RequestMapping("/testHello")
    public String testHello(){
        System.out.println("testHello");
        System.out.println(userService);
        return "success";
    }
}
