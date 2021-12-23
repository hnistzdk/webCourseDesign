<%--
  Created by IntelliJ IDEA.
  User: zdk
  Date: 2021/12/23
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>添加银行卡</title>
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
            <form class="form-horizontal m-t-20" method="post" id="addCardForm" onsubmit="return false">
                <div class="form-group">
                    <span id="numTip" style="display: none; color: red">该卡号已存在</span>
                    <div class="col-xs-12">
                        <label class="col-sm-2 col-form-label">卡号</label>
                        <input class="input-lg input-border" id="number" name="number" oninput="checkCard()" value="" type="text" required="" placeholder="卡号"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <label class="col-sm-2 col-form-label">持卡人真实姓名</label>
                        <input class="input-lg input-border" name="ownerName" value="" type="text" required="" placeholder="持卡人真实姓名"/>
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
<script>
    let tale = new $.tale();
    //编辑角色
    function addCard() {
        $.ajax({
            url:"/card/add",
            type:"post",
            data:$('#addCardForm').serialize(),
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

    function checkCard() {
        let cardNumber = $('#number').val();
        let url = "/card/getCardByCardNum/"+cardNumber;
        $.ajax({
            url:url,
            type:"get",
            success:function (data) {
                console.log('card',data)
                if(data.code != 200){
                    document.getElementById("numTip").style.display='';
                }else {
                    document.getElementById("numTip").style.display='none';
                }
            }
        })
    }
</script>

</body>
</html>
