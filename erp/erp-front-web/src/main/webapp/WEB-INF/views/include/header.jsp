<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<header class="main-header">
    <!-- Logo -->
    <a href="index2.html" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>ERP</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>车管家</b>ERP</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs"> <%-- ${sessionScope.employee.employeeName}--%> <shiro:principal property="employeeName"/> </span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                        <%--    <c:forEach items="${sessionScope.employee.roleList}" var="role">--%>
                                    <p>
                                       <%-- <small>${role.roleName}</small>--%>
                                           <shiro:principal property="employeeName"/>
                                    </p>

                         <%--   </c:forEach>--%>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat pull-right"  data-toggle="modal"  data-target="#updateModal">个人设置</a>
                            </div>
                               <div class="pull-right">
                                <a href="/manage/employ/loginout" class="btn btn-default btn-flat">安全退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">个人设置</h4>
            </div>
            <div class="modal-body">
                <form action="" method="post" class="form-horizontal" id="updateForm">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">输入密码:</label>
                        <div class="col-sm-10">
                          <%--  <input type="hidden" class="form-control" name="password" value="${sessionScope.employee.password}"  id="password">--%>
                            <%--  <input type="hidden" class="form-control" name="employeeName" value="<shiro:principal property="employeeName"/>"  id="employ">
                              <input type="text" class="form-control" name="reemployeeName"  id="reemployeeName" placeholder="请输入用户名">--%>
                            <input type="hidden" class="form-control" name="password" value="<shiro:principal property="password"/>"  id="password">
                            <input type="password" class="form-control" name="repassword"  id="repassword" placeholder="请输入密码">

                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary pull-left" id="updateBtn">提交</button>
                <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%--<script src="/static/dist/js/jquery.validate.min.js"></script>--%>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script>
    $(function() {

        $("#updateBtn").click(function () {

      /*  window.location.href="/manage/employ/*/<%--${sessionScope.employee.id}/set";--%>
           window.location.href="/manage/employ/<shiro:principal property="id"/>/set";
        })

        $("#updateForm").validate({
            errorEmement: "span",
            errorClass: "text-danger",
            rules: {
            /*    employeeName:{
                    required: true

                },
                reemployeeName:{
                    required: true,
                    equalTo: "#employeeName"
                },*/
                password: {

                    required: true
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                /*employeeName:{
                    required: "请输入用户名"

                },
                reemployeeName:{
                    required: "请再次输入用户名",

                },*/
                password: {
                    required: "请输入密码"
                },
                repassword: {
                    required: "请再次输入密码",
                    equalTo: "密码错误"

                },
            }
        })
    })


</script>



