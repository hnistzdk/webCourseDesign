<%--
  Created by IntelliJ IDEA.
  User: fengzhu
  Date: 2021/12/17
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录页</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/signin.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <!--    <link th:href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css}" rel="stylesheet" type="text/css"/>-->
    <link href="/static/css/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.css" rel="stylesheet"/>
    <script src="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
</head>
<body class="text-center">
<form id="loginForm" class="form-signin" onsubmit="return false">
    <img class="mb-4" src="../static/img/logo.jpg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal" content="请登录">网上银行系统</h1>
    <input type="hidden" name="requestMethod" value="login">
    <c:if test="${not empty notLoginMsg}">
        <span style="color: red">${notLoginMsg}</span>
    </c:if>
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
<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/jquery-3.5.1.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/base.js" charset="utf-8"></script>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script>
    let tale = new $.tale();
    function checkForm() {
        $.ajax({
            url:"${path}/user",
            type:"post",
            data:$('#loginForm').serialize(),
            success:function (data) {
                data = JSON.parse(data);
                console.log(data)
                if(data.code == 200){
                    tale.alertOk({
                        text: data.msg,
                        confirmButtonText:'确认',
                        cancelButtonText:'取消',
                        then: function () {
                            setTimeout(function () {
                                window.location.href = '${path}/page/index.jsp';
                            }, 500);
                        }
                    })
                }else{
                    tale.alertError({
                        text:data.msg,
                        confirmButtonText:'确认',
                        cancelButtonText:'取消'
                    })
                }
            }
        })
    }
</script>
