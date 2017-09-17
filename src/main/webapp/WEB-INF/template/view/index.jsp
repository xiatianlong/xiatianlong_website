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

    <jsp:include page="basic/head.jsp"></jsp:include>
    <title>夏天龙的个人网站，记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
        <div class="container">
            <div class="row">

                <%--<div class="col-md-12 col-sm-12 col-xs-12">--%>
                    <%--<div class="col-md-12 col-sm-12 col-xs-12" id="xtl-index-head">--%>
                        <%--<img src="${pageContext.request.contextPath}/resources/images/banner.jpg">--%>
                        <%--<span id="xtl-index-head1" class="animated fadeIn">既然选择了远方</span>--%>
                        <%--<span id="xtl-index-head2">便只顾风雨兼程</span>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="col-md-12 col-sm-12 col-xs-12 margin-t-20">
                    <div class="col-md-8 col-sm-8 col-xs-12 mobile-padding-0">
                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0 xtl-img-tab-title "><img src="${pageContext.request.contextPath}/resources/images/biz/icon/paperClip_128px.png"></div>

                        <c:if test="${not empty list && list.size() gt 0}">
                            <c:forEach items="${list}" var="index" varStatus="status">
                                <c:choose>
                                    <c:when test="${index.type eq 'article'}">
                                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0 animated fadeInLeft index-article-card" articleId="${index.id}">
                                            <c:if test="${not empty index.image}">
                                                <div class="col-md-4 col-sm-4 col-xs-12 article-img">
                                                    <img src="${index.image}">
                                                </div>
                                            </c:if>
                                            <div class="<c:choose><c:when test="${not empty index.image}">col-md-8 col-sm-8 col-xs-12</c:when><c:otherwise>col-md-12 col-sm-12 col-xs-12</c:otherwise></c:choose>">
                                                <div class="col-md-12 col-sm-12 col-xs-12 padding-0 title">
                                                    ${index.title}
                                                </div>
                                                <div class="col-md-12 col-sm-12 col-xs-12 padding-0 introduction">
                                                    ${index.introduction}
                                                </div>
                                                <div class="col-md-12 col-sm-12 col-xs-12 padding-0 text-r">
                                                    <i class="fa fa-clock-o"></i>  ${index.createTime}
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${index.type eq 'note'}">
                                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0 animated fadeInLeft index-note-card" noteId="${index.id}">
                                            <div class="col-md-12 col-sm-12 col-xs-12 title">
                                                ${index.title}
                                            </div>
                                            <c:if test="${not empty index.tags && index.tags.size() gt 0}">
                                                <div class="col-md-12 col-sm-12 col-xs-12 text-r">
                                                    <c:forEach items="${index.tags}" var="tag">
                                                        <span class="tag">${tag}</span>
                                                    </c:forEach>
                                                </div>
                                            </c:if>
                                            <div class="col-md-12 col-sm-12 col-xs-12 text-r">
                                                <i class="fa fa-clock-o"></i> ${index.createTime}
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty list && list.size() == 0}">
                            <div class="col-md-12 col-sm-12 col-xs-12 text-c xtl-has-not">暂无推荐....</div>
                        </c:if>

                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-12 mobile-padding-0">
                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0">
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0 xtl-img-tab-title "><img src="${pageContext.request.contextPath}/resources/images/biz/icon/new_128px.png"></div>
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0" id="xtl-index-new">
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="article-tab-new">
                                        <c:choose>
                                            <c:when test="${not empty newList && newList.size() gt 0}">
                                                <c:forEach items="${newList}" var="article">
                                                    <div class="col-md-12 col-sm-12 col-xs-12 padding-0 xtl-text-overflow xtl-tab-item">
                                                        <a href="${pageContext.request.contextPath}/article/${article.id}">${article.title}</a>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-md-12 col-sm-12 col-xs-12 xtl-has-not text-c">暂无最新....</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0">
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0 xtl-img-tab-title "><img src="${pageContext.request.contextPath}/resources/images/biz/icon/hot_128px.png"></div>
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0"  id="xtl-index-hot">
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="article-tab-hot">
                                        <c:choose>
                                            <c:when test="${not empty hotList && hotList.size() gt 0}">
                                                <c:forEach items="${hotList}" var="article">
                                                    <div class="col-md-12 col-sm-12 col-xs-12 padding-0 xtl-text-overflow xtl-tab-item">
                                                        <a href="${pageContext.request.contextPath}/article/${article.id}">${article.title}</a>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-md-12 col-sm-12 col-xs-12 xtl-has-not text-c">暂无最热....</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <jsp:include page="basic/footer_design.jsp"></jsp:include>

        </div>
    <%--content end--%>
</div>

<jsp:include page="basic/footer.jsp"></jsp:include>
<script>

    $(function(){
        setTimeout(function(){
            $("#xtl-index-head2").show();
            $("#xtl-index-head2").addClass("fadeIn animated");
        },700);

    });

    $(".index-article-card").on('click', function(){
        var articleId = $(this).attr("articleId");
        window.location.href="${pageContext.request.contextPath}/article/"+articleId;
    });
    $(".index-note-card").on('click', function(){
        var noteId = $(this).attr("noteId");
        window.location.href="${pageContext.request.contextPath}/note/"+noteId;
    });

</script>
</body>
</html>
