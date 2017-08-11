<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/3/17
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="top-bar" class="top-bar">
    <div class="bar">
        <button id="navbox-trigger" class="navbox-trigger"><i class="fa fa-lg fa-th"></i></button>
    </div>
    <div class="navbox">
        <div class="navbox-tiles">
            <a href="${pageContext.request.contextPath}/admin" class="tile">
                <div class="icon"><i class="fa fa-home"></i></div><span class="title">首页</span>
            </a>
            <a href="${pageContext.request.contextPath}/admin/article/manage" class="tile">
                <div class="icon"><i class="fa fa-book"></i></div><span class="title">文章管理</span>
            </a>
            <a href="${pageContext.request.contextPath}/admin/note/manage" class="tile">
                <div class="icon"><i class="fa fa-sticky-note"></i></div><span class="title">笔记管理</span>
            </a>
            <a href="${pageContext.request.contextPath}/admin/message/list" class="tile">
                <div class="icon"><i class="fa fa-sticky-note"></i></div><span class="title">留言</span>
            </a>
            <a href="${pageContext.request.contextPath}/admin/login/signOut" class="tile">
                <div class="icon"><i class="fa fa-sign-out"></i></div><span class="title">退出</span>
            </a>

        </div>
    </div>
</div>