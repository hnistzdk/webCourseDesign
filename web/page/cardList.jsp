<%--
  Created by IntelliJ IDEA.
  User: fengzhu
  Date: 2021/12/23
  Time: 10:26
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

    <title>首页</title>
    <!-- Bootstrap core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../static/css/dashboard.css" rel="stylesheet">
    <style type="text/css">
        /* Chart.js */

        @-webkit-keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        @keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        .chartjs-render-monitor {
            -webkit-animation: chartjs-render-animation 0.001s;
            animation: chartjs-render-animation 0.001s;
        }
    </style>
</head>

<body>
<div>
    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
        <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">Company name</a>
        <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
        <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/user/logout">注销</a>
            </li>
        </ul>
    </nav>
</div>

<div class="container-fluid">
    <div class="row">
        <div>
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="${active=='main.html'?'nav-link active':'nav-link'}" href="/page/index.jsp">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
                                    <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                                    <polyline points="9 22 9 12 15 12 15 22"></polyline>
                                </svg>
                                首页 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="${active=='list.html'?'nav-link active':'nav-link'}" href="/page/cardList.jsp">
                                我的银行卡
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <h2><a class="btn btn-sm btn-success" href="${path}/card/add">添加银行卡</a></h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>卡号</th>
                        <th>余额(元)</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="card" items="${cards}" varStatus="status">
                        <tr>
                            <td>${card.getId()}</td>
                            <td>${card.getNumber()}</td>
                            <td>${card.getBalance()}</td>
                            <td>
                                <button class="btn btn-sm btn-primary" onclick="editCardDialog(${card.id})">编辑</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteCard(${card.id})">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>


<script type="text/javascript" src="../static/js/jquery-3.2.1.slim.min.js" ></script>
<script type="text/javascript" src="../static/js/popper.min.js" ></script>
<script type="text/javascript" src="../static/plugins/jbolt-admin.js"></script>
<script type="text/javascript" src="../static/plugins/layer/layer.js"></script>
<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/jquery-3.5.1.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/base.js" charset="utf-8"></script>

<script>
    function editCardDialog(id) {
        DialogUtil.openNewDialog({
            title: "编辑银行卡信息",
            width: "50%",
            height: "50%",
            url: "/card/editCard/"+id,
        })
    }

    let tale = new $.tale();
    function deleteCard(id) {
        tale.alertConfirm({
            title: '确定删除该银行卡吗?',
            then: function () {
                $.ajax({
                    url: '/card/deleteCard',
                    type: "post",
                    data: {id: id},
                    success: function (result) {
                        result = JSON.parse(result);
                        if (result && result.code == 200) {
                            tale.alertOkAndReload('删除成功');
                        } else {
                            tale.alertError(result.msg || '删除失败');
                        }
                    }
                });
            }
        });
    }
</script>

</body>
</html>
