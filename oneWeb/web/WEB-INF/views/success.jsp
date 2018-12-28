<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/25
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>成功</h1>
time:${requestScope.time}
<br>
names:${requestScope.names}
<br>
request user:${requestScope.user}
<br>
session user:${sessionScope.user}
<br>
request test:${requestScope.test}
<br>
session test:${sessionScope.test}
<br/>
<fmt:message key="i18n.username"></fmt:message>
<br/>
<fmt:message key="i18n.password"></fmt:message>
<br/>


</body>
</html>
