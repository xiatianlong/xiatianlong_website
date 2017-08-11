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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/webupload/webuploader.css">
    <title><c:choose><c:when test="${not empty article}">编辑文章</c:when><c:otherwise>创建文章</c:otherwise></c:choose></title>
</head>
<body>

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <%--content begin--%>

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home"></i>  首页</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/article/manage">文章管理</a></li>
            <li class="active"><c:choose><c:when test="${not empty article}">编辑文章</c:when><c:otherwise>创建文章</c:otherwise></c:choose></li>
        </ol>
    </div>
    <div class="row">
        <form id="createArticleForm" method="post">
            <input type="hidden" name="id" id="articleId" value="${article.id}">
            <div class="col-md-12 margin-b-5">
                <small>文章标题<span class="text-danger padding-l-5">*</span>：</small>
                <input type="text" class="form-control" id="title" name="title" placeholder="请输入文章标题" value="${article.title}">
            </div>
            <div class="col-md-12 margin-b-5">
                <hr>
                <small>封面展示图：</small>
                <!--dom结构部分-->
                <div id="uploader">
                    <!--用来存放item-->
                    <div id="fileList" class="uploader-list">

                        <%--如果存在图片才显示这一块 begin--%>
                        <c:if test="${not empty article.image}">
                            <div class="uploadImg-over-div">
                                <img src="${article.image}" class="uploadImg-over-show">
                                <img src="${pageContext.request.contextPath}/resources/images/tip/error_16px.png" class="uploadImg-over-remove-icon" title="remove">
                                <input type="hidden" name="xtlFile" class="xtl-file" value="${article.image}">
                            </div>
                        </c:if>
                        <%--如果存在图片才显示这一块 end--%>

                    </div>
                    <div class="clearBoth"></div>
                    <div id="filePicker">选择图片</div>
                </div>
            </div>
            <div class="col-md-12 margin-b-5">
                <small>文章摘要<span class="text-danger padding-l-5">*</span>：</small>
                <textarea class="form-control" id="introduction" name="introduction" rows="3" placeholder="请输入文章摘要">${article.introduction}</textarea>
            </div>
            <div class="col-md-12 margin-b-60">
                <small>文章内容<span class="text-danger padding-l-5">*</span>：</small>
                <textarea class="xtl-editor-content" id="textarea1" name="content" placeholder="请输入内容">${article.content}</textarea>
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
<%--webupload JS--%>
<script src="${pageContext.request.contextPath}/resources/js/webupload/webuploader.min.js"></script>
<script>

    /**
     * 图片上传
     */
    uploadImg();

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
            var introduction = $("#introduction").val();
            var content = $("#textarea1").val();

            if(!Common.validate.isNotEnpty(title)){
                Common.toast("error", "请输入文章标题");
                return false;
            }
            if(title.length > 100){
                Common.toast("error", "文章标题限制100个字符");
                return false;
            }
            if(!Common.validate.isNotEnpty(introduction)){
                Common.toast("error", "请输入文章摘要");
                return false;
            }
            if(!Common.validate.isNotEnpty(content)){
                Common.toast("error", "请输入文章内容");
                return false;
            }

            <c:choose>
                <c:when test="${not empty article}">
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/admin/article/update",
                        data: $("#createArticleForm").serialize(),
                        success: function(data){
                            if (data.result == 'success'){
                                Common.toast("success", "保存成功，请重新发布上线");
                                setTimeout(function(){
                                    window.location.href="${pageContext.request.contextPath}/admin/article/manage";
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
                        url: "${pageContext.request.contextPath}/admin/article/create",
                        data: $("#createArticleForm").serialize(),
                        success: function(data){
                            if (data.result == 'success'){
                                Common.toast("success", "新建成功，等待发布上线");
                                setTimeout(function(){
                                    window.location.href="${pageContext.request.contextPath}/admin/article/manage";
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
