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
<form role="form" action="/os04/doAddEmployee" method="post">

	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">工号</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="eid" placeholder="请输入id">
		</div>
		
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">部门</label>
		<div class="col-sm-10">
			<input type="text" class="form-control " name="did" placeholder="请输入部门编号">
		</div>
		
	</div>

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">职位</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="epos" placeholder="请输入员工职位">
		</div>
		
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">添加员工</button>
		</div>
	</div>
</form>
</body>
</html>