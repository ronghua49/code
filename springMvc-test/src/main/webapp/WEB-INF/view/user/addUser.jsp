<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/20
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>addUser</title>
</head>
<body>
<form action="/user/add" method="post"><br>
    用户名：<input type="text" name="username"><br>
   密码：<input type="text"name="password"><br>
    地址：<input type="text" name="address"><br>
    电话：<input type="text" name="tel">
    <button>submit</button>
</form>
</body>
</html>
