<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-新增订单</title>

    <style>
        .td_title {
            font-weight: bold;
        }

        .table>tbody>tr>td {
            vertical-align: middle;
        }

    </style>
    <%@ include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">
    <%@ include file="../include/header.jsp" %>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="order"/>
    </jsp:include>

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1 style="text-align: center">
                保养维修单
            </h1>
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
                                <input type="text" id="carLisence" name="licenceNo" class="form-control" v-model="car.licenceNo"/>
                            </td>
                            <td >
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-info btn-flat" id="search"  @click="search"><i class="fa fa-search"></i></button>
                            </span>
                            </td>
                            <td class="td_title">客户姓名:</td>
                            <td id="userName">{{customer.userName}}</td>
                            <td class="td_title">身份证号:</td>
                            <td id="idCard">{{customer.idCard}}</td>
                        </tr>
                        <tr>
                            <td class="td_title">车主电话:</td>
                            <td id="tel">{{customer.tel}}</td>
                            <td></td>
                            <td class="td_title">车型:</td>
                            <td id="carType">{{car.carType}}</td>
                            <td class="td_title">车辆识别码:</td>
                            <td id="carNo">{{car.carNo}}</td>
                        </tr>
                    </table>

                </div>
            </div>




            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">项目选择</h3>
                </div>

                <div class="box-body">
                    <div class="form-inline">
                        <select class="form-control" name="" id=""  v-model="serviceType">
                            <option value="">请选择项目</option>
                            <option  v-for="item in serviceTypes"  :value="item">{{item.serviceName}}-{{item.serviceHour}} 工时</option>
                        </select>
                        <button class="btn btn-info">选择</button>
                    </div>
                    <br>
                    <table class="table table-bordered " style="border-width: 2px;" id="infoForm">
                        <thead>
                        <tr>
                            <th>项目代码</th>
                            <th>项目名称</th>
                            <th>工时费用</th>
                        </tr>
                        </thead>
                        <tbody id="addTr">
                        <tr v-if="serviceType.serviceNo">
                            <td>{{serviceType.serviceNo}}</td>
                            <td>{{serviceType.serviceName}}</td>
                            <td>{{serviceType.serviceHour*50}}</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="4" class="td_title">小计 :{{hourFee}}元</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>






            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">选择配件</h3>
                </div>

                <div class="box-body">

                    <div class="form-inline">
                        <select class="form-control" name="type" id="type" @change="changeType" v-model="partsType">
                            <option value="">请选择配件类型</option>
                            <option v-for="item in partsTypes" :value="item">{{item.typeName}}</option>

                        </select>


                        <select class="form-control" name="parts" v-model="chooseParts">
                            <option value="">请选择配件</option>
                            <option v-for="item in partsList" :value="item" >{{item.partsName}}- {{item.partsNo}}</option>
                        </select>
                        <button class="btn btn-info"  @click="addParts">选择</button>
                    </div>
                    <table class="table table-bordered " style="border-width: 2px;">
                    <br>
                       <%-- id="partsInfoForm"--%>
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>单价</th>
                            <th><span style="margin-left: 20px">数量</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in choosePartsList">
                            <td>{{item.partsNo}}</td>
                            <td>{{item.partsName}}</td>
                            <td>{{item.salePrice}}</td>
                            <td><button type="button" class="btn btn-box-tool" id="minus" @click="minus(item)"><i class="fa fa-minus"></i></button>
                                <input type="text" class="num" v-model="item.num">
                                <button type="button" class="btn btn-box-tool" id="plus" @click="plus(item)"><i class="fa fa-plus"></i></button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-sm" @click="delParts(item)"><li class="fa fa-minus"></li></button>
                            </td>

                        </tr>

                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="5" class="td_title">小计 ：{{partsSum}}元</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h4>总计： {{fee}} 元</h4>
                </div>
            </div>

            <button class="btn btn-info btn-block btn-lg" @click="newOrder">下单</button>

            <div class="modal fade" tabindex="-1" role="dialog" id="addUserModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">添加用户</h4>
                        </div>
                        <div class="modal-body">

                            <form id="addCarForm" action="/car/add" method="post" class="form">
                                <div class="form-group">
                                    <label>车牌号码：</label>
                                    <input type="text" id="newCarLisence" name="licenceNo" v-model="car.licenceNo" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车辆识别码：</label>
                                    <input type="text" name="carNo" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车辆型号</label>
                                    <input type="text" name="carType" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主姓名：</label>
                                    <input type="text" name="userName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主身份证：</label>
                                    <input type="text" name="idCard" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>车主电话：</label>
                                    <input type="text" name="tel" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>颜色：</label>
                                    <input type="text" name="color" class="form-control">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="addCar" @click="addCarInfo">添加</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </section>
        <!-- /.content -->


    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp" %>

</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>

<script src="/static/dist/js/vue.js"></script>
<script>
    
    $(function () {

        /*使用vuejs*/
        var vm = new Vue({
            el:'#app',
            data:{
                car:{},
                customer :{},
                serviceTypes:[],
                partsTypes:[],
                serviceType:{},
                partsType:'',
                partsList:[],
                choosePartsList:[],
                chooseParts:{}
            },
            methods:{
                search:function(){
                   //如果车牌号为空，不打开模态框，
                    var licenceNo =this.car.licenceNo;
                    if(licenceNo){
                        $.get("/car/check",{"licenceNo":licenceNo}).done(function (res) {
                                if(res.state == 'success'){
                                    vm.car = res.data;
                                    vm.customer = res.data.customer;
                                }else{
                                    /*如果车牌号不存在则打开模态框*/
                                    $("#addUserModal").modal({
                                        show:true,
                                        backdrop:'static'
                                    })

                                }
                        }).error(function () {
                            layer.msg("参数异常");
                        })

                    }else{

                        layer.alert("请填写车牌")
                    }
                },

                /*模态框添加数据*/
                addCarInfo:function(){
                    $.post("/car/add",$("#addCarForm").serialize()).done(function (res) {
                        if(res.state == "success"){
                            vm.car=res.data;
                            vm.customer = res.data.customer;
                            $("#addUserModal").modal("hide");
                        }
                    }).error(function(){
                        layer.msg("系统异常");
                    })

                },



                /*选择添加配件*/

                addParts:function(){
                    var addFlag = false;
                    for(var i = 0; i < this.choosePartsList.length; i++) {
                        if(this.chooseParts == this.choosePartsList[i]) {
                            addFlag = true;
                            this.choosePartsList[i].num = this.choosePartsList[i].num + 1;
                            break;
                        }
                    }
                    if(!addFlag) {
                        this.choosePartsList.push(this.chooseParts);
                    }

                },



                /*选择配件类型*/
                changeType:function(){
                    if(this.partsType.id){
                        $.get("/order/" + this.partsType.id +"/parts").done(function (response) {
                            for(var i = 0; i < response.data.length ; i++){
                                response.data[i].num = 1;
                            }
                            vm.partsList = response.data;
                        }).error(function(err){
                            layer.msg("系统异常");
                        })
                    }
                },

                /*减少配件数量*/
                minus:function(item){
                    if(item.num){
                        item.num --;
                    }
                },
                /*增加配件数量*/
                plus:function(item){
                    item.num ++;
                },
                /*删除配件*/

                delParts:function(item){
                    var index = this.choosePartsList.indexOf(item);
                    this.choosePartsList.splice(index,1);
                },

                /*下订单进行验证*/
                newOrder:function(){
                    if(!this.car.id){
                        layer.msg("请输入车辆信息");
                        return;
                    };

                    if(!this.serviceType.id){

                        layer.msg("请选择服务项目类型");
                        return;
                    };

                    if(!this.choosePartsList.length){
                        layer.msg("请选择配件");
                        return;
                    }
                    /*提交订单时传递数据*/
                    var partsList = [];
                    for(var i = 0 ; i < this.choosePartsList.length;i++){
                        var parts = {};
                        parts.id = this.choosePartsList[i].id;
                        parts.num=this.choosePartsList[i].num;
                        partsList.push(parts);
                    }

                    /*ajax提交订单*/
                    $.ajax({ // axios
                        type:"post",
                        url:"/order/new",
                        data:{
                            json: JSON.stringify({
                                "carId" :vm.car.id,
                                "serviceTypeId":vm.serviceType.id,
                                "fee":vm.fee,
                                "partsList":partsList
                            })
                        },
                        success: function(json){
                            if(json.state == "success") {
                                window.location.href = "/order/undone/list";
                            }
                        }

                    })

                },

            },





            /*进行函数计算*/
            computed:{
                /*配件费用合计*/

                partsSum:function(){
                    var sum = 0;
                    var chooseParts
                    for(var i = 0 ; i< this.choosePartsList.length; i++){
                        chooseParts = this.choosePartsList[i];
                        sum += chooseParts.salePrice * chooseParts.num;
                    }
                    return sum;
                },

                /*服务类型费用计算*/
                hourFee:function(){
                    return this.serviceType.serviceHour ? this.serviceType.serviceHour*50 : 0;
                },

                /*总费用*/
                fee:function(){
                    return this.hourFee + this.partsSum;
                }
            },


            /*使用钩子在加载页面是发起ajax请求*/
            mounted:function(){
                /*服务类型列表*/
                $.get("/order/service/types").done(function(response){
                    vm.serviceTypes = response.data;
                }).error(function(err){
                    layer.msg("系统异常");
                });

                /*配件类型*/
                $.get("/order/parts/types").done(function(response){
                    vm.partsTypes = response.data;

                }).error(function(err){
                    layer.msg("系统异常");
                });

            }







        })
















       /*
        //点击查找按钮
        $("#search").click(function () {
            //如果车牌号为空，不打开模态框，
            var licenseNo = $("#carLisence").val();
            if(licenseNo){

                $.get("/car/check",{"licenceNo":$("#carLisence").val()}).done(function (res) {
                //如果车牌号不为空，检查车牌号是否存在，
                    if(res.state == "success"){
                      //如果车牌号存在，带回车主和车辆信息
                        $("#userName").text(res.data.customer.userName);
                        $("#idCard").text(res.data.customer.idCard);
                        $("#tel").text(res.data.customer.tel);
                        $("#carType").text(res.data.carType)
                        $("#carNo").text(res.data.carNo)
                    }else{
                        /!*如果车牌号不存在，打开模态框,新增车辆信息*!/
                        $("#newCarLisence").val($("#carLisence").val());
                        $("#addUserModal").modal({
                            show:true,
                            backdrop:'static'
                        })
                    }
                }).error(function () {
                    layer.msg("参数异常");
                })
            }else{
                layer.alert("请填写车牌")
            }
        })


        /!*提交模态框*!/
        $("#addCar").click(function () {
            $("#addCarForm").submit();
        })

*/


    })

</script>
</body>
</html>
