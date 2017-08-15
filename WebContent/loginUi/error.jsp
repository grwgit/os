<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		${sessionScope.forbidden }
		${sessionScope.passwdError }
		${sessionScope.userNone }
		${sessionScope.userNull }
	</div>
	<%
		out.println("2s后跳转到登陆界面");
		response.setHeader("refresh", "2;URL=loginUi/login.jsp");
	%>
</body>
</html>