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

<!-- Bootstrap core JS -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<%--<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

<!-- jquery ui -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<%--<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js"></script>--%>

<%-- Toast JS --%>
<script src="${pageContext.request.contextPath}/resources/js/overhang/overhang.min.js"></script>

<%-- Page js--%>
<script src="${pageContext.request.contextPath}/resources/js/pageList/jquery.pagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageList/op.pagination.js"></script>

<%--Common JS--%>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>




<%--去顶部的实现 begin--%>
<div id="xtl-gotop">
    <div class="xtl-gotop-item" id="xtl-gotop-up">
        <i class="fa fa-chevron-up" aria-hidden="true"></i>
    </div>
    <div class="xtl-gotop-item" id="xtl-gotop-wechat">
        <i class="fa fa-weixin" aria-hidden="true"></i>
        <img src="${pageContext.request.contextPath}/resources/images/wechat.png">
    </div>
</div>
<script>
    $(window).scroll(function() {
        if($(this).scrollTop() > 100) {
            $("#xtl-gotop").fadeIn();
        } else {
            $("#xtl-gotop").fadeOut();
        }
    });
    $("#xtl-gotop-up").click(function() {
        $("body,html").animate({scrollTop:0},400);
    });
</script>
<%--去顶部的实现 end--%>

<%--<!-- JiaThis Button BEGIN -->--%>
<%--<script type="text/javascript" >--%>
    <%--var jiathis_config={--%>
        <%--data_track_clickback:true,--%>
        <%--siteNum:5,--%>
        <%--sm:"email,tsina,weixin,qzone,ydnote",--%>
        <%--summary:"",--%>
        <%--showClose:false,--%>
        <%--shortUrl:false,--%>
        <%--hideMore:true--%>
    <%--}--%>
<%--</script>--%>
<%--<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jiathis_r.js?uid=2115028&btn=r.gif&move=0" charset="utf-8"></script>--%>
<%--<!-- JiaThis Button END -->--%>




