<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Theme style -->

    <%@ include file="../include/css.jsp" %>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>
    <!-- =============================================== -->




    <!-- =============================================== -->
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">员工管理</h3>
                    <div class="box-tools pull-right" id="add" >
                        <button type="button" class="btn btn-box-tool"   title="Collapse">
                            <i class="fa fa-plus"></i> 添加员工</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>部门</th>
                            <th>手机</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${employeeList}" var="employee">
                        <tbody>
                        <tr>

                                <td>${employee.employeeName}</td>
                                <td>维修组</td>
                                <td>${employee.tell}</td>


                            <td>
                                <a href="">禁用</a>
                                <a  href="javascript:;" rel="${employee.id}" class="del">删除</a>
                                <a href="/employ/${employee.id}/edit">编辑</a>
                            </td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@ include file="../include/footer.jsp" %>

</div>
<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script>
    $(function(){
        var message="${message}";
        if(message){
            layer.msg(message);
        }

        $("#add").click(function () {
            window.location.href="/employ/add"
        })

        /*删除人员*/

        $(".del").click(function(){

            var id = $(this).attr("rel");
            layer.confirm("你确定要删除吗？", function () {
                window.location.href="/employ/"+id+"/del";
            })
        })




    });
</script>
</body>
</html>
