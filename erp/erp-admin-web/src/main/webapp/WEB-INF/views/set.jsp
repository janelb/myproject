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
    <!-- Bootstrap 3.3.6 -->
    <%@ include file="include/css.jsp" %>


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

    <%@ include file="include/header.jsp" %>
    <jsp:include page="include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>
   ====================== -->
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">个人设置</h3>
                    <div class="box-tools pull-right">
                        <a class="btn btn-primary btn-sm" href="/manage/employ"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" method="post" id="addEmpl">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" name="id" value="${employee.id}" hidden >
                            <input type="text" name="employeeName"  value="${employee.employeeName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" name="tell" value="${employee.tell}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>请输入新密码</label>
                            <input type="password" name="password" id="password" value="${employee.password}" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>请再次输入新密码</label>
                            <input type="password" name="repassword" value="${employee.password}"  class="form-control" >
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="btn">保存</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
<%@ include file="include/footer.jsp" %>

</div>
<!-- jQuery 2.2.3 -->

<%@ include file="include/js.jsp" %>

<script>
    $(function(){



        $("#btn").click(function(){

            $("#addEmpl").submit();
        })

        $("#addEmpl").validate({
            errorEmement:"span",
            errorClass:"text-danger",
            rules:{
                employeeName:{
                    required:true
                },
                tell:{
                    required:true,
                    minlength:4,//电话号码至少4位
                    maxlength:11,//电话号码最多11位
                    digits:true //必须是纯数字

                },
                password:{

                    required:true
                },
                repassword:{

                    required:true,
                    equalTo:"#password"
                },

            },
            messages:{
                employeeName:{
                    required:"请输入用户名"
                },
                tell:{
                    required:"请输入4-11位数字",
                    minlength:"请输入4-11位数字",
                    maxlength:"请输入4-11位数字",
                    digits:"请输入有效数字"

                },
                password:{

                    required:"请输入密码"
                },
                repassword:{

                    required:"请再次输入密码",
                    equalTo:"两次输入的不同"
                },

            }

        })

    })

</script>


</body>
</html>
