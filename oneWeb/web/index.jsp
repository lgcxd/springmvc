<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="test/testRedirect">testRedirect</a>
  <br>

  <a href="test/testView">testView</a>
  <br>

  <a href="test/testModelResolover">testModelResolover</a>
  <br>
  <HR/>
  <!--
		模拟修改操作
		1. 原始数据为: 1, Tom, 123456,1@qq.com,12
		2. 密码不能被修改.
		3. 表单回显, 模拟操作直接在表单填写对应的属性值
	-->
  <form action="test/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"/>
    username: <input type="text" name="username" value="Tom"/>
    <br>
    email: <input type="text" name="email" value="1@qq.com"/>
    <br>
    age: <input type="text" name="age" value="12"/>
    <br>
    <input type="submit" value="Submit"/>
  </form>
  <br/>
  <a href="test/testSessionAttributes">testSessionAttributes</a>
  <br>
  <a href="test/testMap">testMap</a>
  <br>
  <a href="test/testModelAndView">testModelAndView</a>
  <br>
  <a href="test/testServletAPI">testServletAPI</a>
  <br>
  <form action="test/testPojo" method="post">
    username:<input type="text" name="username"/>
    <br>
    password:<input type="password" name="password"/>
    <br>
    email:<input type="email" name="email"/>
    <br>
    age:<input type="text" name="age"/>
    <br>
    city:<input type="text" name="address.city"/>
    <br>
    province:<input type="text" name="address.province"/>
    <br>
    <input type="submit" name="user提交">
  </form>
  <br/>
  <a href="test/cookieValue">requestCookieValue</a>
  <br>
  <a href="test/requestHeader">requestHeader</a>
  <br/>
  <a href="test/getRequestParames?username=cbb&age=11">getRequestParames</a>
  <hr color="red">
  <%--有问题--%>
  <form action="test/rest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="restPut"/>
  </form>
  <!-- rest 删除操作 -->
  <br/>
  <form action="test/rest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="restDelete"/>
  </form>
  <!-- rest post -->
  <br/>
  <form action="test/rest" method="post">
    <input type="submit" value="rest">
  </form>
  <!-- rest风格规范 get-->
  <br/>
  <a href="test/rest/1" methods="get">rest </a>
  <hr/>
  <!-- url占位符传参 将参数传到目标方法 -->
  <a href="test/getPathVariable/21">getPathVariable</a>
  <br/>
  <!-- url占位符 -->
  <a href="test/getAntPath/lal/ab">getAntPath</a>
  <br/>
  <!-- url传参和头 -->
  <a href="test/Param?username=com&age=11">Param</a>
  <br/>
  <!-- url限定post方式 -->
  <form action="test/testMethod" method="post">
    <input type="submit" value="submit">
  </form>
  <br/>
  <%--这个是不可行的 HTTP Status 405 - Request method 'GET' not supported 因为这是一个get请求 所以要用上面的form表单形式 --%>
  <a href="test/testMethod">testMethod</a>
  <br/>
  <a href="test/getSuccess">getSuccess</a>
  <br/>
  <a href="helloworld">hello world!</a>
  </body>
</html>
