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
<form role="form" action="/os04/doAddDepartment" method="post">
	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">
			部门编号
		</label>
		<div class="col-sm-10 ">
			<input type="text" class="form-control" name="did" placeholder="请输入部门编号">
		</div>
	</div>
	
	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">部门名称</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="dname" placeholder="请输入部门名称">
		</div>
	</div>
	
	<div>
		<label class="col-sm-2">
		部门功能
		</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="dfunc" placeholder="请输入部门功能">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2  col-sm-10">
			<button type="submit" class="btn btn-primary" >添加部门</button>
		</div>
	</div>
	
</form>
</body>
</html>