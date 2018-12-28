package com.cbb.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ExceptionHandlers
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/7/5 16:43
 * @Version:
 */
@ControllerAdvice
public class ExceptionHandlers {
    /*没有实现想要的效果 因为缺少东西 */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handelerException(Exception ex){
        System.out.println("........出异常了"+ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("excption",ex);
        return mv;
    }
}
