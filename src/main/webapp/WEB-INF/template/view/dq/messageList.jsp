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
                    <button class="bt-button btn-xtl-dq" id="addMessage-btn">添加留言</button>
                    <div class="border"></div>
                    <div class="border"></div>
                    <div class="border"></div>
                    <div class="border"></div>
                </div>
            </div>

            <div class="col-xs-12 padding-0 margin-t-20" id="dqMessageContent">
                <c:if test="${not empty messageList && messageList.size() gt 0}">
                    <c:forEach items="${messageList}" var="message">
                        <div class="white-panel item animated zoomIn" id="mesaageCard-${message.id}">
                            <div class="messageItemContent" userName="${message.userName}" createTime="${message.createTime}">${message.content}</div>
                            <div class="dq-photo-mark">${message.createTime}</div>
                            <div class="dq-photo-mark"><c:if test="${message.showDelete}"><span class="dq-delete-label">已删除</span></c:if>by ·  ${message.userName}</div>
                            <input type="hidden" value="${message.id}" class="messageId">
                        </div>
                    </c:forEach>
                </c:if>
            </div>

        </div>
    </div>
    <%--content end--%>

    <%--message modal begin--%>
    <div class="modal fade" tabindex="-1" id="dqMessageModal" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加留言</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <textarea id="dq-message-textarea" placeholder="请输入留言内容"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="padding-r-15" id="dq-message-textarea-count">0/2000</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="dqMessageSubmitBtn">提交</button>
                </div>
            </div>
        </div>
    </div>
    <%--message modal end--%>

    <%--show message modal begin--%>
    <div class="modal fade" tabindex="-1" id="dqMessageShowModal" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="dqMessageShowName"></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12 padding-0">
                            <pre id="dqMessageShowContent"></pre>
                            <div class="text-r padding-r-15" id="dqMessageShowCreateTime"></div>
                            <a href="javascript:;" id="messageRemoveBtn">删除留言</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--show message modal end--%>

</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/mp.mansory.min.js"></script>
<script>
    $(document).ready(function () {

        $("#dqMessageContent").mpmansory({
            breakpoints:{lg: 3, md: 3, sm: 4, xs: 6}
        });

        $("#addMessage-btn").click(function(){
            $("#dqMessageModal").modal();
        });

        //投票具体意见最大输入200字
        $("#dq-message-textarea").on("input",function(){
            var value = $("#dq-message-textarea").val();

            if(value.length>200){
                $("#dq-message-textarea").val(value.substring(0,2000));
            }
            var count = $("#dq-message-textarea").val().length;
            $("#dq-message-textarea-count").text(""+count+"/2000");
        });

        // 添加消息提交
        $("#dqMessageSubmitBtn").click(function(){
            var message = $("#dq-message-textarea").val();
            if(!Common.validate.isNotEnpty(message)){
                Common.toast("error", "留言内容不可以为空");
                return;
            }
            $("#dqMessageSubmitBtn").attr("disabled", true);
            $("#dqMessageSubmitBtn").text("Loading....");
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/dq/message/add",
                data: {
                    content: message
                },
                success: function(data){
                    $("#dqMessageSubmitBtn").attr("disabled", false);
                    $("#dqMessageSubmitBtn").text("提交");
                    if (data.result == 'success'){
                        Common.toast("success", "提交成功");
                        setTimeout(function(){
                            window.location.reload();
                        }, 2000);
                    }else{
                        Common.toast("error", data.message);
                    }
                }
            });

        });

        // 打开模态框口 展示详情
        $("#dqMessageContent .item").click(function(){
            $("#dqMessageShowContent").text('');
            $("#dqMessageShowContent").text($(this).find(".messageItemContent").text());
            $("#dqMessageShowCreateTime").text("留言时间：" + $(this).find(".messageItemContent").attr("createTime"));
            $("#dqMessageShowName").text($(this).find(".messageItemContent").attr("userName"));
            $("#messageRemoveBtn").attr("messageId", $(this).find("input.messageId").val());
            $("#dqMessageShowModal").modal();
        });

        // 删除message提交
        $("#messageRemoveBtn").click(function(){
            var messageId = $(this).attr("messageId");
            Common.confirm("确定要删除该留言吗？", function(){

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/dq/message/"+messageId+"/remove",
                    success: function(data){

                        if (data.result == 'success'){
                            Common.toast("success", "提交成功");
                            $("#dqMessageShowModal").modal('hide');
                            $("#mesaageCard-"+messageId).fadeOut(300, function(){
                                $("#dqMessageContent").mpmansory({
                                    breakpoints:{lg: 3, md: 3, sm: 4, xs: 6}
                                });
                            });
                        }else{
                            Common.toast("error", data.message);
                        }
                    }
                });


            })

        });

    });
</script>
</body>
</html>
