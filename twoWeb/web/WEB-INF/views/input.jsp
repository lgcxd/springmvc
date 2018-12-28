<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="testConversionServiceConverer" method="POST">
    <!-- lastname-email-gender-department.id 例如: GG-gg@atguigu.com-0-105 -->
    Employee: <input type="text" name="employee"/>
    <input type="submit" value="Submit"/>
</form>
<br><br>
    <%--
        1.为什么使用form标签呢?
        可以更快速的开发出表单页面,而且可以更方便的进行表单的回显

        2. 注意:
		可以通过 modelAttribute 属性指定绑定的模型属性,
		若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
		如果该属性值也不存在，则会发生错误。
    --%>
    <form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
        <form:errors path="*"></form:errors>
        <br>
        <c:if test="${employee.id == null }">
            <!-- path 属性对应 html 表单标签的 name 属性值 -->
            LastName: <form:input path="lastName"/>
            <form:errors path="lastName"></form:errors>
        </c:if>
        <c:if test="${employee.id != null }">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
            <%-- 对于 _method 不能使用 form:hidden 标签, 因为 modelAttribute 对应的 bean 中没有 _method 这个属性 --%>
            <%--
            <form:hidden path="_method" value="PUT"/>
            --%>
        </c:if>
        <br/>
        Email:<from:input path="email"/>
        <form:errors path="email"></form:errors>
        <br>
        <br/>
        <%
            Map<String,String> genders = new HashMap<>();
            genders.put("1","Male");
            genders.put("0","Female");

            request.setAttribute("genders", genders);//放到域对象里面
        %>
        Gender:<from:radiobuttons path="gender" items="${genders}" delimiter="<br>"/>
        <br/>
        Department:<from:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
        <br/>
        <%--1.数据类型转换
            2.数据格式化
            3.数据校验-
            1)如何校验? 注解?
            使用jsr303验证标准 Hibnate vilidator
            1.加入jar 2.配置文件下 <mvc:annotation-driven/> 3.属性上注解 4.在目标方法bean 类型前面添加@Valid注解
            2)验证出错转向那个页面?
            注意:需校验的bean 对象和其绑定结果对象或错误对象是成对出现,他们之间不允许声明其他的入参
            3)错误消息?如何显示?<form:errors path=""> 如何把错误消息国际化? --%>
        Birth:<form:input path="birth"/>
        <form:errors path="birth"></form:errors>
        <br>
        Salary:<form:input path="salary"/>
        <br>
        <input type="submit" value="提交"/>
    </form:form>
</body>
</html>
