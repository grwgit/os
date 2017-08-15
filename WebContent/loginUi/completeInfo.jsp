<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<style type="text/css">
	.myinfo{
		position:absolute;
		right:200px;
		left:200px;
		top:100px;
		bottom: 100px;
	}
	h3{
		text-align: center;
	}

</style>
<title>login</title>
</head>
<body>
	<h3>完善资料</h3>
	<div class="myinfo">
		<form action="/os04/doCoptInfo" method="post" class="form-horizontal myform" role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">工号</label>
				<div class="col-sm-10">
					<input class="form-control"  type="text" name="eid" placeholder="请输入您的工号">
				</div>
			</div>
		
			<div class="form-group">
				<label class="col-sm-2 control-label">新账号</label>
				<div class="col-sm-10">
					<input class="form-control"  type="text" name="uid" placeholder="请输入账号">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">新密码</label>
				<div class="col-sm-2">
					<input class="form-control"  type="password" name="passwd" placeholder="请输入密码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-2">
					<input class="form-control"  type="text" name="ename" placeholder="请输入姓名">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-2">
					<input class="form-control"  type="text" name="eage" placeholder="请输入出生日期(yyyy-mm-dd)">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">性别</label>
				<div class="col-sm-2">
					<input class="form-control"  type="text" name="esex" placeholder="请输入性别">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" name="l" class="btn btn-default">提交</button>	
			</div>
		</form>
	</div>
</body>
</html>