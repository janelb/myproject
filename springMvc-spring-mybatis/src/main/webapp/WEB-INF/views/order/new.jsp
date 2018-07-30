<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-汽车保养项目</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
    <style>
        .td_title {
            font-weight: bold;
            /* font-size: 14px; */
            /* text-align: right */
        }
        .td_title:input {
            margin-left: 5px;
            font-size: 14px;
        }

        .table>tbody>tr>td {
            vertical-align: middle;
        }

    </style>
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

    <!-- 顶部导航栏部分 -->
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
                            <span class="hidden-xs">李美苏</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                <p>
                                    李美苏
                                    <small>海外事业部</small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="/profile" class="btn btn-default btn-flat">个人设置</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/logout" class="btn btn-default btn-flat">安全退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <aside class="main-sidebar">
        <section class="sidebar">
            <!-- 菜单 -->
            <ul class="sidebar-menu">
                <li class="header">系统功能</li>
                <!-- 维修服务 -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-share-alt"></i> <span>维修服务</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i>历史订单</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i>新建订单</a></li>
                    </ul>
                </li>
                <!-- 结算管理 -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-jpy"></i> <span>结算管理</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i>订单结算</a></li>
                    </ul>
                </li>
                <!-- 统计报表 -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i> <span>统计报表</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i>收入统计</a></li>
                    </ul>
                </li>


                <li class="header">系统管理</li>
                <!-- 部门员工管理 -->
                <li class=""><a href="#"><i class="fa fa-users"></i> <span>员工管理</span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h3 style="text-align: center">
                保养维修单
            </h3>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户车辆信息</h3>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tr>
                            <td class="td_title">车牌号:</td>
                            <td style="width: 280px">
                                <input type="text" class="form-control" value="豫H888255">

                            </td>
                            <td >
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-info btn-flat" id="search"><i class="fa fa-search"></i></button>
                            </span>
                            </td>
                            <td class="td_title">客户姓名:</td>
                            <td>张三</td>
                            <td class="td_title">身份证号:</td>
                            <td>410523199008051982</td>
                        </tr>
                        <tr>
                            <td class="td_title">车主电话:</td>
                            <td>159283756352</td>
                            <td></td>
                            <td class="td_title">车型:</td>
                            <td>大众CC</td>
                            <td class="td_title">车辆识别码:</td>
                            <td>SN38643FDH341251</td>

                        </tr>
                    </table>

                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">维修项目</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="minus"><i class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" id="plus"><i class="fa fa-plus"></i></button>
                    </div>
                </div>

                <div class="box-body">
                    <table class="table table-bordered " style="border-width: 2px;" id="infoForm">
                        <thead>
                        <tr>
                            <th>项目代码</th>
                            <th>项目名称</th>
                            <th>工时费用</th>
                            <th>主修人</th>
                        </tr>
                        </thead>
                        <tbody id="addTr">

                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="4" style="font-weight: bold">合计 ： 200元</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">预估备件</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="minus"><i class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" id="plus"><i class="fa fa-plus"></i></button>
                    </div>
                </div>

                <div class="box-body">
                    <table class="table table-bordered " style="border-width: 2px;" id="infoForm">
                        <thead>
                        <tr>
                            <th>备件编号</th>
                            <th>备件名称</th>
                            <th>所需个数</th>
                            <th>备件单价</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="4" style="font-weight: bold">合计 ： 200元</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <div class="modal fade" tabindex="-1" role="dialog" id="addUserModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">添加用户</h4>
                        </div>
                        <div class="modal-body">
                            <form action="" class="">
                                <input type="hidden" name="" value="豫H8898" class="form-control" disabled>
                                <div class="form-group">
                                    <label>车辆识别码：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车辆型号</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车牌号码：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主姓名：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主身份证：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主电话：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车型：</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>颜色：</label>
                                    <input type="text" class="form-control">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary">添加</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

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
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<!--select2-->
<script src="/static/plugins/select2/select2.min.js"></script>

<script>
    $(function() {
        //点击放大镜
        $("#search").click(function() {

            $("#addUserModal").modal({
                show:true,
                backdrop:'static'
            });



        });

        var html = "<tr>\n" +
            "                <td><input type='text' name=''  class='input01'/></td>\n" +
            "                <td><input type='text' name='' class='input02'/></td>\n" +
            "                <td><input type='text' name='' class='input03'/></td>\n" +
            "                <td><input type='text' name='' class='input04'/></td>\n" +
            "                <td><button class='btn btn-xs btn-success aaa'>添加</button>" +
            "                <button class='btn btn-xs btn-danger bbb'>取消</button></td>" +
            "            </tr>"
        //点击加号
        $("#plus").click(function() {

            $("#addTr").append($(html));

        })

        //点击加号
        $("#minus").click(function() {
            $('.del').show();


        })
        //点击删除
        $(document).on("click",".del",function() {

            $(this).parent("tr").remove();

        })

        //点击添加
        $(document).on("click",".aaa",function() {

            var input01 = $(".input01").val();
            var input02 = $('.input02').val();
            var input03 = $('.input03').val();
            var input04 = $('.input04').val();

            var res = "<tr>\n" +
                "               <td>" + input01 + "</td>\n" +
                "               <td>" + input02 + "</td>\n" +
                "               <td>" + input03 + "</td>\n" +
                "               <td>" + input04 + "</td>\n" +
                "               <td hidden class='del'><button class='btn btn-xs btn-danger'>删除</button></td>\n" +
                "           </tr>"


            if(input01 || input02 || input03 || input04) {
                $("#addTr").find("tr:last").remove();
                $("#addTr").append($(res));
            }else {
                alert("请填入值");
            }
        })
        //点击取消
        $(document).on("click",".bbb",function() {

            $("#addTr").find("tr:last").remove();

        })



    })
</script>
</body>
</html>
