<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="/os04/doMutiQuery" method="post">
<input type="text"  name="uid"/>
<input type="text"  name="eid"/>
<input type="text"  name="status"/>
<button type="submit">查询</button>
<table class="table table-hover">
<tr class="success">
<th>u_id</th><th>e_id</th><th>passwd</th><th>status</th>
</tr>
<c:forEach items="${userBeans }" var="user">
	<tr><td>${user.uid }</td>	<td>${user.eid }</td>	<td>********</td>	<td>${user.status }</td>
	<td><a href="doDelUser?uid=${user.uid }" class="btn btn-primary">删除</a></td>
</c:forEach>
<%--=request.getAttribute("userBeans") --%>
</table>
</form>
</body>
</html>