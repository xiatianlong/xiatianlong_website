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
    <title>Article   记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12 padding-0 text-r">
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form method="post" action="${pageContext.request.contextPath}/article/list" id="submitForm">
                        <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
                        <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
                        <div class="input-group margin-b-10" id="article-search-content">
                            <input type="text" class="form-control xtl-index-input" id="articleSearchInput" name="keyWord" placeholder="输入搜索关键字" value="${keyword}">
                            <span class="input-group-addon xtl-index-input" id="article-search-btn"><i class="fa fa-search"></i></span>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-12 margin-b-10">
                    <a href="${pageContext.request.contextPath}/article/timeline" class="xtl-article-type"><i class="fa fa-calendar"></i> 时间轴</a>&nbsp;&nbsp;&nbsp;
                    <span><i class="fa fa-list"></i> 列表</span>
                </div>
            </div>
            <div class="col-md-12 col-sm-12 col-xs-12">
                <c:if test="${not empty list && list.size() gt 0}">
                    <c:forEach items="${list}" var="article" varStatus="status">
                        <div class="col-md-10 col-sm-11 col-xs-12 mobile-padding-0 xtl-article-list-card animated <c:choose><c:when test="${status.index % 2 == 0}">fadeInLeft </c:when><c:otherwise>fadeInRight</c:otherwise></c:choose>" articleId="${article.id}">
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0 card-title">${article.title}</div>
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0">
                                <c:choose>
                                    <c:when test="${not empty article.image}">
                                        <div class="col-md-3 col-sm-3 col-xs-12 padding-0 card-img"><img src="${article.image}"></div>
                                        <div class="col-md-9 col-sm-9 col-xs-12 padding-0 card-introduction">${article.introduction}</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-12 col-sm-12 col-xs-12 padding-0 card-introduction">${article.introduction}</div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="text-r">
                                <span title="访问量"><i class="fa fa-eye"></i> (${article.browseTimes})</span>&nbsp;&nbsp;
                                <span title="创建时间"><i class="fa fa-clock-o"></i> ${article.createTime}</span>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <%--分页--%>
                <div id="Pagination" class="col-md-12 col-sm-12 col-xs-12"></div>

            </div>
        </div>

        <jsp:include page="../basic/footer_design.jsp"></jsp:include>

    </div>
    <%--content end--%>
</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        // 初始化分页
        initPageList(${dataCnt}, ${pageNo}, ${pageSize});

        $("#submitForm").keydown(function(){
            var evt = window.event || arguments[0];
            if (evt && evt.keyCode == 13) {
                $("#submitForm").submit();
            }
        });
    });

    // 搜索提交
    $("#article-search-btn").click(function(){
        $("#submitForm").submit();
    });

    //初始化分页
    function initPageList(dataCnt, pageIndex, pageSize) {
        if (dataCnt > 0) {
            $("#Pagination").pagination(dataCnt, {
                callback: PageCallback, //PageCallback() 为翻页调用次函数。
                items_per_page: pageSize,
                num_display_entries: 5, //连续分页主体部分分页条目数
                current_page: pageIndex - 1 //当前页索引
            });
        }else {
            $(".cmain").remove();
            $("#Pagination").hide();
            $("#detailList").html("<tr class=\"cmain\" style='text-align:center;font-size:14px;'><td colspan='5' style='color:red;'>对不起，没有找到数据!</td></tr>");
        }
    }

    // 提交分页表单（同步）
    function getPageList(pageIndex, pageSize) {
        $("#pageNo").val(pageIndex);
        $("#submitForm").submit();
    }

    // 卡片点击
    $(".xtl-article-list-card").on('click', function(){
        window.open("${pageContext.request.contextPath}/article/"+$(this).attr("articleId"));
    });




</script>
</body>
</html>
