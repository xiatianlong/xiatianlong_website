<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/3/17
  Time: 10:38
  To change this template use File | Settings | File Templates.

  CDN : http://www.bootcdn.cn/

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- jquery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<%--<script src="https://cdn.bootcss.com/jquery/2.2.2/jquery.min.js"></script>--%>

<!-- jquery ui -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<%--<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js"></script>--%>

<!-- Bootstrap core JS -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<%--<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

<!-- wangEditor core JS -->
<script src="${pageContext.request.contextPath}/resources/js/wangEditor-2.1.23/wangEditor.min.js"></script>
<%--<script src="https://cdn.bootcss.com/wangeditor/2.1.20/js/wangEditor.min.js"></script>--%>

<%-- Page js--%>
<script src="${pageContext.request.contextPath}/resources/js/pageList/jquery.pagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageList/op.pagination.js"></script>

<%-- Toast JS --%>
<script src="${pageContext.request.contextPath}/resources/js/overhang/overhang.min.js"></script>

<%--Common JS--%>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>


<%--admin menu init begin--%>
<script>
    $(document).ready(function () {
        $('#navbox-trigger').click(function () {
            return $('#top-bar').toggleClass('navbox-open');
        });
        return $(document).on('click', function (e) {
            var $target;
            $target = $(e.target);
            if (!$target.closest('.navbox').length && !$target.closest('#navbox-trigger').length) {
                return $('#top-bar').removeClass('navbox-open');
            }
        });
    });

</script>
<%--admin menu init end--%>

<%--Toast Begin--%>
<script>

    /**
     * 图片上传
     */
    function uploadImg(){
        // 初始化Web Uploader
        var uploader = WebUploader.create({

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
                multiple: false
            },
            fileSingleSizeLimit:2048*1024,
            duplicate:true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 文件上传前校验
        uploader.on( 'beforeFileQueued', function(file) {
            console.log(file);
            // 如果画面存在图片div，即不允许再上传（编辑的时候）
            if($(".uploadImg-over-div").length >= 1){
                Common.toast("warn", "只允许上传一张图片");
                return false;
            }
        });
        // 文件上传成功
        uploader.on( 'uploadSuccess', function(file, data) {
            if (data.result=='success'){
                var html = insertImg(data.fileModel.url, data.fileModel.fileName);
                $("#fileList").append(html);
            }
        });
        // 文件上传失败，显示上传出错。
        uploader.on( 'uploadError', function( file ) {
            console.log(file);
        });
        // 文件上传失败，显示上传出错。
        uploader.on( 'error', function( type ) {
            if(type=='F_EXCEED_SIZE'){
                Common.toast("error", "显示上传文件最大值为2MB!");
            }
        });

        function insertImg(fileUrl,fileName){
            var html = "";
            html+='<div class="uploadImg-over-div">';
            html+='<img src="'+fileUrl+'" class="uploadImg-over-show" title="'+fileName+'">';
            html+='<img src="${pageContext.request.contextPath}/resources/images/tip/error_16px.png" class="uploadImg-over-remove-icon" title="remove">';
            html+='<input type="hidden" name="xtlFile" class="xtl-file" value="'+fileUrl+'">';
            html+='</div>';
            return html;
        }
    }

    // 删除图片
    $("div.uploader-list").on('click', '.uploadImg-over-remove-icon', function(){
        // step1: 请求接口删除掉服务器上的图片
            // TODO 有空实现删除服务器上的图片，节省服务器空间
        // step2: 删除页面的图片
        $(this).parent().remove();
    });

</script>
<%--Toast end--%>