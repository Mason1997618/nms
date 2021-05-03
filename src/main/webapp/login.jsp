<%@ page language="java" contentType="text/html; charset=utf-8" 
pageEncoding="utf-8"%> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>登录页面</title>
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="css/login.css">
</head>
<body background="img/background.jpg">
<!--头部区域-->
<header>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="javascript:;">天地一体化网络仿真测试平台</a>
            </div>
            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="register.jsp"> <!-- target="_blank" --><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<form action="${pagecontext.request.getcontextpath}/NetworkSimulation/login.action" method="post">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2 style="color : black">欢迎登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" placeholder="请输入账户名" required autofocus/>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="psw" placeholder="请输入密码" required autofocus/>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <button type="submit" class="btn btn-success col-lg-12" id="login">登录</button>
            </div>
        </div>
    </div>
</form>

<script>
document.getElementById("login").onclick=function () {
    location.href = "detailProject.html";
}
</script>

<script type="text/javascript" src="lib/jquery/jquery.js"></script>
<script type="text/javascript" src="lib/bootstrap/js/bootstrap.js"></script>
</body>
<footer>
    <div class="container">
        <div class="row">
            <p align="center">Copyright &copy; 中电科54所 & 电子科技大学</p>
        </div>
    </div>
</footer><!--页脚结束-->
</html>