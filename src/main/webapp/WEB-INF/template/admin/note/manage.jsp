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
    <title>笔记管理</title>
</head>
<body>

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <%--content begin--%>

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home"></i>  首页</a></li>
            <li class="active">笔记管理</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-12 padding-0">
                <a href="${pageContext.request.contextPath}/admin/note/new" type="button" class="btn btn-success btn-sm"><i class="fa fa-plus"></i>  新建笔记</a>
            </div>
            <div class="col-md-6 padding-0 margin-t-10">
                <form method="post" action="${pageContext.request.contextPath}/admin/note/manage" id="submitForm">
                    <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
                    <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
                    <div class="form-group">
                        <input type="text" class="form-control xtl-input" name="keyWord" value="${keyword}" placeholder="输入笔记标题关键字">
                    </div>
                </form>
            </div>
            <table class="table" style="table-layout:fixed">
                <thead>
                    <tr>
                        <th width="5%">#</th>
                        <th width="30%">笔记标题</th>
                        <th width="20%">标签</th>
                        <th width="15%">浏览次数</th>
                        <th width="15%">创建时间</th>
                        <th width="15%">操作</th>
                    </tr>
                </thead>
                <tbody id="detailList">
                    <c:forEach items="${list}" var="note" varStatus="status">
                        <tr>
                            <th>${status.index + 1 + ((pageNo -1) * pageSize) }</th>
                            <td class="xtl-text-overflow"><a href="#">${note.title}</a></td>
                            <td>${note.fmtTags}</td>
                            <td>${note.browseTimes}</td>
                            <td>${note.createTime}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${note.statusCode eq '021001'}"><button type="button" class="btn btn-default btn-xs note-offline-btn" noteId="${note.id}">下线</button></c:when>
                                    <c:when test="${note.statusCode eq '021002'}"><button type="button" class="btn btn-default btn-xs note-online-btn" noteId="${note.id}">上线</button></c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${!note.recommend}"> <button type="button" class="btn btn-default btn-xs note-recommend-btn" noteId="${note.id}">推荐</button></c:when>
                                    <c:otherwise> <button type="button" class="btn btn-default btn-xs note-unrecommend-btn" noteId="${note.id}">不推荐</button></c:otherwise>
                                </c:choose>
                                <button type="button" class="btn btn-default btn-xs note-update-btn" noteId="${note.id}">编辑</button>
                                <button type="button" class="btn btn-default btn-xs note-delete-btn" noteId="${note.id}">删除</button>
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
    $("#detailList").on('click', '.note-offline-btn', function(){
        var $offline = $(this);
        Common.confirm("确认要下线笔记吗？", function(){
            var noteId = $offline.attr("noteId");
            $.post("${pageContext.request.contextPath}/admin/note/offline/"+noteId, function(data){
                if(data.result == 'success'){
                    Common.toast("success", "笔记已下线");
                    $offline.text("上线");
                    $offline.addClass("note-online-btn");
                    $offline.removeClass("note-offline-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });

    });
    // 上线
    $("#detailList").on('click', '.note-online-btn', function(){
        var $online = $(this);
        Common.confirm("确认要发布上线该笔记吗？", function(){
            var noteId = $online.attr("noteId");
            $.post("${pageContext.request.contextPath}/admin/note/online/"+noteId, function(data){
                if(data.result == 'success'){
                    Common.toast("success", "发布上线成功");
                    $online.text("下线");
                    $online.addClass("note-offline-btn");
                    $online.removeClass("note-online-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });

    // 推荐
    $("#detailList").on('click', '.note-recommend-btn', function(){
        var $recommend = $(this);
        Common.confirm("确认要推荐笔记吗？", function(){
            var noteId = $recommend.attr("noteId");
            $.post("${pageContext.request.contextPath}/admin/note/recommend/"+noteId+"/true", function(data){
                if(data.result == 'success'){
                    Common.toast("success", "笔记已推荐");
                    $recommend.text("不推荐");
                    $recommend.addClass("note-unrecommend-btn");
                    $recommend.removeClass("note-recommend-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });

    });
    // 不推荐
    $("#detailList").on('click', '.note-unrecommend-btn', function(){
        var $unrecommend = $(this);
        Common.confirm("确认要取消该笔记的推荐吗？", function(){
            var noteId = $unrecommend.attr("noteId");
            $.post("${pageContext.request.contextPath}/admin/note/recommend/"+noteId+"/false", function(data){
                if(data.result == 'success'){
                    Common.toast("success", "笔记已修改为推荐");
                    $unrecommend.text("推荐");
                    $unrecommend.addClass("note-recommend-btn");
                    $unrecommend.removeClass("note-unrecommend-btn");
                }else{
                    Common.toast("error", data.message);
                }
            });
        });
    });

    // 编辑
    $(".note-update-btn").click(function(){
        var noteId = $(this).attr("noteId");
        window.location.href="${pageContext.request.contextPath}/admin/note/edit/"+noteId;
    });
    // 删除
    $(".note-delete-btn").click(function(){
        var $delete = $(this);
        Common.confirm("确认要删除该笔记吗？", function(){
            var noteId = $delete.attr("noteId");
            $.post("${pageContext.request.contextPath}/admin/note/delete/"+noteId, function(data){
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
