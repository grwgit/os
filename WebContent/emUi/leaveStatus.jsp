<%@page import="java.util.ArrayList,com.johe.bean.Leave"%>
<%@page import="java.util.List"%>
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
		<table>
			<thead><tr><th>申请时间</th>	<th>状态</th>		<th>详细信息</th></tr></thead>
			<tbody>
				<%
					List<Leave> leaves = (ArrayList)request.getAttribute("leaves");
					for(Leave leave:leaves){
				%>
					<tr><td><%=leave.getLsubmit() %></td>	<td><%=leave.getStatus()==1?"未审批":(leave.getStatus()==2?"已审批未备案":"已审批已备案") %></td>	
					<td><a href="leaveStatusMore?lid=<%=leave.getLid()%>">展开详细信息</a></td></tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>