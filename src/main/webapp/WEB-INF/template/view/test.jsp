<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/8/3
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <style>
        .bar {
            height: 18px;
            background: green;
        }
    </style>
</head>
<script>
    /**
     * 判断是否是微信内置浏览器
     *
     * @returns boolean
     */
    function isWeiXin() {
        var ua = window.navigator.userAgent;
        return ua.toLowerCase().indexOf("micromessenger") > 0;
    }
</script>
<body>
<input id="fileupload" type="file" accept="video/*" capture="camcorder" name="file">



<%--<script type="text/javascript">--%>

    <%--if (/android/.test(navigator.userAgent.toLowerCase()) && !isWeiXin()) {--%>
        <%--document.write('<input id="filePicker" class="hidden" type="file" name="file" accept="image/*;capture=camera" onchange="handleFiles(this)" multiple="multiple" />');--%>
    <%--}else if(/android/.test(navigator.userAgent.toLowerCase()) && isWeiXin()){--%>
        <%--document.write('<input id="filePicker" class="hidden" type="file" name="file" accept="image/*" capture="camera" onchange="handleFiles(this)" multiple="multiple" />');--%>
    <%--}else{--%>
        <%--//解决IOS10.3在微信下上传照片只能拍照不能选择文件问题。--%>
        <%--document.write('<input id="filePicker" class="hidden" type="file" name="file" accept="image/jpg,image/jpeg,image/png" onchange="handleFiles(this)" multiple="multiple" />');--%>
    <%--}--%>
<%--</script>--%>


<button id="uploadBtn">upload</button>
<div id="progress">
    <div class="bar" style="width: 0%;"></div>
</div>


<div id="previewt-video" style="display: none;">
</div>



<div id="result-video" style="display: none;">
    上传成功返回的视屏：
    <video src="" width="320" height="240" controls="controls">
        你的浏览器不支持HTML5的video播放器
    </video>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/vedioUpload/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/vedioUpload/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/vedioUpload/jquery.fileupload.js"></script>
<script>

    var $upload;

    var overallProgress = $('#fileupload').fileupload({
        url:'${pageContext.request.contextPath}/common/fileUpload',
        type: 'POST',
        dataType: 'json',
        fileInput: $('input:file'),
        paramName: 'file',
        autoUpload: false,
        singleFileUploads: true,
        /* ... */
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                    'width',
                    progress + '%'
            );
        }

    });

    $('#fileupload')
            .bind('fileuploadadd', function (e, data) {
                $upload = data;
            })
            .bind('fileuploadsubmit', function (e, data) {
                console.log("submit");
                console.log(data);
            })
            .bind('fileuploadsend', function (e, data) {
                console.log("send");
                console.log(data);
            })
            .bind('fileuploaddone', function (e, data) {
                console.log("done");
                console.log(data);

                console.log("done ... ");

                console.log(data.result.fileModel);
                $("#result-video").show();
                $("#result-video").before(data.result.fileModel.url);
                $("#result-video > video").prop("src", data.result.fileModel.url);
            })
            .bind('fileuploadfail', function (e, data) {
                console.log("file");
                console.log(data);
            })
            .bind('fileuploadalways', function (e, data) {
                console.log("always");
                console.log(data);
            })
            .bind('fileuploadprogress', function (e, data) {
                console.log("progress");
                console.log(data);
            })
            .bind('fileuploadprogressall', function (e, data) {
                console.log("progressall");
                console.log(data);
            })
            .bind('fileuploadstart', function (e, data) {
                console.log("start");
                console.log(data);
            })
            .bind('fileuploadstop', function (e, data) {
                console.log("stop");
                console.log(data);
            })
            .bind('fileuploadchange', function (e, data) {
                console.log("change");
                console.log(data);

                $("#previewt-video").show();
                $("#previewt-video").text(data.files[0].name);
            })
            .bind('fileuploadpaste', function (e, data) {
                console.log("paste");
                console.log(data);
            })
            .bind('fileuploadchunksend', function (e, data) {
                console.log("chuck send");
                console.log(data);
            })
            .bind('fileuploadchunkdone', function (e, data) {

                console.log("chunk done");
                console.log(data);


            })
            .bind('fileuploadchunkfail', function (e, data) {
                console.log("chunk fail");
                console.log(data);
            })
            .bind('fileuploadchunkalways', function (e, data) {
                console.log("chunk always");
                console.log(data);
            });


    $("#uploadBtn").on('click', function(){
        $upload.submit();

    });
</script>
</body>
</html>
