<%--
  Created by IntelliJ IDEA.
  User: xiati
  Date: 2017/5/7
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="basic/head.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/login.css">
    <title>管理员登录</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style="margin-top: 5%;" id="admin-login-content">
            <div class="form-horizontal">
                <span class="heading">管理员登录</span>
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" placeholder="用户名/邮箱">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="密码">
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="valiCode" placeholder="验证码">
                    <i class="fa fa-wrench"></i>
                </div>
                <a href="javascript: Common.reloadValiCode();">
                    <img id="imgcheckCode" src="${pageContext.request.contextPath }/imgCodeServlet">
                </a>
                <div class="form-group">
                    <button id="submitBtn" class="btn btn-default">立即登录</button>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="basic/footer_design.jsp"></jsp:include>

</div>

<jsp:include page="basic/footer.jsp"></jsp:include>
<script>

    $(function(){
        $("#admin-login-content").keydown(function(){
            var evt = window.event || arguments[0];
            if (evt && evt.keyCode == 13) {
                $("#submitBtn").click();
            }
        });
    })

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
            url: "${pageContext.request.contextPath}/admin/login",
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
                        window.location.href="/admin";
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
