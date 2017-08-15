<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	h2{
		left:50px;
	}
	.titl{
		position:relative;
		left:250px;
	}
	.myform{
		position:relative;
		left:200px;
		top:190px;
	}
	.foot{
		position:absolute;
		left:500px;
		bottom: 0px;
	}
</style>

<title>Login</title>

</head>
<body style="background: url(/os04/image/oa_bg2.jpg) ">
<div class="titl">
	<h1 class="titl"><span class="label label-default ">johe办公系统</span></h1>
</div>

<form action="/os04/doLogin.do" method="POST" class="form-horizontal myform" role="form">
<div class="form-group">
	<label class="col-sm-2 control-label">账号</label>
	<div class="col-sm-2">
		<input type="text" class="form-control" placeholder="请输入账号" name="uid" value=${sessionScope.user}>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label">密码</label>
	<div class="col-sm-2">
		<input type="password" class="form-control" placeholder="请输入密码" name="passwd" value=${sessionScope.passwd}>
	</div>
</div>

<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<button type="submit" name="l" class="btn btn-default glyphicon glyphicon-log-in">Login</button>	
	</div>
</div>

</form>

<p class="foot label label-default">@CopyRight johe软件有限公司</p>
</body>
</html>