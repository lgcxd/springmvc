package com.test;

import com.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName TestClass
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/26 17:25
 * @Version:
 */
/*@SessionAttributes({"user"})*/
@SessionAttributes(value = {"user"},types = {String.class})
@RequestMapping("/test")
@Controller
public class TestClass {
    private static final String SUCCESS = "success";
    /*重定向或转发操作*/
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }
    /*自定义视图*/
    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView");
        return SUCCESS;
    }
    @RequestMapping("/testModelResolover")
    public String testModelResolover(){
        System.out.println("testModelResolover");
        return SUCCESS;
    }
    /*
    * 1.有@ModelAttribute 标记的方法,会在每个目标方法执行之前被SpringMvc调用
    * 2.@ModelAttribute注解也可以来修饰目标方法pojo类型的入参,其value属性值有如下的作用:
    *   springmvc 会使用value属性值在 implicitModel 中查找对应的对象,若存在则会直接传入目标方法
    *   springmvc 会以value 为key,pojo类型的对象为value,存入到request中*/
    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false)Integer id,Map<String,Object> map){
        System.out.println("modelAttribute method");
        if(id != null){
            //模拟从数据库中获取对象
            User user = new User(1,"ll","123","1@qq.com",12);
            System.out.println("从数据库中获取一个对象:"+user);
            map.put("user",user);
        }
    }
    /*
    * 运行流程:
    * 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user (类名首字母小写)
    * 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
    * 3. SpringMVC 把上述对象传入目标方法的参数.
    *
    * 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
    *SpringMVC 确定目标方法 POJO 类型入参的过程
	 * 1. 确定一个 key:
	 * 1). 若目标方法的 POJO 类型的参数没有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
	 * 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.
	 * 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
	 * 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到.
	 * 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,
	 * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
	 * 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
	 * 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
	 * 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
	 * 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中.
	 *
	 * 源代码分析的流程
	 * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
	 * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
	 * 1). 创建 WebDataBinder 对象:
	 * ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写.
	 * *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute
	 * 的 value 属性值
	 *
	 * ②. 确定 target 属性:
	 * 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
	 * 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
	 * 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常.
	 * 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
	 * 和 attrName 相匹配, 则通过反射创建了 POJO 对象
	 *
	 * 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性.
	 * 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel.
	 * 近而传到 request 域对象中.
	 * 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参.
     * */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user){
        System.out.println("updateUser"+user);
        return SUCCESS;
    }
    /*
    * @SessionAttributes 除了可以通过属性名指定需要放到会话中属性外(实际上使用的是value的属性值)
    * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际使用的是types属性值)
    *
    * 注意:该注解只能放在类上使用,不能修饰方法.*/
    /*@RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object> map){
        User user1 = new User("tome","ss","qq.com",12);
        map.put("user",user1);
        map.put("test","哈哈哈字符串");
        return SUCCESS;
    }*/
    /*常用:
    * 目标方法可以添加Map(也可以Model(接口)和ModelMap)类型的参数*/
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("Hha","xx","ss"));
        return SUCCESS;
    }
    /*
    * 目标方法的返回值可以是ModelAndView类型
    * 其中可以包含视图和模型信息
    * Springmvc 会把ModelAndView 中的model中的数据放入到request的域对象中
    * */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String name = SUCCESS;
        ModelAndView mv = new ModelAndView(name);
        mv.addObject("time",new Date());
        return mv;
    }
    /*
    * 可以使用Servlet原生的api作为方法的参数
    * 具体支持以下类型:
    * HttpServletRequest
    * HttpServletResponse
    * HttpSession
    * java.security.Principal
    * locale InputStream
    * OutputStream
    * Reader
    * Writer
    * */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer out){
        System.out.println("testServletAPI,"+request+"--"+response);
        try {
            out.write("springHello!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* 9. api */
   /* @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response){
        System.out.println("testServletAPI,"+request+"--"+response);
        return  SUCCESS;
    }*/
    /*
    * 使用pojo作为请求参数,它会自动的与pojo进行匹配,并且支持级联属性*/
    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println("testPojo"+user);
        return SUCCESS;
    }
    /*
     8. 不常用
    *@CookieValue 用法同@RequestParam
    * */
    @RequestMapping("/cookieValue")
    public String cookieValue(@CookieValue(value = "JSESSIONID")String Id){
        System.out.println("cookieValue--"+Id);
        return SUCCESS;
    }
    /*
     * 7. 了解即可
     * @RequestHeader 用法同@RequestParam */
    @RequestMapping(value = "/requestHeader")
    public String requestHeader(@RequestHeader(value = "Accept-Language")String al){
        System.out.println("requestHeader--"+al);
        return SUCCESS;
    }
    /*
    * 6. @RequestParam 来映射请求参数
    * value 值就是请求参数的参数名
    * required 设置该参数为是否必须,默认为true
    * defaultValue 当参数为非必须设置请求参数的默认是值为
    * */
    @RequestMapping(value = "/getRequestParames")
    public String getRequestParame(@RequestParam(value = "username")String un,
                                   @RequestParam(value = "age",required = false,defaultValue = "0") int age){
        System.out.println("getRequestParames" +un+"和"+age);
        return SUCCESS;
    }
    /* 5. rest 风格的url
    * 以CRUD为例:
    * 新增: /order POST
    * 修改: /order/1 PUT update?id=1
    * 获取: /order/1 GET get?id=1
    * 删除: /order/1 DELETE delete?id=1
    *
    * 如何发送put请求和delete请求?
    * 1.需要配置HiddenHttpMethodFilter
    * 2.需要发送post请求
    * 3.视图需要发送post请求时携带一个name="_method"的隐藏域,值为delete或put
    *
    * 在springmvc的目标方法中如何得到id呢?
    * 使用@PathVariable("id") 注解
    *
    * @ResponseBody注解(没有会报错405)
    * 发起的请求是个RESTFul风格的请求，调用了RESTFul风格的PUT方法。但是controller里testRestPUT返回的success字符串被映射到success.jsp。
    * 因此spring认为这应该是个JSP接口，且JSP接口仅仅支持GET方法和POST方法。所以系统提示提示了这个错误。
    * */
    @RequestMapping(value = "/rest/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public String testRestPut(@PathVariable("id") Integer id){
        System.out.println("testRestPut-"+id);
        return SUCCESS;
    }

    @RequestMapping(value = "/rest/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String testRestDelete(@PathVariable("id") Integer id){
        System.out.println("testRestDelete-"+id);
        return SUCCESS;
    }

    @RequestMapping(value = "/rest",method = RequestMethod.POST)
    public String testRestPost(){
        System.out.println("testRestPost");
        return SUCCESS;
    }

    @RequestMapping(value = "/rest/{id}",method = RequestMethod.GET)
    public String testRest(@PathVariable("id") Integer id){
        System.out.println("testRest-"+id);
        return SUCCESS;
    }
    /*
    * @PathVariable 可以来映射URL 中的占位符将参数传到目标方法的参数中
    */
    @RequestMapping("/getPathVariable/{id}")
    public String getPathVariable(@PathVariable("id") Integer id){
        System.out.println("getPathVariable:"+id);
        return SUCCESS;
    }
    /*4.url 占位符  了解 */
    @RequestMapping("/getAntPath/*/ab")
    public String getAntPath(){
        System.out.println("getAntPath");
        return SUCCESS;
    }

    /*3. 了解 传参和请求头,请求头请在浏览器中找 */
    @RequestMapping(value = "/Param",params = {"username","age!=10"},headers = {"Accept-Language=zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"})
    public String getParam(){
        System.out.println("getParam");
        return SUCCESS;
    }
    /*
    *2. 常用:使用method属性来指定请求方式*/
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String getMethod(){
        System.out.println("getMethod");
        return SUCCESS;
    }
    /*
    1. @RequestMapping
    * 类定义处:提供了初步的请求映射信息,相对于web应用的根目录
    * 方法处:提供了进一步的详细映射信息
    * 相对于类定义处的url.若类定义处未标注@RequestMapping,则方法处标记的url相对于web应用的根目录
    * */
    @RequestMapping("/getSuccess")
    public String getSuccess(){
        System.out.println("成功了");
        return SUCCESS;
    }
}
