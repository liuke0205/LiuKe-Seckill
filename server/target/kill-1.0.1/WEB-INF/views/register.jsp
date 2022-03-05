<%--
  Created by IntelliJ IDEA.
  User: steadyjack
  Date: 2019/5/20
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tag.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>商城高并发抢购-用户注册</title>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1 align="center">用户注册</h1>

            <form action="${ctx}/register" method="post">
                <table align="center" border="0" style="border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td>用户名:</td>
                        <td><input type="text" name="userName" value="${userName}"></td>
                    </tr>
                    <tr>
                        <td>邮箱:</td>
                        <td><input type="text" name="email" value="${email}"></td>
                    </tr>
                    <tr>
                        <td>密&nbsp;&nbsp;码:</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="注册" name="register"></td>
                        <td><input type="reset" value="重置" name="reset"></td>
                        <td><a href="${ctx}/to/login" style="margin-left: -130px;">有账号，前往登录</a></td>
                    </tr>
                </table>
            </form>

            <h2 align="center">${userName}&nbsp;&nbsp;&nbsp;<br/>${errorMsg}</h2>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/static/plugins/jquery.js"></script>
<script src="${ctx}/static/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${ctx}/static/plugins/jquery.cookie.min.js"></script>
<script src="${ctx}/static/plugins/jquery.countdown.min.js"></script>
</html>
















