<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/3/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <jsp:include page="../basic/head.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/wangEditor-2.1.23/css/wangEditor.min.css">
    <title>${note.title}</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 xtl-article-detail-content">
                <div class="col-md-12 padding-0 text-c article-title">${note.title}</div>
                <div class="col-md-12 padding-0 text-c article-second-title"><i class="fa fa-user"></i> ${note.userName}&nbsp;&nbsp;&nbsp;<i class="fa fa-clock-o"></i> ${note.createTime}&nbsp;&nbsp;</div>
                <c:if test="${not empty note.tags && note.tags.size() gt 0}">
                    <div class="col-md-12 padding-0 margin-b-20">
                        <c:forEach items="${note.tags}" var="tag">
                            <span class="xtl-note-tag">${tag}</span>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="padding-0 col-md-12 wangEditor-container" style="border: none;background: none;">
                    <article class="col-md-12 padding-0 wangEditor-txt" style="word-break: break-all;">${note.content}</article>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-10 col-md-offset-1 margin-b-50">
                <!--PC和WAP自适应版-->
                <div id="SOHUCS" sid="note_${note.id}" ></div>
                <script type="text/javascript">
                    (function(){
                        var appid = 'cyt39FTO0';
                        var conf = 'prod_695f5c8dbb174d36659144535b115189';
                        var width = window.innerWidth || document.documentElement.clientWidth;
                        if (width < 960) {
                            window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); } else { var loadJs=function(d,a){var c=document.getElementsByTagName("head")[0]||document.head||document.documentElement;var b=document.createElement("script");b.setAttribute("type","text/javascript");b.setAttribute("charset","UTF-8");b.setAttribute("src",d);if(typeof a==="function"){if(window.attachEvent){b.onreadystatechange=function(){var e=b.readyState;if(e==="loaded"||e==="complete"){b.onreadystatechange=null;a()}}}else{b.onload=a}}c.appendChild(b)};loadJs("https://changyan.sohu.com/upload/changyan.js",function(){window.changyan.api.config({appid:appid,conf:conf})}); } })(); </script>
            </div>
        </div>

        <jsp:include page="../basic/footer_design.jsp"></jsp:include>
    </div>
    <%--content end--%>
</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {


    });
</script>
</body>
</html>
