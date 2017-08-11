<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/3/17
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
                <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_32px.png">
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li <c:if test="${not empty dq_navbarKey && dq_navbarKey eq '050001'}">class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/dq/index">Home</a>
                </li>
                <li <c:if test="${not empty dq_navbarKey && dq_navbarKey eq '050002'}">class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/dq/message">留言板</a>
                </li>
                <li <c:if test="${not empty dq_navbarKey && dq_navbarKey eq '050003'}">class="active"</c:if>>
                    <a href="${pageContext.request.contextPath}/dq/photos">照片墙</a>
                </li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>