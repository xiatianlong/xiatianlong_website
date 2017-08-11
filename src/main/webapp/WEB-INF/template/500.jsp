<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/3/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <jsp:include page="view/basic/head.jsp"></jsp:include>
    <title>xiatianlong</title>

</head>

<body class="index-body">



<div class="text-c">
    <span class="xtl-error-page-title" style="margin-top: 10%">
        <span style="font-size:28px;">500</span><br>
        别激动！系统出现故障而已，错误信息已经邮件提醒给了管理员&nbsp;(⁎˃ᴗ˂⁎)<br>
        管理员将尽快解决。

    </span>

</div>
<div class="text-c"><a href="${pageContext.request.contextPath}/" class="xtl-backhome-btn">回首页</a></div>


<jsp:include page="view/basic/footer.jsp"></jsp:include>
<script>

</script>
</body>
</html>
