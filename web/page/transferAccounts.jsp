<%--
  Created by IntelliJ IDEA.
  User: zdk
  Date: 2021/12/23
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>转账</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/signin.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <!--    <link th:href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css}" rel="stylesheet" type="text/css"/>-->
    <link href="/static/css/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.css" rel="stylesheet"/>
    <script src="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <form class="form-horizontal m-t-20" method="post" id="transferAccountsForm" onsubmit="return false">
                <div class="form-group">
                    <div class="col-xs-12">
                        <label class="col-sm-2 col-form-label">源卡号</label>
                        <input class="input-lg input-border" id="origin" name="origin" value="" type="text" required="" placeholder="源卡号"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <label class="col-sm-2 col-form-label">目的卡号</label>
                        <input class="input-lg input-border" id="target" name="target" value="" type="text" required="" placeholder="目的卡号"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <label class="col-sm-2 col-form-label">转账金额</label>
                        <input class="input-lg input-border" name="money" value="" type="text" required="" placeholder="转账金额"/>
                    </div>
                </div>
            </form>
            <div class="form-group text-center">
                <button type="button" onclick="addCard()" class="btn btn-primary">提交</button>
            </div>
        </main>
    </div>
</div>

<script type="text/javascript" src="/static/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="/static/js/popper.min.js" ></script>
<script src="/static/js/jquery-3.5.1.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/base.js" charset="utf-8"></script>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    let tale = new $.tale();
    //编辑角色
    function addCard() {
        $.ajax({
            url:"${path}/card/transferAccounts",
            type:"post",
            data:$('#transferAccountsForm').serialize(),
            success:function (data) {
                if(data.code == 200){
                    tale.alertOk({
                        text:data.msg,
                        then:function () {
                            setTimeout(function () {
                                parent.reloadCurrentPage()
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

</body>
</html>
