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
<form role="form" action="askLeave" method="post">

	<%
		request.setAttribute("uid", request.getParameter("uid"));
		//out.print(request.getParameter("uid"));
	%>
	<div class="form-group">
		<label class="col-sm-2">账号</label>
		<div class="col-sm-10">
			<input class="form-control" readonly="readonly" type="text" name="uid"  value=<%=request.getParameter("uid") %>>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2">编号</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" name="lid"  placeholder="编号">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2">原因</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" name="reason"  placeholder="请假原因">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2">开始时间</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" name="btime"  placeholder="格式：yy-MM-dd:HH">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2">截止时间</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" name="etime" placeholder="格式：yy-MM-dd:HH">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">申请</button>
		</div>
	</div>
</form>
</body>
</html>