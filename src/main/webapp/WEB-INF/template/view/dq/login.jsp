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
    <title>夏天龙的个人网站，记录、学习与分享</title>

</head>

<body class="index-body">

<jsp:include page="../basic/navbar.jsp"></jsp:include>

<div>
    <%--content begin--%>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 padding-0">
                <div id="dq-login-content">
                    <div class="input-group margin-b-10">
                        <span class="input-group-addon xtl-index-input"><i class="fa fa-user-o"></i></span>
                        <input type="text" class="form-control xtl-index-input" name="userName" id="userName" placeholder="用户名/邮箱">
                    </div>
                    <div class="input-group col-xs-12 margin-b-10">
                        <span class="input-group-addon xtl-index-input"><i class="fa fa-lock"></i></span>
                        <input type="password" class="form-control xtl-index-input" name="password" id="password" placeholder="输入密码">
                    </div>
                    <div class="input-group col-xs-12 margin-b-10">
                        <span class="input-group-addon xtl-index-input"><i class="fa fa-wrench"></i></span>
                        <input type="text" class="form-control xtl-index-input" name="valiCode" id="valiCode" placeholder="验证码">
                        <span class="input-group-addon xtl-index-input padding-b-5" onclick="Common.reloadValiCode();">
                            <img id="imgcheckCode" src="${pageContext.request.contextPath }/imgCodeServlet">
                        </span>
                    </div>
                    <div class="input-group col-xs-12 margin-b-10">
                        <a class="btn btn-info btn-sm float-r" id="submitBtn"><i class="fa fa-paper-plane-o"></i> &nbsp;提&nbsp;交</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--content end--%>
</div>

<jsp:include page="../basic/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $("#dq-login-content").keydown(function(){
            var evt = window.event || arguments[0];
            if (evt && evt.keyCode == 13) {
                $("#submitBtn").click();
            }
        });
    });

    $("#submitBtn").on('click', function(){

        var userName = $("#userName").val();
        var password = $("#password").val();
        var valiCode = $("#valiCode").val();

        if(!Common.validate.isNotEnpty(userName)){
            Common.toast("error", "请输入用户名/邮箱");
            return false;
        }
        if (!Common.validate.isNotEnpty(password)){
            Common.toast("error", "请输入密码");
            return false;
        }
        if (!Common.validate.isNotEnpty(valiCode)){
            Common.toast("error", "请输入验证码");
            return false;
        }

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/dq/login",
            data: {
                userName: userName,
                password: password,
                valiCode: valiCode
            },
            success: function(data){
                Common.reloadValiCode();
                if (data.result == 'success'){
                    Common.toast("success", "登录成功");
                    if (Common.validate.isNotEnpty("${reqUrl}")){
                        window.location.href="${reqUrl}";
                    }else{
                        window.location.href="${pageContext.request.contextPath}/dq/index";
                    }
                }else{
                    Common.toast("error", data.message);
                }
            }
        });

    });

</script>
</body>
</html>
