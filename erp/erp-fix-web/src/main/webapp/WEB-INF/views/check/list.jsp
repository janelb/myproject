<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <![endif]-->
    <%@ include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="order"/>
    </jsp:include>

    <!-- =============================================== -->
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                质检部任务领取
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:forEach items="${page.list}" var="fixOrder">

                <!-- Default box -->
                <div class="box">
                    <div class="box-body">
                        <div class="panel panel-info">
                            <!-- Default panel contents -->
                            <div class="panel-heading">
                                <a href="/check/${fixOrder.orderId}/service">订单号:${fixOrder.orderId}</a> - ${fixOrder.carType} - ${fixOrder.orderType}

                                <c:if test="${fixOrder.state == '4'}">
                                <button rel="${fixOrder.orderId}" class="btn btn-success btn-sm pull-right receiveBtn">任务领取</button> </div>
                            </c:if>

                            <!-- List group -->
                            <c:forEach items="${fixOrder.fixOrderPartsList}" var="fixOrderParts">
                                <ul class="list-group">
                                    <li class="list-group-item">${fixOrderParts.typeName}-${fixOrderParts.partsName} * ${fixOrderParts.partsNum}</li>
                                </ul>
                            </c:forEach>


                        </div>
                    </div>
                </div>
            </c:forEach>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->

    <%@ include file="../include/footer.jsp" %>
</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>
<script>
    $(function(){

        $(".receiveBtn").click(function () {
            //判断是否已经有任务
            var orderId = $(this).attr("rel");
            layer.confirm("你确定要领取该任务？",function () {
                $.get("/check/"+orderId+"/receive",function (res) {
                    if(res.state == 'success'){
                        window.location.href="/check/"+orderId+"/service";
                    }else{
                        layer.msg(res.message );
                    }
                })

            })
        })






        $("#pagination").twbsPagination({
            totalPages :${page.pages},
            visiblePages : 5,
            first : '首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}"
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
