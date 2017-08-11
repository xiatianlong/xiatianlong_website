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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.eeyellow.Timeline.css">
    <title>关于   记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">

            <div class="col-md-12 padding-0">
                <div class="col-md-3 col-sm-5">
                    <img src="${pageContext.request.contextPath}/resources/images/xiatianlong.jpg" class="animated zoomIn" id="about-xiatianlong-img">
                    <div class="text-c">
                        <span class="xtl-message-btn" data-toggle="modal" data-target="#addMessageModal" data-backdrop="static">向我留言</span>
                    </div>
                </div>

                <div class="col-md-9 col-sm-7">
                    <blockquote class="xtl-blockquote"><span>关于我</span></blockquote>
                    <div class="xtl-about-text margin-b-30">
                        <p class="xtl-about-text-item animated fadeInRight">一枚入职两年的初级程序员</p>
                        <p class="xtl-about-text-item animated fadeInRight">一枚想成为全栈的初级java软件工程师</p>
                        <p class="xtl-about-text-item animated fadeInRight">一个怀揣着梦想而又享受平凡生活的年轻人</p>
                        <p class="xtl-about-text-item animated fadeInRight">一个每天两点一线且周末死宅默默努力的屌丝沪漂</p>
                    </div>

                    <blockquote class="xtl-blockquote"><span>关于网站</span></blockquote>
                    <div class="xtl-about-text margin-b-30">
                        <p class="xtl-about-text-item animated fadeInRight">建站目的一是为了记录工作、生活中的点滴，更是为了作为学习用途。</p>
                        <p class="xtl-about-text-item animated fadeInRight">网站采用主流框架SSH，前端采用bootstrap。</p>
                        <p class="xtl-about-text-item animated fadeInRight">建站主要是为了学习，所以在功能上暂时没有方向，想到什么就做什么，想做什么就做什么。</p>
                        <p class="xtl-about-text-item animated fadeInRight">会不定期更新网站内容或者功能，具体周期视工作繁忙程度决定。</p>
                        <p class="xtl-about-text-item animated fadeInRight">限制于水平问题，可能会有不确定性bug出现，欢迎向我留言，不胜感激。限制于审美，UI有宝贵建议请不吝赐教。</p>
                        <p class="xtl-about-text-item animated fadeInRight">大家有任何功能上的建议欢迎提出，极有可能会在下个版本中发布上线。</p>
                    </div>

                    <blockquote class="xtl-blockquote"><span>座右铭</span></blockquote>
                    <div class="xtl-about-text margin-b-30">
                        <p class="xtl-about-text-item animated fadeInRight" style="color: rgba(161,40,40,0.8);;">既然选择了远方，便只顾风雨兼程。</p>
                    </div>

                    <blockquote class="xtl-blockquote"><span>联系方式</span></blockquote>
                    <div class="xtl-about-text margin-b-30">
                        <p class="xtl-about-text-item animated fadeInRight">QQ：675500969</p>
                        <p class="xtl-about-text-item animated fadeInRight">邮箱：xiatianlong@hotmail.com</p>
                    </div>
                </div>
            </div>

        </div>

        <jsp:include page="../basic/footer_design.jsp"></jsp:include>

    </div>
    <%--content end--%>

    <%--add message begin--%>
    <div class="modal fade" id="addMessageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">留言</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="message-name" class="control-label">您的昵称/名字<span style="color: #A12828;">&nbsp;*</span>:</label>
                            <input type="text" class="form-control" id="message-name">
                        </div>
                        <div class="form-group">
                            <label for="message-contact" class="control-label">您的联系方式:</label>
                            <input type="text" class="form-control" id="message-contact" placeholder="电话/QQ/微信...">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="control-label">内容<span style="color: #A12828;">&nbsp;*</span>:</label>
                            <textarea class="form-control" id="message-text" placeholder="限制500字"></textarea>
                            <div class="text-r"><span id="message-content-count">0/500</span></div>

                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="messageSubmitBtn">发送留言</button>
                </div>
            </div>
        </div>
    </div>
    <%--add message end--%>

</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script>
    $(document).ready(function () {
        //投票具体意见最大输入200字
        $("#message-text").on("input",function(){
            var value = $("#message-text").val();
            if(value.length>500){
                $("#message-text").val(value.substring(0,500));
            }
            var count = $("#message-text").val().length;
            $("#message-content-count").text(""+count+"/500");
        });

        $("#messageSubmitBtn").on('click', function(){

            var name = $("#message-name").val();
            var contact = $("#message-contact").val();
            var content = $("#message-text").val();

            if (!Common.validate.isNotEnpty(name)){
                Common.toast("error", "请输入您的昵称");
                return false;
            }
            if (name.length > 20){
                Common.toast("error", "昵称限制在20个字符以内");
                return false;
            }
            if (Common.validate.isNotEnpty(contact) && name.length > 50){
                Common.toast("error", "联系方式限制在50个字符以内");
                return false;
            }
            if (!Common.validate.isNotEnpty(content)){
                Common.toast("error", "请输入您留言的内容");
                return false;
            }

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/about/addMessage",
                data: {
                    ip: returnCitySN.cip,
                    ipAddress: returnCitySN.cname,
                    userName: name,
                    contactInfomation: contact,
                    content: content
                },
                success: function(data){
                    if (data.result == 'success'){
                        Common.toast("success", "留言成功，感谢！");
                        $('#addMessageModal').modal('hide');
                        $("#message-name").val('');
                        $("#message-contact").val('');
                        $("#message-text").val('');
                        $("#message-content-count").text("0/500");
                    }else{
                        Common.toast("error", data.message);
                    }
                }

            });

        });

    });





</script>
</body>
</html>
