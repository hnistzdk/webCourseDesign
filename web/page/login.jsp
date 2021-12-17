<%--
  Created by IntelliJ IDEA.
  User: fengzhu
  Date: 2021/12/17
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录页</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/signin.css" rel="stylesheet">
</head>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<body class="text-center">
<form id="loginForm" class="form-signin" onsubmit="return false">
    <img class="mb-4" src="${path}/static/img/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal" content="请登录">请登录</h1>
    <!--			如果msg值不为空才不显示消息-->
    <input type="hidden" name="requestMethod" value="login">
    <p style="color: red" content="${msg}" c:if="${not empty msg}"></p>
    <label class="sr-only" content="">Username</label>
    <input type="text" name="username" class="form-control" placeholder="请输入用户名" required="required" autofocus="">
    <label class="sr-only" content="">Password</label>
    <input type="password" name="password" class="form-control" placeholder="请输入密码" required="required">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="rememberMe" value="remember-me">记住我
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="button" onclick="checkForm()">登录</button>
</form>
</body>
</html>
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.5.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.js"></script>
<script type="text/javascript" src="<c:url value="/static/js/base.js"/>"></script>
<script>
    let tale = new $.tale();
    function checkForm() {
        $.ajax({
            url:"${path}/user",
            type:"post",
            data:$('#loginForm').serialize(),
            success:function (data) {
                if(data.code===200){
                    tale.alertOk({
                        text: data.msg,
                        then: function () {
                            setTimeout(function () {
                                window.location.href = '/admin/index';
                            }, 500);
                        }
                    })
                }else{
                    tale.alertError(data.msg)
                }
            }
        })
    }
</script>
