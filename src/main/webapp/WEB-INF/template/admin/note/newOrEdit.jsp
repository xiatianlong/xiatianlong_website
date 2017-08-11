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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-input/bootstrap-tagsinput.css">
    <title><c:choose><c:when test="${not empty note}">编辑笔记</c:when><c:otherwise>创建笔记</c:otherwise></c:choose></title>
</head>
<body>

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <%--content begin--%>

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home"></i>  首页</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/note/manage">笔记管理</a></li>
            <li class="active"><c:choose><c:when test="${not empty note}">编辑笔记</c:when><c:otherwise>创建笔记</c:otherwise></c:choose></li>
        </ol>
    </div>
    <div class="row">
        <form id="createNoteForm" method="post">
            <input type="hidden" name="id" id="noteId" value="${note.id}">
            <div class="col-md-12 margin-b-5">
                <small>笔记标题<span class="text-danger padding-l-5">*</span>：</small>
                <input type="text" class="form-control" id="title" name="title" placeholder="请输入笔记标题" value="${note.title}">
            </div>
            <div class="col-md-12 margin-b-5">
                <hr>
                <small>笔记标签：</small>
                <!--dom结构部分-->
                <input type="text" class="form-control" value="${note.fmtTags}" data-role="tagsinput" id="tags" name="tags"/>
            </div>
            <div class="col-md-12 margin-b-60">
                <small>笔记内容<span class="text-danger padding-l-5">*</span>：</small>
                <textarea class="xtl-editor-content" id="textarea1" name="content" placeholder="请输入内容">${note.content}</textarea>
            </div>
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="col-md-12 margin-t-10">
                    <button type="button" id="submitBtn" class="btn btn-success float-r submit-btn"><i class="fa fa-save"></i>  提交</button>
                </div>
            </nav>
        </form>
    </div>


    <%--content end--%>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<%-- bootstrap-tagsinput --%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-input/bootstrap-tagsinput.min.js"></script>
<script>

    $(function(){
        // editor init ....
        var editor = new wangEditor('textarea1');
        editor.config.uploadImgUrl = '/common/editorFileUpload';
        editor.config.uploadImgFileName = 'editorUploadFileName';
        // 将图片大小限制为 3M
        editor.config.uploadImgMaxSize = 3 * 1024 * 1024;
        // 限制一次最多上传 5 张图片
        editor.config.uploadImgMaxLength = 1;
        editor.create();


        $("#submitBtn").click(function(){

            var title = $("#title").val();
            var content = $("#textarea1").val();
            var tags = $("#tags").tagsinput('items');
            if(!Common.validate.isNotEnpty(title)){
                Common.toast("error", "请输入笔记标题");
                return false;
            }
            if(title.length > 100){
                Common.toast("error", "笔记标题限制100个字符");
                return false;
            }
            if(!Common.validate.isNotEnpty(tags)){
                Common.toast("error", "请添加笔记标签");
                return false;
            }
            if(tags.length > 5){
                Common.toast("error", "笔记标签限制5个");
                return false;
            }
            if(!Common.validate.isNotEnpty(content)){
                Common.toast("error", "请输入笔记内容");
                return false;
            }

            <c:choose>
                <c:when test="${not empty note}">
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/admin/note/update",
                        data: $("#createNoteForm").serialize(),
                        success: function(data){
                            if (data.result == 'success'){
                                Common.toast("success", "保存成功，请重新发布上线");
                                setTimeout(function(){
                                    window.location.href="${pageContext.request.contextPath}/admin/note/manage";
                                }, 1000);
                            }else{
                                Common.toast("error", data.message);
                            }
                        }
                    });
                </c:when>
                <c:otherwise>
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/admin/note/create",
                        data: $("#createNoteForm").serialize(),
                        success: function(data){
                            if (data.result == 'success'){
                                Common.toast("success", "新建成功，等待发布上线");
                                setTimeout(function(){
                                    window.location.href="${pageContext.request.contextPath}/admin/note/manage";
                                }, 1000);
                            }else{
                                Common.toast("error", data.message);
                            }
                        }
                    });
                </c:otherwise>
            </c:choose>


        });


    })

</script>
</body>
</html>
