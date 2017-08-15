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
	<%--
		Employee em = (Employee) request.getAttribute("em");
	--%>
	<div>
		<table class="table">
			<thead>
				<tr class="default"><td>工号</td>		<td>部门编号</td>	<td>职位</td>		<td>姓名</td>		<td>年龄</td>		<td>性别</td></tr>
			</thead>
			<tbody>
				<tr><td>${requestScope.em.eid}</td>	<td>${requestScope.em.did}</td>	<td>${requestScope.em.epos}</td>	<td>${em.ename }</td>
				<td><c:if test="${em.eage==2 }">"男"	</c:if>	</td>	<td>${em.esex}</td></tr>
			</tbody>
		</table>
		
		<div>
			<button class="btn btn-info">修改资料</button>
		</div>
		<div>
			<button class="btn btn-info" onclick="location.href='toRetrievePasswd'">修改密码</button>
		</div>
		<%
		
		%>
	</div>	
</body>
</html>