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

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">类型管理</h3>
                   <%-- <div class="box-tools pull-right" id="add" >
                        <button type="button" class="btn btn-box-tool"   title="Collapse">
                            <i class="fa fa-plus"></i> 添加类型</button>
                    </div>--%>
                    <a href="#" class="btn btn-success pull-right"  data-toggle="modal" data-target="#addModal">新增类型</a>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>类型</th>

                        </tr>
                        </thead>
                            <c:forEach items="${typeList}" var="type">
                                <tbody>
                                <tr>
                                    <td>${type.id}</td>
                                    <td>${type.typeName}</td>
                                    <td>

                                        <a href="javascript:;" rel="${type.id}" class="del">删除</a>
                                        <a href="javascript:;" class="update"  td="${type.id}"rel="${type.typeName}"
                                           data-target="#updateModal">编辑</a>
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

    <%--模态框--%>

    <div class="modal fade" id="addModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span  aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增类型</h4>
                </div>

                <div class="modal-body">

                    <form action="/type/add" method="post" class="form-horizontal" id="addForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">类型名称:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name="typeName" id="addNnodeName" placeholder="请输入节点名称">
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary pull-left" id="btn">保存</button>
                    <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->





    <div class="modal fade" id="updateModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">更新节点</h4>
                </div>

                <div class="modal-body">
                    <form action="/type/update" method="post" class="form-horizontal" id="updateForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">节点名称:</label>
                            <div class="col-sm-10">
                                <input type="hidden" class="form-control" name="id"  id="updateNode" placeholder="请输入节点名称">
                                <input type="text" class="form-control" name="typeName"  id="updateNodeName" placeholder="请输入节点名称">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-primary pull-left" id="updateBtn">保存</button>
                    <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->







    <%@ include file="../include/footer.jsp" %>

</div>
    <%@ include file="../include/js.jsp" %>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script>
    $(function(){

        var message = "${message}";
        if(message){
            layer.msg(message);
        }
        /*修改*/

        $(".update").click(function () {
            var name = $(this).attr("rel");

            $("#updateNodeName").val(name);
            var id = $(this).attr("td");

            $("#updateNode").val(id);
            $("#updateModal").modal({

                keyboard:false,
            })
        })

        $("#updateBtn").click(function(){

            $("#updateForm").submit();
        }),

        $("#updateForm").validate({
            errorElement:'span',
            errorClass:'text-danger',
            rules:{
                    typeName:{
                        required:true,
                        remote:'/type/check'
                    }
                },
             messages:{
                 typeName:{
                     required:'请输入类型名称',
                     remote:'该类型已存在'
                 }
            },
         submitHandler:function () {
                $.ajax({
                    url: "/type/update",
                    type: "post",
                    data: $("#updateForm").serialize(),

                    beforeSend : function(){
                        $("#updateBtn").attr("disabled","disabled").text("保存中...");
                    },
                    success : function(res){
                    if(res!=0){
                            layer.alert("修改成功",function(){
                                history.go(0);
                            });

                            /*  window.location.href="/classlist"; */
                        }else{
                        layer.alert("修改失败",function(){
                            history.go(0);
                        });

                        }
                    },

                    error : function(){
                        alert("系统繁忙");
                    },
                    complete : function() {
                        $("#updateBtn").removeAttr("disabled").text("保存");
                    }
                })
         }
        });




        /*新增*/
        $("#btn").click(function(){
            $("#addForm").submit();
        })

        $("#addForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules:{
                typeName:{
                    required:true,
                    remote:"/type/check"
                }
            },
            messages:{
                typeName:{
                    required: "请输入类别名称",
                    remote: "该名称已存在"
                }
            },
            submitHandler:function () {
                $.ajax({
                    url: "/type/add",
                    type: "post",
                    data: $("#addForm").serialize(),

                    beforeSend: function () {
                        $("#btn").attr("disabled", "disabled").text("保存中...");
                    },
                    success: function (res) {
                        if (res != 0) {
                            layer.alert("添加成功", function () {
                                history.go(0);
                                /*  window.location.href="/classlist"; */
                            });
                        } else {

                            layer.alert("添加失败", function () {
                                history.go(0);
                            });
                        }
                    },
                    error: function () {
                        alert("系统繁忙");
                    },
                    complete: function () {
                        $("#btn").removeAttr("disabled").text("保存");
                    }
                })
            }
        });

        /*删除*/

        $(".del").click(function(){
            var id  = $(this).attr("rel");
            layer.confirm("你确定要删除吗？",function () {
                $.ajax({
                    url:"/type/"+id+"/del",
                    type:"get",
                    success:function(res){
                        if(res==true){

                            layer.alert("删除成功",function(){
                                history.go(0);
                        });

                    }else{
                            layer.alert("该类下有配件或已经不存在",function(){
                                history.go(0);
                            });
                        }
                }
            })
            })
        });








    });
</script>
</body>
</html>
