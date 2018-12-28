<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/27
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form method="post" action="testMethod">
    <button>提交</button>
  </form>
  <!-- HTTP Status 405 - Request method 'GET' not supported 异常 应该写post方式的表单 -->
  <a href="testMethod">testMethod</a>
  <a href="helloTest">Test1</a>
  </body>
</html>
