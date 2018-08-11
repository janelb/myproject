<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-订单列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                入库单查询
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                     <%--   <input type="text" name="orderId" placeholder="订单号" class="form-control">--%>
                        <input type="text" name="partsId" placeholder="配件编号" value="${param.partsNo}" class="form-control">
                       <%-- <input type="hidden" name="startTime" id="startTime">
                        <input type="hidden" name="endTime" id="endTime">
                        <input type="text" class="form-control" id="time" placeholder="下单日期选择">--%>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>配件编码</th>
                            <th>配件名称</th>
                            <th>入库前数量</th>
                            <th>入库数量</th>
                            <th>当前数量</th>
                            <th>采购员</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="partsIn">
                        <tr>
                            <td>${partsIn.parts.partsNo}</td>
                            <td>${partsIn.parts.partsName}</td>
                            <td>${partsIn.parts.inventory-partsIn.num}</td>
                            <td>${partsIn.num}</td>
                            <td>${partsIn.parts.inventory}</td>
                            <td>${partsIn.employee.employeeName}</td>
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

    <%@ include file="../include/footer.jsp" %>

</div>

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
            href:"?p={{number}}&partsNo="+encodeURIComponent('${param.partsNo}')
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
    })
</script>
</body>
</html>
