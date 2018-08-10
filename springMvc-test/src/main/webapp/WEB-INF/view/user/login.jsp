<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/20
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/user/login" method="post">
        用户名：<input type="text" name="username" value="${name}"><br>
       地址： <input type="text" name="address"><br>
        记住我：<input type="checkbox" name="remember" <c:if test="${not empty name}">
                   checked
        </c:if>  ><br>
        <button>提交</button>

    </form>
</body>
</html>
