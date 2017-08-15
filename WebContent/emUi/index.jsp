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
<title>change passwd</title>
</head>
<body>
	<div>
	<%
		response.setContentType("text/html;charset=utf-8");
	%>
	${sessionScope.changePasswdSucc}
	${sessionScope.oldPasswd }
	${sessionScope.twoPasswdAgree}
	${sessionScope.twoPasswdDisagree}
	<%
		out.println("3s后跳转到登录界面");
		response.setHeader("refresh", "3;URL=emUi/personData.jsp");
	%>
	</div>
</body>
</html>