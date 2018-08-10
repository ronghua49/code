<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/23
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h3 style="color: red">${message}</h3>
<form action="" method="post" enctype="multipart/form-data">
    <input type="text" name="remark" placeholder="请输入文件描述">
    <input type="file" name="file">
    <button style="color: orange">提交</button>
</form>


</body>
</html>
