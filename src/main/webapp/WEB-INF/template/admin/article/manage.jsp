<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/4/16
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="../basic/head.jsp"></jsp:include>
    <title>admin index</title>
</head>
<body>

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <%--content begin--%>

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home"></i>  首页</a></li>
            <li class="active">文章管理</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-12 padding-0">
                <a href="${pageContext.request.contextPath}/admin/article/new" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus"></i>  新建文章</a>
            </div>
            <div class="col-md-6 padding-0 margin-t-10">
                <form method="post" action="${pageContext.request.contextPath}/admin/article/manage" id="submitForm">
                    <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
                    <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
                    <div class="form-group">
                        <input type="text" class="form-control xtl-input" name="keyWord" value="${keyword}" placeholder="输入文章标题关键字">
                    </div>
                </form>
            </div>
            <table class="table" style="table-layout:fixed">
                <thead>
                    <tr>
                        <th width="5%">#</th>
                        <th width="55%">文章标题</th>
                        <th width="15%">创建时间</th>
                        <th width="10%">浏览次数</th>
                        <th width="15%">操作</th>
                    </tr>
                </thead>
                <tbody id="detailList">

                    <c:forEach items="${list}" var="article" varStatus="status">

                        <tr>
                            <th>${status.index + 1 + ((pageNo -1) * pageSize) }</th>
                            <td class="xtl-text-overflow"><a href="#">${article.title}</a></td>
                            <td>${article.createTime}</td>
                            <td>${article.browseTimes}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${article.statusCode eq '020001'}"><button type="button" class="btn btn-default btn-xs article-offline-btn" articleId="${article.id}">下线</button></c:when>
                                    <c:when test="${article.statusCode eq '020002'}"><button type="button" class="btn btn-default btn-xs article-online-btn" articleId="${article.id}">上线</button></c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${!article.recommend}"> <button type="button" class="btn btn-default btn-xs article-recommend-btn" articleId="${article.id}">推荐</button></c:when>
                                    <c:otherwise> <button type="button" class="btn btn-default btn-xs article-unrecommend-btn" articleId="${article.id}">不推荐</button></c:otherwise>
                                </c:choose>
                                <button type="button" class="btn btn-default btn-xs article-update-btn" articleId="${article.id}">编辑</button>
                                <button type="button" class="btn btn-default btn-xs article-delete-btn" articleId="${article.id}">删除</button>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <%--分页--%>
            <div id="Pagination"></div>

        </div>
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
            $("#detailList").html("<tr class=\"cmain\" style='text-align:center;font-size:14px;'><td colspan='5' style='color:red;'>对不起，没有符合的记录!</td></tr>");
        }
    }

    // 提交分页表单（同步）
    function getPageList(pageIndex, pageSize) {
        $("#pageNo").val(pageIndex);
        $("#submitForm").submit();
    }

    // 下线
    $("#detailList").on('click', '.article-offline-btn', function(){
        var $offline = $(this);
        Common.confirm("确认要下线文章吗？", function(){
            var articleId = $offline.attr("articleId");
            $.post("${pageContext.request.contextPath}/admin/article/offline/"+articleId, function(data){
                if(data.result == 'success'){
                    Common.toast("success", "文章已下线");
                    $offline.text("上线");
                    $offline.addClass("article-online-btn");
                    $offline.removeClass("article-offline-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });
    // 上线
    $("#detailList").on('click', '.article-online-btn', function(){
        var $online = $(this);
        Common.confirm("确认要发布上线该文章吗？", function(){
            var articleId = $online.attr("articleId");
            $.post("${pageContext.request.contextPath}/admin/article/online/"+articleId, function(data){
                if(data.result == 'success'){
                    Common.toast("success", "发布上线成功");
                    $online.text("下线");
                    $online.addClass("article-offline-btn");
                    $online.removeClass("article-online-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });

    // 推荐
    $("#detailList").on('click', '.article-recommend-btn', function(){
        var $recommend = $(this);
        Common.confirm("确认要推荐该文章？", function(){
            var articleId = $recommend.attr("articleId");
            $.post("${pageContext.request.contextPath}/admin/article/recommend/"+articleId+"/true", function(data){
                if(data.result == 'success'){
                    Common.toast("success", "文章已推荐");
                    $recommend.text("不推荐");
                    $recommend.addClass("article-unrecommend-btn");
                    $recommend.removeClass("article-recommend-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });
    // 不推荐
    $("#detailList").on('click', '.article-unrecommend-btn', function(){
        var $unrecommend = $(this);
        Common.confirm("确认要发布上线该文章吗？", function(){
            var articleId = $unrecommend.attr("articleId");
            $.post("${pageContext.request.contextPath}/admin/article/recommend/"+articleId+"/false", function(data){
                if(data.result == 'success'){
                    Common.toast("success", "已取消推荐该文章");
                    $unrecommend.text("推荐");
                    $unrecommend.addClass("article-recommend-btn");
                    $unrecommend.removeClass("article-unrecommend-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });

    // 编辑
    $(".article-update-btn").click(function(){
        var articleId = $(this).attr("articleId");
        window.location.href="${pageContext.request.contextPath}/admin/article/edit/"+articleId;
    });
    // 删除
    $(".article-delete-btn").click(function(){
        var $delete = $(this);
        Common.confirm("确认要删除该文章吗？", function(){
            var articleId = $delete.attr("articleId");
            $.post("${pageContext.request.contextPath}/admin/article/delete/"+articleId, function(data){
                if(data.result == 'success'){
                    Common.toast("success", "删除成功");
                    $delete.parents("tr").fadeOut();
                }else{
                    Common.toast("error", data.message);
                }
            });
        });

    });

</script>
</body>
</html>
