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
                    <h3 class="box-title">修改员工信息</h3>
                    <div class="box-tools pull-right">
                        <a class="btn btn-primary btn-sm" href="employee.html"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" method="post" id="addEmpl">

                        <div class="form-group">
                            <label>姓名</label>
                            <input type="hidden" name="id" value="${employee.id}" class="form-control">
                            <input disabled type="text" name="employeeName" value="${employee.employeeName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <input type="text" name="department" value="维修部" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" name="tell" value="${employee.tell}" class="form-control">
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

    <!-- 底部 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2010 -2017 <a href="http://almsaeedstudio.com">车管家</a>.</strong> All rights
        reserved.
    </footer>

</div>


<!-- jQuery 2.2.3 -->

<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/js.jsp" %>

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
                department:{

                    required:true
                }

            },


            message:{
                employeeName:{
                    required:"请输入用户名"
                },
                tell:{
                    required:"请输入4-11位数字",
                    minlength:"请输入4-11位数字",
                    maxlength:"请输入4-11位数字",
                    digits:"请输入有效数字"

                },
                department:{

                    required:"请输入密码"
                }

            }

        })






    })




</script>


</body>
</html>
