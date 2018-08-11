<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<aside class="main-sidebar">
    <section class="sidebar">
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="header">系统功能</li>


            <!-- 库存管理 -->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-home"></i> <span>库存管理</span>
                    <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/parts"><i class="fa fa-circle-o"></i>配件管理</a></li>
                    <li><a href="/type"><i class="fa fa-circle-o"></i>类型管理</a></li>
                    <li><a href="/parts/new"><i class="fa fa-circle-o"></i>配件入库</a></li>
                    <li><a href="/inventory/in"><i class="fa fa-circle-o"></i>入库查询</a></li>
                    <li><a href="/inventory/out"><i class="fa fa-circle-o"></i>出库查询</a></li>
                </ul>
            </li>>




        </ul>
    </section>
    <!-- /.sidebar -->
</aside>