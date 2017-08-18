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

    <jsp:include page="../basic/dq_head.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/imageViewer/imageviewer.css">
    <title>夏天龙 ❤ 杜琪</title>

</head>

<body class="index-body">

<jsp:include page="../basic/dq_navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 padding-0">
                <button type="button" class="btn btn-xtl-dq" id="upload-btn">上传照片</button>
            </div>
            <div class="col-xs-12 padding-0" id="dqPhotosContent">
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/xiatianlong.jpg" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/wechat.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_96px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_128px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_128px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/banner.jpg" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/site-bg.jpg" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_72px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_256px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/index-head.jpg" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_64px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/index-head.jpg" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
                <div class="white-panel item animated zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/images/icon/xterm_color_x_64px.png" class="gallery-items">
                    <div>xxxxxxxxxxxx</div>
                </div>
            </div>
        </div>
    </div>
    <%--content end--%>

    <%--upload begin--%>
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="uploadPhotosModalLabel" data-backdrop="static" id="uploadPhotosModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="uploadPhotosModalLabel">上传照片</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-9">
                            Level 1: .col-sm-9
                            <div class="row">
                                <div class="col-xs-8 col-sm-6">
                                    Level 2: .col-xs-8 .col-sm-6
                                </div>
                                <div class="col-xs-4 col-sm-6">
                                    Level 2: .col-xs-4 .col-sm-6
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">确认</button>
                </div>
            </div>
        </div>
    </div>
    <%--upload end--%>

</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/mp.mansory.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/imageViewer/imageviewer.min.js"></script>

<script>
    $(document).ready(function () {

        $("#dqPhotosContent").mpmansory({
            breakpoints:{lg: 2, md: 2, sm: 3, xs: 4}
        });

        var viewer = ImageViewer();
        $('.gallery-items').click(function () {
            var imgSrc = $(this).attr("src");
            viewer.show(imgSrc, imgSrc);
        });

        $("#upload-btn").click(function(){
            $("#uploadPhotosModal").modal();
        });

    });
</script>
</body>
</html>
