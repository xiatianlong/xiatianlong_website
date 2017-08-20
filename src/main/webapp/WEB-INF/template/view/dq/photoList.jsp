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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/webupload/webuploader.css">
       <title>夏天龙 ❤ 杜琪</title>
</head>

<body class="index-body">

<jsp:include page="../basic/dq_navbar.jsp"></jsp:include>
<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 padding-0">
                <div id="fourth" class="bt-buttonBox">
                    <button class="bt-button btn-xtl-dq" id="upload-btn">上传照片</button>
                    <div class="border"></div>
                    <div class="border"></div>
                    <div class="border"></div>
                    <div class="border"></div>
                </div>
            </div>
            <div class="col-xs-12 padding-0 margin-t-20" id="dqPhotosContent">
                <c:choose>
                    <c:when test="${not empty photoList && photoList.size() gt 0}">
                        <c:forEach items="${photoList}" var="photo" varStatus="i">
                            <div class="white-panel item animated zoomIn">
                                <a id="dq-img-box-${i.index}" href="${photo.photoUrl}">
                                    <img class="lazy" src="${photo.photoThumbnailUrl}" data-original="${photo.photoThumbnailUrl}">
                                </a>
                                <div class="dq-photo-memo">${photo.photoMemo}</div>
                                <div class="dq-photo-mark">${photo.createTime}</div>
                                <div class="dq-photo-mark"><c:if test="${photo.delete}"><span class="dq-delete-label">已删除</span></c:if>by ·  ${photo.userName}</div>
                            </div>

                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-xs-12 padding-0 text-c" id="no-photos-content">
                            <img src="${pageContext.request.contextPath}/resources/images/pictures_photos_128px.png" style="width: 15em;height: auto;">
                            <div style="font-family: '楷体';color: cadetblue;font-size: 16px;">还没有照片，快点上传吧！</div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="credit"></div>
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
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div id="filePicker">选择照片</div>
                        </div>
                        <form id="photosForm">
                            <div class="col-md-12 col-sm-12 col-xs-12 padding-0" id="dq-upload-img-content">
                               <%--add file here--%>

                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="saveBtn">确认上传</button>
                </div>
            </div>
        </div>
    </div>
    <%--upload end--%>

</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/mp.mansory.min.js"></script>
<%--webupload JS--%>
<script src="${pageContext.request.contextPath}/resources/js/webupload/webuploader.min.js"></script>
<script>
    $(document).ready(function () {

        // 瀑布流初始化
        if (${not empty photoList && photoList.size() gt 0}){
            $("#dqPhotosContent").mpmansory({
                breakpoints:{lg: 2, md: 2, sm: 3, xs: 4}
            });
        }

        // 懒加载
        $("img.lazy").lazyload({
            placeholder : "${pageContext.request.contextPath}/resource/images/default_img.png",
            effect: "fadeIn"
        });

        // 模态框显示
        $("#upload-btn").click(function(){
            $("#uploadPhotosModal").modal();
        });

        // 模态框必须初始化后进行上传初始化（在模态框中存此此bug）
        $('#uploadPhotosModal').on('shown.bs.modal', function (e) {
            /**
             * 图片上传
             */
            uploadImg();
        });
        // 销毁上传控件
        $('#uploadPhotosModal').on('hide.bs.modal', function (e) {
            uploader.destroy();
        });

        $("#saveBtn").click(function(){

            var flag = true;
            $("input[name='photoName']").each(function(){
                if ($("input[name='photoName']").length ==1 && !Common.validate.isNotEnpty($(this).val())){
                    $(this).css("border", "1px solid #D85A49");
                    Common.toast("warn", "只有一张照片请输入照片描述");
                    flag = false;
                }
                if ($(this).val().length > 50){
                    $(this).css("border", "1px solid #D85A49");
                    Common.toast("warn", "图片描述不能超过50个字");
                    flag = false;
                }
            });
            if(flag){
                $("#saveBtn").attr("disabled", true);
                $("#saveBtn").text("Loading....");
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/dq/photos/add",
                    data: $("#photosForm").serialize(),
                    success: function(data){
                        $("#saveBtn").attr("disabled", false);
                        $("#saveBtn").text("确认上传");
                        if (data.result == 'success'){
                            Common.toast("success", "上传成功");
                            setTimeout(function(){
                                window.location.reload();
                            }, 2000);
                        }else{
                            Common.toast("error", data.message);
                        }
                    }
                });
            }

        });

    });

    var uploader;

    /**
     * 图片上传
     */
    function uploadImg(){
        // 初始化Web Uploader
        uploader = WebUploader.create({

            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '${pageContext.request.contextPath}/resource/images/Uploader.swf',
            // 文件接收服务端。
            server: '${pageContext.request.contextPath}/common/fileUpload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: {
                id:$("#filePicker"), // id
                multiple: true
            },
            fileSingleSizeLimit:5048*1024, //5M
            duplicate:true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,png',
                mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif'
            }
        });
        // 文件上传前校验
        uploader.on( 'beforeFileQueued', function(file) {
            console.log(file);
        });
        // 文件上传成功
        uploader.on( 'uploadSuccess', function(file, data) {
            if (data.result=='success'){
                var html = insertImg(data.fileModel.url, data.fileModel.fileName);
                $("#dq-upload-img-content").append(html);
            }
        });
        // 文件上传失败，显示上传出错。
        uploader.on( 'uploadError', function( file ) {
            console.log(file);
        });
        // 文件上传失败，显示上传出错。
        uploader.on( 'error', function( type ) {
            if(type=='F_EXCEED_SIZE'){
                Common.toast("error", "显示上传文件最大值为5MB!");
            }
        });

        function insertImg(fileUrl,fileName){
            var html = "";
            html += '<div class="col-md-3 col-sm-3 col-xs-4">';
            html += '<img src="'+fileUrl+'" class="dq-show-uploadimg" title="'+fileName+'">';
            html += '<img src="${pageContext.request.contextPath}/resources/images/tip/error_16px.png" class="uploadImg-over-remove-icon" title="remove">';
            html += '<input type="hidden" name="photoUrl" class="dq-photo" value="'+fileUrl+'">';
            html += '<input class="form-control input-sm dq-photo-name" type="text" name="photoName" placeholder="图片描述" value="">';
            html += '</div>';

            return html;
        }
    }

    // 删除图片
    $("#dq-upload-img-content").on('click', '.uploadImg-over-remove-icon', function(){
        // step1: 请求接口删除掉服务器上的图片
        // TODO 有空实现删除服务器上的图片，节省服务器空间
        // step2: 删除页面的图片
        $(this).parent().remove();
    });

</script>
</body>
</html>
