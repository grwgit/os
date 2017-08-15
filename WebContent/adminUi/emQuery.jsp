<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<style type="text/css">
	.paging{
		position:absolute;
		right:0px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<form action="/os04/doMutiQueryEm" method="post">
<input type="text" name="eid" value=${emDto.eid }>
<input type="text" name="did" value=${emDto.did }>
<input type=>
<input type="text" name="epos" value=${emDto.epos }>
<input type="text" name="ename" value=${emDto.ename }>
<input type="text" name="status" value=${emDto.status }>
<button type="submit">查询</button>
<table class="table table-hover">
<tr class="success">
<th>e_id</th><th>d_id</th><th>e_pos</th><th>e_name</th><th>status</th>
</tr>
<c:forEach items="${emBeans }" var="em">
	<tr><td>${em.eid }</td>	<td>${em.did }</td>	<td>${em.epos }</td>	<td>${em.ename }</td>	<td>${em.status }</td>
	<td><a href="doDelEm?eid=${em.eid }" class="btn btn-primary">删除</a></td>
	<td><a href="toUpdEm?eid=${em.eid }" class="btn btn-primary">修改</a></td>
</c:forEach>
</table>
</form>
<div class="paging">
	<form action="/os04/doPaging" method="post">
		<p>${(currentPage)+1 }</p>
		<a href="/os04/doPaging?pageNum=${(currentPage)-1 }">上一页</a>
		<input type="text" col-sm-2 name="pageNum" value=${currentPage }>
		<button type="submit">go</button>
		<a href="/os04/doPaging?pageNum=${(currentPage)+1 }">下一页</a>
	</form>
</div>

</body>
</html>