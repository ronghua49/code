<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-员工管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="employee"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1 style="display: inline-block;">
               员工管理
            </h1>
            <div class="box-tools pull-right" style="display: inline-block">
                <button type="button" class="btn btn-box-tool"  title="Collapse" id="addBtn">
                    <i class="fa fa-plus"></i> 添加员工</button>
            </div>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline pull-left" id="searchForm" action="">
                        <input type="text" name="employeeName" placeholder="员工姓名或者电话" class="form-control" value="${param.employeeName}">
                        <select class="form-control" name="state" id="state">
                                <option value="">请选择状态</option>
                                <option value="0" ${(param.state==0) ? 'selected' : '' } >禁用</option>
                                <option value="1" ${(param.state==1) ? 'selected' : '' } >正常</option>
                        </select>
                        <select class="form-control" name="roleId" id="roleIds">
                            <option value="">请选择职位</option>
                            <c:forEach items="${roleList}" var="role">
                            <option value="${role.id}"} >${role.roleName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-default" id="btn" >搜索</button>
                    </form>
                </div>
            </div>

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <table class="table">

                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>职位</th>
                            <th>电话</th>
                            <th>录入时间</th>
                            <th>状态</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="employee">
                            <tr>
                                <td>${employee.employeeName}</td>
                                <td>
                                    <c:forEach items="${employee.roleList}" var="role">
                                        ${role.roleName}&nbsp;
                                    </c:forEach>
                                </td>
                                <td>${employee.employeeTel}</td>
                                <td> <fmt:formatDate type="date" value="${employee.createTime}" /></td>
                                <td>${employee.state eq "1" ? "正常":"禁止"}</td>
                                <td>
                                    <a href="/manage/employee/${employee.id}/lock">${employee.state eq "1" ? "禁用":"启用"}</a>
                                    <a href="/manage/employee/${employee.id}/edit">更新</a>
                                    <a href="javascript:;" class="del" rel="${employee.id}">删除</a> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul id="pagination" class="pagination pull-right"></ul>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp" %>


</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>
<script>
    $(function(){
        $("#pagination").twbsPagination({
            totalPages : ${page.pages},
            visiblePages : 5,
            first : '首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}&employeeName=${param.employeeName}&state=${state}"
        });
        var locale = {
            "format": 'YYYY-MM-DD',
            "separator": " - ",//
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始时间",
            "toLabel": "结束时间'",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        };

        var startDate = "";
        var endDate = "";

        if(startDate && endDate) {
            $('#time').val(startDate + " / " + endDate);
        }

        $('#time').daterangepicker({
            autoUpdateInput:false,
            "locale": locale,
            "opens":"right",
            "timePicker":false
        },function(start,end) {
            $("#startTime").val(start.format('YYYY-MM-DD'));
            $("#endTime").val(end.format('YYYY-MM-DD'));

            $('#time').val(start.format('YYYY-MM-DD') + " / " + end.format('YYYY-MM-DD'));
        });
        var message="${message}"
        if(message){
            layer.msg(message);
        }

        $(".del").click(function(){
            var employeeId = $(this).attr("rel");
            layer.confirm("are your sure delete this employee?",function () {
                window.location.href="/manage/employee/"+employeeId+"/del";
            })
        });

        $("#addBtn").click(function () {
            window.location.href="/manage/employee/add";
        });
    })
</script>
</body>
</html>
