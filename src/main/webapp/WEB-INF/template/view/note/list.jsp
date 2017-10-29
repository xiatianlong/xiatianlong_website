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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/note-list.css">
    <title>Note   记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="margin-t-5">
    <%--content begin--%>
    <div class="container">
        <%--小尺寸显示  begin--%>
        <div class="row">
            <div class="input-group margin-b-10 hidden-lg hidden-md hidden-sm" id="note-search-content-xs">
                <input type="text" class="form-control xtl-index-input" id="noteSearchInput-xs" placeholder="输入搜索关键字">
                <span class="input-group-addon xtl-index-input" id="note-search-btn-xs"><i class="fa fa-search"></i></span>
            </div>
            <div class="col-xs-12 hidden-lg hidden-md hidden-sm">
                <c:choose>
                    <c:when test="${not empty tags && tags.size() gt 0}">
                        <c:forEach items="${tags}" var="tag" varStatus="status">
                            <div class="xtl-tag-group" tag="${tag.tag}">${tag.tag}<span>${tag.count}</span></div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise><span class="xtl-tag-group-null">暂无标签</span></c:otherwise>
                </c:choose>
            </div>
        </div>
        <%--小尺寸显示  end--%>
        <div class="row">
            <div class="col-md-12 padding-0" id="search-tags-content">

            </div>
            <div class="col-md-12 padding-0 margin-t-10">
                <div class="col-md-10 padding-0">
                    <c:if test="${not empty noteList && noteList.size() gt 0}">
                        <section id="noteContent">
                            <c:forEach items="${noteList}" var="note" varStatus="status">
                                <article class="white-panel animated zoomIn xtl-note-card" noteId="${note.id}">
                                    <div><span class="xtl-note-title">${note.title}</span></div>
                                    <div>
                                        <c:forEach items="${note.tags}" var="tag">
                                            <span class="xtl-note-tag">${tag}</span>
                                        </c:forEach>
                                    </div>
                                    <div class="text-r margin-t-10">
                                        <span class="xtl-note-createtime"><i class="fa fa-user"></i> ${note.userName}</span>
                                    </div>
                                    <div class="text-r">
                                        <span class="xtl-note-createtime"><i class="fa fa-clock-o"></i> ${note.createTime}</span>
                                    </div>
                                </article>
                            </c:forEach>
                        </section>
                    </c:if>
                    <c:if test="${empty noteList || noteList.size() == 0}">
                        <div class="col-md-12 text-c xtl-has-not">暂无笔记....</div>
                    </c:if>
                </div>
                <div class="col-md-2 padding-0 hidden-xs">
                    <div class="input-group margin-b-10" id="note-search-content">
                        <input type="text" class="form-control xtl-index-input" id="noteSearchInput" placeholder="输入搜索关键字">
                        <span class="input-group-addon xtl-index-input" id="note-search-btn"><i class="fa fa-search"></i></span>
                    </div>
                    <blockquote class="xtl-blockquote"><span>标签云</span></blockquote>
                    <c:choose>
                        <c:when test="${not empty tags && tags.size() gt 0}">
                            <c:forEach items="${tags}" var="tag" varStatus="status">
                                <div class="xtl-tag-group" tag="${tag.tag}">${tag.tag}<span>${tag.count}</span></div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise><span class="xtl-tag-group-null">暂无标签</span></c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <jsp:include page="../basic/footer_design.jsp"></jsp:include>

    </div>

    <form id="searchNoteForm">
        <input type="hidden" id="lastNoteCreateTime" name="lastNoteCreateTime" value="${lastNoteCreateTime}">

    </form>

    <%--content end--%>
</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/pinterest_grid.js"></script>
<script>
    $(document).ready(function () {
        $("#noteContent").pinterest_grid({
            no_columns:3,
            single_column_breakpoint: 700
        });

        $("#note-search-content").keydown(function(){
            var evt = window.event || arguments[0];
            if (evt && evt.keyCode == 13) {
                noteInputSearch();
            }
        });
        $("#note-search-content-xs").keydown(function(){
            var evt = window.event || arguments[0];
            if (evt && evt.keyCode == 13) {
                noteInputSearchXS();
            }
        });
    });
    $("#noteContent").on('click', '.xtl-note-card', function(){
        window.open("${pageContext.request.contextPath}/note/"+$(this).attr("noteId"));
    });

    // 标签选中处理
    $(".xtl-tag-group").on('click', function(){
        var tag = $(this).attr("tag");
        // 已选中的标签不需再进行选中
        if(!$("input[value='"+tag+"']").size()){
            var searchHtml = '<div class="search-tag"><span>'+tag+'</span><span class="search-tag-remove" tag="'+tag+'"><i class="fa fa-times" aria-hidden="true"></i></span></div>';
            var formHtml = '<input type="hidden" name="tags" value="'+tag+'">';
            $("#search-tags-content").append(searchHtml);
            $("#searchNoteForm").append(formHtml);
            chooseTagSubmit();
        }
    });
    // 标签删除处理
    $("#search-tags-content").on('click', '.search-tag-remove', function(){
        var tag = $(this).attr("tag");
        $(this).parents(".search-tag").remove();
        $("input[value='"+tag+"']").remove();
        chooseTagSubmit();
    });

    // 文本框搜索点击
    $("#note-search-btn").click(function(){
        noteInputSearch();
    });
    $("#note-search-btn-xs").click(function(){
        noteInputSearchXS();
    });

    function noteInputSearch(){
        var keyword = $("#noteSearchInput").val();
        $("#noteSearchInput-xs").val(keyword);
        if ($("input[name='keyword']").size()){
            $("input[name='keyword']").val(keyword);
        }else{
            var formHtml = '<input type="hidden" name="keyword" id="keyword" value="'+keyword+'">';
        }
        $("#searchNoteForm").append(formHtml);
        chooseTagSubmit();
    }

    function noteInputSearchXS(){
        var keyword = $("#noteSearchInput-xs").val();
        $("#noteSearchInput").val(keyword);

        if ($("input[name='keyword']").size()){
            $("input[name='keyword']").val(keyword);
        }else{
            var formHtml = '<input type="hidden" name="keyword" id="keyword" value="'+keyword+'">';
        }
        $("#searchNoteForm").append(formHtml);
        chooseTagSubmit();
    }

    function chooseTagSubmit(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/note/tagSearch",
            data: $("#searchNoteForm").serialize(),
            success: function(data){
                if(data.result == 'success'){
                    $("#noteContent").empty();
                    $("#lastNoteCreateTime").val(data.lastNoteCreateTime);
                    buildHtml(data.noteList);
                }else{
                    $("#noteContent").text("没搜索到结果....");
                }
            }
        });
    }

    // 滚动监听
    $(window).scroll( function() {
        // 滚动到底部触发加载更多
        if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
            var lastDate = $("#articleListLastDate").val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/note/list",
                data: $("#searchNoteForm").serialize(),
                success: function(data){
                    if(data.result == 'success'){
                        $("#lastNoteCreateTime").val(data.lastNoteCreateTime);
                        buildHtml(data.noteList);
                    }else{
                        Common.toast("error", data.message);
                    }
                }
            });
        }
    });

    function buildHtml(noteList){
        for (var i = 0; i < noteList.length; i++){
            var html = '';
            var note = noteList[i];
            html += '<article class="white-panel animated zoomIn xtl-note-card" noteId="'+note.id+'">';
            html += '<div><span class="xtl-note-title">'+note.title+'</span></div>';
            html += '<div>';
            for (var j = 0; j < note.tags.length; j++){
                html += '<span class="xtl-note-tag">'+note.tags[j]+'</span>';
            }
            html += '</div>';
            html += '<div class="text-r margin-t-10">';
            html += ' <span class="xtl-note-createtime"><i class="fa fa-user"></i> '+note.userName+'</span>';
            html += '</div>';
            html += '<div class="text-r">';
            html += ' <span class="xtl-note-createtime"><i class="fa fa-clock-o"></i> '+note.createTime+'</span>';
            html += '</div>';
            html += '</article>';
            $("#noteContent").append(html);
        }
    }
</script>
</body>
</html>
