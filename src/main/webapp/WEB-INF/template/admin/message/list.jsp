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
    <title>留言列表</title>
</head>
<body>

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <%--content begin--%>

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home"></i>  首页</a></li>
            <li class="active">留言列表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-6 padding-0 margin-t-10">
                <form method="post" action="${pageContext.request.contextPath}/admin/message/list" id="submitForm">
                    <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
                    <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
                    <div class="form-group">
                        <input type="text" class="form-control xtl-input" name="keyWord" value="${keyword}" placeholder="昵称、联系方式、内容、地址">
                    </div>
                </form>
            </div>
            <table class="table" style="table-layout:fixed">
                <thead>
                    <tr>
                        <th width="5%">#</th>
                        <th width="15%">留言者昵称</th>
                        <th width="30%">留言内容</th>
                        <th width="15%">联系方式</th>
                        <th width="15%">留言者地址</th>
                        <th width="15%">留言时间</th>
                        <th width="5%">操作</th>
                    </tr>
                </thead>
                <tbody id="detailList">
                    <c:forEach items="${list}" var="message" varStatus="status">
                        <tr>
                            <th class="xtl-text-overflow">${status.index + 1 + ((pageNo -1) * pageSize) }</th>
                            <td class="xtl-text-overflow">${message.userName}</td>
                            <td class="xtl-text-overflow">${message.content}</td>
                            <td class="xtl-text-overflow">${message.contactInfomation}</td>
                            <td class="xtl-text-overflow">${message.ipAddress}</td>
                            <td class="xtl-text-overflow">${message.createTime}</td>
                            <td>
                                <button type="button" class="btn btn-default btn-xs message-view-btn"
                                        <c:if test="${!message.read}">style="color:rgba(161,40,40,1);border-color:rgba(161,40,40,1) " </c:if>
                                        messageId="${message.id}">查看</button>
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

    <%--message-modal begin --%>
    <div class="modal fade" id="message-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="view-message-username"></h4>
                </div>
                <div class="modal-body">

                    <p id="view-message-contact">13451108847</p>

                    <p id="view-message-address">上海市闵行区</p>

                    <p id="view-message-content">安心as西安市小as西安市小as</p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <%--message-modal end--%>




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

    $(".message-view-btn").on('click', function(){

        $("#view-message-username").empty();
        $("#view-message-contact").empty();
        $("#view-message-address").empty();
        $("#view-message-content").empty();

        var messageId = $(this).attr("messageId");

        console.log(messageId);
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/message/view/"+messageId,
            success: function(data){
                if (data.result == 'success'){

                    $("#view-message-username").html(data.data.userName);
                    if (Common.validate.isNotEnpty(data.data.contactInfomation)){
                        $("#view-message-contact").html("联系方式："+data.data.contactInfomation);
                    }
                    if (Common.validate.isNotEnpty(data.data.ipAddress)){
                        $("#view-message-address").html("ip地址："+data.data.ipAddress);
                    }
                    $("#view-message-content").html("留言内容："+data.data.content);

                    // 调起model框
                    $('#message-modal').modal('show');

                }else{
                    Common.toast("error", data.message);
                }
            }
        });
    });

</script>
</body>
</html>
