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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.eeyellow.Timeline.css">
    <title>Article   记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12 text-r">
                <span><i class="fa fa-calendar"></i> 时间轴</span>&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/article/list" class="xtl-article-type"><i class="fa fa-list"></i> 列表</a>
            </div>
            <div class="col-md-12 col-sm-12 col-xs-12 mobile-padding-0">
                <div class="VivaTimeline">
                    <dl id="article-timeline-content">
                        <c:choose>
                            <c:when test="${not empty articleList && articleList.size() gt 0}">
                                <c:forEach items="${articleList}" var="articleMonth" varStatus="monthStatus">
                                    <dt>${articleMonth.month}</dt>
                                    <c:forEach items="${articleMonth.dayList}" var="articleDay" varStatus="dayStatus">
                                        <dd class="animated <c:choose><c:when test="${(dayStatus.index) % 2 == 0}">pos-left fadeInLeft</c:when><c:otherwise>pos-right fadeInRight</c:otherwise></c:choose> clearfix">
                                            <div class="circ"></div>
                                            <div class="time">${articleDay.day}</div>
                                            <div class="events">
                                                <div class="events-body">
                                                    <c:forEach items="${articleDay.dataList}" var="article" varStatus="status">
                                                        <div class="row">
                                                            <div class="events-article-title" articleId="${article.id}">${article.title}</div>
                                                            <c:if test="${not empty article.image}">
                                                                <div class="col-md-6 col-sm-6 col-xs-12 pull-left">
                                                                    <img class="events-object img-responsive img-rounded" src="${article.image}"/>
                                                                </div>
                                                            </c:if>
                                                            <div class="events-desc">
                                                                    ${article.introduction}
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                    <%--多文章切换必须 加footer--%>
                                                <div class="events-footer"></div>
                                            </div>
                                        </dd>
                                    </c:forEach>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-12 col-sm-12 col-xs-12 xtl-has-not text-c">暂无文章....</div>
                            </c:otherwise>
                        </c:choose>
                    </dl>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${lastDate}" id="articleListLastDate">
    <%--content end--%>

</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/jquery.eeyellow.Timeline.js"></script>
<script>
    $(document).ready(function () {
        // 时间轴初始化
        $('.VivaTimeline').vivaTimeline({
            carousel: true,
            carouselTime: 3000
        });

    });

    // 滚动监听
    $(window).scroll( function() {

        // 滚动到底部触发加载更多
        if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
            var lastDate = $("#articleListLastDate").val();
            $.post("${pageContext.request.contextPath}/article/timeline", {lastDate:lastDate},  function(data){
                if(data.result == 'success'){
                    $("#articleListLastDate").val(data.lastDate);
                    buildHtml(data.dataList);
                }else{
                    Common.toast("error", data.message);
                }
            });

        }
    });

    /**
     * 构建数据
     * @param dataList  数据聚合
     */
    function buildHtml(dataList){
        var html = "";
        for (var i = 0; i < dataList.length; i++){
            var monthData = dataList[i];
            html += '<dt>'+monthData.month+'</dt>';
            for(var j = 0; j < monthData.dayList.length; j++){
                var dayData = monthData.dayList[j];
                if(j%2==0){
                    html += '<dd class="animated pos-left fadeInLeft clearfix">';
                }else{
                    html += '<dd class="animated pos-right fadeInRight clearfix">';
                }
                html += '<div class="circ"></div>';
                html += '<div class="time">'+dayData.day+'</div>';
                html += '<div class="events">';
                html += '<div class="events-body">';
                for (var k = 0; k < dayData.dataList.length; k++){
                    var articleData = dayData.dataList[k];
                    html += '<div class="row">';
                    html += '<div class="events-article-title" articleId="'+articleData.id+'">'+articleData.title+'</div>';
                    if (Common.validate.isNotEnpty(articleData.image)){
                        html += '<div class="col-md-6 col-sm-6 col-xs-12 pull-left">';
                        html += '<img class="events-object img-responsive img-rounded" src="'+articleData.image+'"/>';
                        html += '</div>';
                    }
                    html += '<div class="events-desc">'+articleData.introduction+'</div>';
                    html += '</div>';
                }
                html += '</div>';
                html += '<div class="events-footer"></div>';
                html += '</div>';
                html += '</dd>';
            }
        }

        $("#article-timeline-content").append(html);

        $('.VivaTimeline').vivaTimeline({
            carousel: true,
            carouselTime: 3000
        });
    }

    // 去详情画面


    $("#article-timeline-content").on('click', '.events-article-title', function(){
        window.open("${pageContext.request.contextPath}/article/"+$(this).attr("articleId"));
    });

</script>
</body>
</html>
