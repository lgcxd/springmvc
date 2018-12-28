package com.cbb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LanJie
 * @Author: ChenBJ
 * @Description: TODO 自定义拦截器
 * @Date: 2018/7/5 15:59
 * @Version:
 */
public class LanJie implements HandlerInterceptor{
    /*目标方法前执行
    * 返回值为fale  后续的拦截器就不会被调用 目标方法也不会执行
    *
    * 用途:权限 日志 事物*/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("[LanJie] preHandle");
        return true;
    }
    /*目标方法之后渲染方法前被调用
    *  用途:对请求域中的值的属性做修改*/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("[LanJie] postHandle");

    }
    /*渲染视图的后被调用
    * 释放资源*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("[LanJie] afterCompletion");

    }
}
