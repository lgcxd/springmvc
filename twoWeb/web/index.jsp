<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/28
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#testJson").click(function(){
                var url = this.href;
                var args = {};
                $.post(url, args, function(data){
                    for(var i = 0; i < data.length; i++){
                        var id = data[i].id;
                        var lastName = data[i].lastName;
                        alert(id + ": " + lastName);
                    }
                });
                return false;
            });
        })
    </script>
  </head>
  <body>
  <form action="testFileUpload" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc"/>
    <input type="submit" value="提交"/>
  </form>
  <br>
  <a href="/emps">Test EmployeesAll</a>
  <br>
  <a href="testJson" id="testJson">testJson</a>
  <br>
  <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc"/>
    <input type="submit" value="提交">
  </form>
  <br>
  <a href="testResponseEntity"> testResposeEntity </a>
  <br>

  <%--
  关于国际化:
  1.在页面上根据浏览器语言设置的情况对文本(不是内容),时间 数值进行本地化处理
  2.可以在bean 中获取国际化资源文件Locale对应的消息
  3.可以通过超链接切换Locale,而不再依赖于浏览器的语言设置情况

  解决:
  1.使用jstl的fmt 标签
  2.在bean中注入ResourceBundleMessageSource示例,使用对应的getMessage方法即可
  3.配置LocalResolver 和 LocaleChangeInterceptor
  --%>
  <br>
  <a href="i18n">I18n page</a>

  <br>
  <a href="testException?i=10">testException</a>
  <br><br>
  <a href="tesResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>
  <br>
  <a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
  <br>
  <a href="testSimpleMappingExceptionResolver?i=10">Test SimpleMappingExceptionResolver</a>
  </body>
</html>
