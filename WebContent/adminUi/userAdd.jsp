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
<title>Insert title here</title>
</head>
<body>
<form role="form" action="/os04/doAddUser" method="post">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">用户名</label>
		<div class="col-sm-10">
			<input type="text" class="form-control " name="uid" placeholder="请输入用户名">
		</div>
		
	</div>

	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">用户工号</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="eid" placeholder="请输入用户id">
		</div>
		
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">用户密码</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" name="passwd" placeholder="请输入用户密码">
		</div>
		
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label>
					<input type="checkbox">记住账号
				</label>
			</div>
		</div>		
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">添加用户</button>
		</div>
	</div>
</form>
</body>
</html>