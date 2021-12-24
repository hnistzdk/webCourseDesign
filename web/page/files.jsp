<%--
  Created by IntelliJ IDEA.
  User: fengzhu
  Date: 2021/12/24
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link href="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.css" rel="stylesheet"/>
    <script src="//cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="/static/css/dashboard.css" rel="stylesheet">
</head>
<body>
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
                        <li class="nav-item">
                            <a class="${active=='list.html'?'nav-link active':'nav-link'}" href="/page/files.jsp">
                                文件
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">文件上传下载</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <a class="nav-link" href="/user/logout">注销</a>
                    </div>
                </div>
            </div>
            <!-- 上传文件是上传到服务器上，而保存到数据库是文件名 -->
            <!-- 上传文件是以文件转换为二进制流的形式上传的 -->
            <!-- enctype="multipart/form-data"需要设置在form里面，否则无法提交文件 -->
            <form id="fileUploadForm" method="post">
                <table>
                    <tr>
                        <td></td>
                        <td><h1>文件上传</h1></td>
                    </tr>
                    <tr>
                        <td>上传文件:</td>
                        <td><input id="file" type="file" name="file"/></td>
                        <input type="hidden" id="filename" name="filename">
                    </tr>
                    <tr>
                        <td>
                            <button type="button" onclick="upload()" value="上传文件">上传</button>
                        </td>
                    </tr>
                </table>
            </form>

            <div>
                <h2>下载文件</h2>
                <c:forEach items="${fileNameMap}" var="file">
                    <a href="/download/${file.key}">${file.key}</a><br/>
                </c:forEach>
            </div>
        </main>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="/static/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="/static/js/popper.min.js" ></script>
<script src="/static/js/jquery-3.5.1.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/base.js" charset="utf-8"></script>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    let tale = new $.tale();
    function upload() {
        let formData = new FormData();
        let file = document.getElementById("file").files.item(0);
        formData.append("file",file);
        formData.append("filename",file.name);
        $.ajax({
            url: "${path}/fileUpload",
            type: "post",
            processData : false,
            contentType : false,
            data: formData,
            success: function (data) {
                if (data.code == 200) {
                    tale.alertOk({
                        text: data.msg,
                        then: function () {
                            setTimeout(function () {
                                parent.reloadCurrentPage()
                            }, 500);
                        }
                    })
                } else {
                    tale.alertError({
                        text: data.msg,
                        then: function () {
                            setTimeout(function () {
                                parent.reloadCurrentPage()
                            }, 500);
                        }
                    })
                }
            }
        })
    }
</script>
