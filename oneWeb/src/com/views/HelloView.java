package com.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName HelloView
 * @Author: ChenBJ
 * @Description: 自定义视图
 * @Date: 2018/6/28 14:59
 * @Version:
 */
@Component
public class HelloView implements View {
    @Override
    public String getContentType(){
        return "text/html";
    }
    @Override
    public void render(Map<String,?> model, HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().print("time"+new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
