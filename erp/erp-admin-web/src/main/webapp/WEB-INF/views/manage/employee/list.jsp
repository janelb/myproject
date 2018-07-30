<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Theme style -->

    <%@ include file="../../include/css.jsp" %>

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

    <%@ include file="../../include/header.jsp" %>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>
    <!-- =============================================== -->




    <!-- =============================================== -->
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <section class="content-header">
                <h1>
                    账号管理
                </h1>
            </section>
            <div class="box no-border">
                <div class="box-body">

                    <form class="form-inline">
                        <input type="text" name="nameMobile" placeholder="账号或手机号码" class="form-control" value="${param.nameMobile}">
                        <select name="roleId" class="form-control">
                            <option value="">所有角色</option>
                            <c:forEach items="${roleList}" var="roles">
                                <option value="${roles.id}" ${param.roleId == roles.id ? 'selected' : ''}>${roles.roleName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>

                </div>
            </div>


            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
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
                            <th>手机号码</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <c:forEach items="${employeeList}" var="employee">
                        <tbody>
                        <tr>

                                <td>${employee.employeeName}</td>
                                <td>${employee.tell}</td>

                                <td>
                                    <c:forEach items="${employee.roleList}" var="role">
                                        ${role.roleName}
                                    </c:forEach>
                                </td>

                            <td>

                                ${employee.state == 2 ? "正常" : "冻结"}

                            </td>
                            <td>${employee.createTime}</td>

                            <td>
                               <c:choose>
                                   <c:when test="${employee.state==1}">
                                        <a href="javascript:;" rel="${employee.id}"  fro="${employee.state}" class="frozen" id="fro">解除</a>
                                   </c:when>
                                   <c:when test="${employee.state==2}">
                                       <a href="javascript:;" rel="${employee.id}"  fro="${employee.state}" class="frozen" id="fro">禁止</a>
                                   </c:when>
                               </c:choose>




                                <a  href="javascript:;" rel="${employee.id}" class="del">删除</a>
                                <a href="/manage/employ/${employee.id}/edit">编辑</a>
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
    <%@ include file="../../include/footer.jsp" %>

</div>
<%@ include file="../../include/js.jsp" %>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script  type="text/javascript">
    $(function(){
        var message="${message}";
        if(message){
            layer.msg(message);
        }

        $("#add").click(function () {
            window.location.href="/manage/employ/add"
        })

        /*删除人员*/

        $(".del").click(function(){

            var id = $(this).attr("rel");
            layer.confirm("你确定要删除吗？", function () {
                window.location.href="/manage/employ/"+id+"/del";
            })
        })

        /*冻结人员*/

            $(".frozen").click(function () {
                var id = $(this).attr("rel");
                var state = $(this).attr("fro");
                layer.confirm("你确定要冻结吗？",function(){
                    window.location.href="/manage/employ/"+id+"/frozen/"+state;

                   /* $.ajax({
                        url:"/employ/"+id+"/frozen",
                        type:"get",
                       /!* beforeSend:function(){
                            $("#fro").attr("disabled", "disabled").text("禁用中...");
                        },*!/
                        success: function (res) {
                            if (res !=0) {


                                layer.alert("冻结成功", function () {

                                  /!*   document.getElementById("fro").style.display="none";
                                     document.getElementById("re").style.display="block";*!/
                                  /!*  $("#fro").style.visibility="hidden";*!/

                                       /!* $("#fro").style.display='none';*!/
                                    /!* $("#re").show();*!/

                                    document.getElementById("fro").style.display="none";
                                    document.getElementById("re").style.display="inline";

                                  /!*  history.go(0);*!/

                                });
                            } else {

                                document.getElementById("fro").style.display="none";
                                document.getElementById("re").style.display="inline";
                            }
                        },
                      /!*  error: function () {
                            alert("系统繁忙");
                        },
                        complete: function () {
                            $("#re").removeAttr("disabled").text("解除");
                        }
*!/
                    })*/


                })
            })




    });
</script>
</body>
</html>
