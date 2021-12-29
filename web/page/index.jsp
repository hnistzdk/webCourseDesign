<%--
  Created by IntelliJ IDEA.
  User: zdk
  Date: 2021/12/17
  Time: 19:42
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
<c:set var="path" value="${pageContext.request.contextPath}"/>
<body>
<div class="container-fluid">
    <div class="row">
        <div>
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="${active=='main.html'?'nav-link active':'nav-link'}" href="/page/index.jsp">
                                首页 <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="${active=='list.html'?'nav-link active':'nav-link'}" href="${path}/card/list">
                                我的银行卡
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="${active=='list.html'?'nav-link active':'nav-link'}" href="/fileList">
                                文件
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
                </div>
                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
                </div>
            </div>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">网上银行管理系统</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <a class="nav-link" href="/user/logout">注销</a>
                    </div>
                </div>
            </div>
            <canvas class="my-4 chartjs-render-monitor" style="display: block;background: url('/static/img/bg1.jfif');width: 1200px; height: 590px;"></canvas>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="../static/js/jquery-3.2.1.slim.min.js" ></script>
<script type="text/javascript" src="../static/js/popper.min.js" ></script>
<script type="text/javascript" src="../static/js/bootstrap.min.js" ></script>

<!-- Icons -->
<script type="text/javascript" src="../static/js/feather.min.js" ></script>
<script>
    feather.replace()
</script>

</body>

</html>
