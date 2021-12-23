<%--
  Created by IntelliJ IDEA.
  User: fengzhu
  Date: 2021/12/23
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dashboard Template for Bootstrap</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
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
        <!--				commons.html中的sidebar组件-->
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
            <form action="/card/updateCard" method="post">
                <input type="hidden" value="${employee.getId()}" name="id">
                <div class="form-group">
                    <label>LastName</label>
                    <input value="${employee.getLastName()}" type="text" name="lastName" class="form-control">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input value="${employee.getEmail()}" type="email" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label>Gender</label>
                    <div class="form-check form-check-inline">
                        <input checked="${employee.getGender()==1}" class="form-check-input" type="radio" name="gender" value="1">
                        <label class="form-check-label">男</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input checked="${employee.getGender()==0}" class="form-check-input" type="radio" name="gender" value="0">
                        <label class="form-check-label">女</label>
                    </div>
                </div>
                <div class="form-group">
                    <label>Department</label>
                    <select class="form-control" name="department.id">
                        <option selected="${department.getId()==employee.getDepartment().getId()}"
                                each="department:${departments}"
                                text="${department.getDepartmentName()}"
                                value="${department.getId()}"></option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">修改</button>
            </form>
        </main>
    </div>
</div>

<script type="text/javascript" src="../static/js/jquery-3.2.1.slim.min.js" ></script>
<script type="text/javascript" src="../static/js/popper.min.js" ></script>
<script type="text/javascript" src="../static/js/bootstrap.min.js" ></script>

<!-- Icons -->
<script type="text/javascript" src="../static/js/feather.min.js" ></script>
<script>
    feather.replace()
</script>

<!-- Graphs -->
<script type="text/javascript" src="../static/js/Chart.min.js" ></script>
<script>
    var ctx = document.getElementById("myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            datasets: [{
                data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false,
            }
        }
    });
</script>

</body>
</html>
