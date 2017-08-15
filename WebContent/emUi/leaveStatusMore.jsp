<%@page import="com.johe.bean.Leave"%>
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
	<div>
	<%
		Leave leave = (Leave)request.getAttribute("leave");
	%>
		<form>
			<label>编号</label>
			<input type="text" readonly="readonly" value=<%=leave.getLid()%>>
			<label>申请时间</label>
			<input type="text" readonly="readonly" value=<%=leave.getLsubmit() %>>
			<label>请假开始时间</label>
			<input type="text" readonly="readonly" value=<%=leave.getLbtime() %>>
		</form>
	</div>
</body>
</html>