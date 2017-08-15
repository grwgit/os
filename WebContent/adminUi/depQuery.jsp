<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="/os04/doMutiQueryDep" method="post">
<label >did</label>
<input type="text" name="did">
<label>dname</label>
<input type="text" name="dname">
<button type="submit">查询</button>
<table class="table table-hover">
	<thead class="success">
	<tr><th>d_id</th>	<th>d_name</th>	<th>d_func</th></tr>
	</thead>
	
	<c:forEach items="${requestScope.depBeans }" var="dep">
		<tr><td>${dep.did }</td>	<td>${dep.dname }</td>	<td>${dep.dfunc }</td>	<td><a href="doDelDep?did=${dep.did }" class="btn btn-primary">删除</a></td></tr>
	</c:forEach>
</table>
</form>
</body>
</html>