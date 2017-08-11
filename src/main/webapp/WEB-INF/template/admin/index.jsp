<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/4/16
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="basic/head.jsp"></jsp:include>
    <title>XiaTianlong Admin</title>
</head>
<body>

<jsp:include page="basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 text-c" id="xtl-index-title">Welcome to XiaTianlong's website</div>

        <div class="col-md-10  col-md-offset-1 margin-t-30 margin-b-10"><hr></div>

        <div class="col-md-10 col-md-offset-1">
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">系统版本</div>
                <div class="col-md-12 xtl-index-system-text">${osName }</div>
            </div>
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">系统版本</div>
                <div class="col-md-12 xtl-index-system-text">${osArch }</div>
            </div>
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">JD版本</div>
                <div class="col-md-12 xtl-index-system-text">${javaVersion }</div>
            </div>
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">工作目录</div>
                <div class="col-md-12 xtl-index-system-text">${currentDir}</div>
            </div>
        </div>

        <div class="col-md-10  col-md-offset-1 margin-t-10 margin-b-10"><hr></div>

        <div class="col-md-10 col-md-offset-1">
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">文章</div>
                <div class="col-md-12 xtl-index-system-text">
                    <p>上线数：${onlineArticleCnt}</p>
                    <p>下线数：${offlineArticleCnt}</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">笔记</div>
                <div class="col-md-12 xtl-index-system-text">
                    <p>上线数：${onlineNoteCnt}</p>
                    <p>下线数：${offlineNoteCnt}</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="col-md-12 xtl-index-system-title">留言</div>
                <div class="col-md-12 xtl-index-system-text">
                    <p>总留言：${messageAllCnt}</p>
                    <p>未读：${unReadMessageCnt}</p>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="basic/footer.jsp"></jsp:include>
<script>


</script>
</body>
</html>
