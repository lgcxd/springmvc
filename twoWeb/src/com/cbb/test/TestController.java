package com.cbb.test;

import com.cbb.bean.Employee;
import com.cbb.dao.EmployeeDao;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName TestController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/7/2 10:44
 * @Version:
 */
@Controller
public class TestController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ResourceBundleMessageSource message;

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i")int i){
        String[] val = new String[10];
        System.out.println("testSimpleMappingExceptionResolver.."+val[i]);
        return "success";
    }
    /*
    加上method = RequestMethod.POST 就通过不了 */
    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver(){
        System.out.println("testDefaultHandlerExceptionResolver..");
        return "success";
    }

    /*
    * @ResponseStatus NOT_FOUND 4O4
    * 注解使用正常值 也是会报404 因为在方法上标识了*/
    @ResponseStatus(reason = "测试",value =HttpStatus.NOT_FOUND )
    @RequestMapping("/tesResponseStatusExceptionResolver")
    public String tesResponseStatusExceptionResolver(@RequestParam("i")int i){
        if (i==13){
            throw new UserNameNotMatchPasseordException();
        }
        System.out.println("tesResponseStatusExceptionResolver.......");
        return "success";
    }

    /*@ExceptionHandler({RuntimeException.class})
    public ModelAndView handlerException2(Exception ex){
        System.out.println("[出异常了]"+ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("excption",ex);
        return mv;
    }*/
    /*1.在@ExceptionHandler方法的入参中可以加入Exception 类型的参数,该参数即对应发生的异常对象
    * 2.在@ExceptionHandler方法的入参中不能传入map 若希望把异常信息传到页面上,需要使用ModeAndView 作为返回值
    * 3.在@ExceptionHandler方法标记的异常优先级问题
    * 4.@ControllerAdvice:如果当前Handler中找不到@ExceptionHandler方法来出来当前方法出现的异常,
    * 则将去@ControllerAdvice标记的类中查找来处理异常*/
    /*@ExceptionHandler({ArithmeticException.class})
    public String handlerException(Exception ex){
        System.out.println("出异常了"+ex);
        return "error";
    }*/
//    @RequestMapping("/testException")
//    public String testException(@RequestParam("i")int i){
//        System.out.println("result:"+(10/i));
//        return "success";
//    }

    /*MultipartFile只有它能做上传 httpMessageRevler不能 他可以做下载*/
    @RequestMapping("/testFileUpload")
    public String uploadFile(@RequestParam(value = "desc",required = false)String desc, @RequestParam("file")MultipartFile multipartFile) throws IOException {
        System.out.println(desc+":"+multipartFile.getOriginalFilename());//文件名
        System.out.println("输入流"+multipartFile.getInputStream());
        return "success";
    }
    /*国际化相关*/
    @RequestMapping("/i18n")
    public String i18n(Locale locale){
        String val = message.getMessage("i18n.user",null, locale);
        System.out.println(val);
        return "i18n";
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResposeEntity(HttpSession session){
        byte[] body= null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/备份.txt");
        try {
            body = new byte[in.available()];
            in.read(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename=备份.txt");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body,httpHeaders,status);
        return response;
    }

    @RequestMapping("/testHttpMessageConverter")
    @ResponseBody
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "helloworld!"+new Date();
    }

    /* 测试json*/
    @RequestMapping("/testJson")
    @ResponseBody
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }

    /* 自定义类型转换器 执行一个添加操作*/
    @RequestMapping("/testConversionServiceConverer")
    public String testConverter(@RequestParam("employee") Employee employee){
        System.out.println("save:"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
