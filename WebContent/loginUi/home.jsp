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
<script type="text/javascript">
$(document).ready(function(){
	$(".usercrud").hide();
	$(".emcrud").hide();
	$(".depcrud").hide();
	$("#user_btn").mouseenter(function(){
		$(".usercrud").fadeIn("fast");
	})
	$("#user_btn").mouseleave(function(){
		$(".usercrud").fadeOut("fast");
	})
	$(".usercrud").mouseenter(function(){
		$(".usercrud").fadeIn("fast");
	})
	$(".usercrud").mouseleave(function(){
		$(".usercrud").fadeOut("fast");
	})
	
	$("#em_btn").mouseenter(function(){
		$(".emcrud").fadeIn("fast");
	})
	$("#em_btn").mouseleave(function () {
		$(".emcrud").fadeOut("fast");
	})
	$(".emcrud").mouseenter(function(){
		$(".emcrud").fadeIn("fast");
	})
	$(".emcrud").mouseleave(function(){
		$(".emcrud").fadeOut("fast");
	})
	
	$("#dep_btn").mouseenter(function(){
		$(".depcrud").fadeIn("fast");
	})
	$("#dep_btn").mouseleave(function () {
		$(".depcrud").fadeOut("fast");
	})
	$(".depcrud").mouseenter(function(){
		$(".depcrud").fadeIn("fast");
	})
	$(".depcrud").mouseleave(function(){
		$(".depcrud").fadeOut("fast");
	})
	
	//$(".iframe").append("sd");
	
	$(".leavecrud").hide();
	$("#btn_leave").mouseenter(function () {
		$(".leavecrud").fadeIn("fast");
	})
	$("#btn_leave").mouseleave(function (){
		$(".leavecrud").fadeOut("fast");
	})
	$(".leavecrud").mouseenter(function (){
		$(".leavecrud").fadeIn("fast");
	})
	$(".leavecrud").mouseleave(function(){
		$(".leavecrud").fadeOut("fast");
	})
});

</script>
<script type="text/javascript">
function checkUser() {
	$(".admin_head").hide();
	$(".admin_logout").hide();
	$(".em_head").hide();
	$(".em_logout").hide();
	$(".admin_nav").hide();
	$(".em_nav").hide();
	var t = "${homeType}";
	if (t==("admin")) {
		$(".admin_head").show();
		$(".admin_logout").show();
		$(".admin_nav").show();
	}else if(t==("em")){
		$(".em_head").show();
		$(".em_logout").show();
		$(".em_nav").show();
	}else if(t==("pm")){
		
	}else if(t==("hm")){
		
	}
}
</script>
<style type="text/css">

	.html{
	
		position:absolute;
		width:100%%;
		height:100%;
		background-color: blue;
	}
	.body{
	
		position:relative;
		width:100%%;
		height:100%;
		background-color: blue;
	}
	.myhead{
	
		position:absolute;
		top:0px;
		left:0px;
		right:0px;
		height:50px;
		background-color:#ffddcc;
		margin:0x;
		padding:0px;
		
		
	}
	.mynav{
		
		position:absolute;
		width:300px;
		left:0px;
		top:50px;
		bottom: 50px;
		background-color: green;
		padding:0px;
		margin:0px;
	}
	.mycont{
	
		position:absolute;
		left:300px;
		right:0px;
		top:50px;
		bottom:50px;
		background-color: #ffcc22;
	}
	.myfooter{
	
		positon:relative;
		right:0px;
		left:0px;
		bottom:0px;
		height:50px;
		background-color: blue;
	}
	.iframe{
	
		position: relative;
		width:100%;
		height: 100%;
		background-color: blue;
	}
	.usercrud{
	
		position: absolute;
		top:0px;
		left:83px;
		
	}
	.emcrud{
	
		position: absolute;
		top:30px;
		left:83px;
	}
	.depcrud{
	
		position:absolute;
		top:60px;
		left:83px;
	}
	.title{
		position:absolute;
		left:0px;
		width:500px;
		text-align: center;
	}
	.logout{
		position:absolute;
		right:0px;
	}
	.leavecrud{
		position:absolute;
		left:100px;
	}
</style>
<title>聚合后台管理</title>
</head>
<body onload="checkUser()">


<div class="myhead">
	<!-- head title界面 -->
	<div class="title">
		<div class="admin_head">
			<h3>聚合后台管理系统</h3>
		</div>
		<div class="em_head">
			<h3>聚合办公系统</h3>
		</div>
	</div>
	<!-- head 右上角的登出等 -->
	<div class="logout">
		<div class="admin_logout">
			<button class="btn btn-primary" onclick="location.href='doLoginOut';">登出</button>
			<button class="btn btn-primary" onclick="location.href='doLogOut'">注销</button>
		</div>
		<div class="em_logout">
			<button class="btn btn-primary" onclick="location.href='doLoginOut';">登出</button>
			<button class="btn btn-primary" onclick="location.href='doLogOut'">注销</button>
			<a href="toPersonData?uid=${user }" target="myiframe" class="btn btn-primary">个人资料</a>
	 	</div>
 	</div>
</div>


<div class="mynav">
	<div class="admin_nav">
		<div class="btn-group btn-group-vertical">
			<button id="user_btn" class="btn btn-success" onclic="">账户管理</button>
			<button id="em_btn" class="btn btn-success">员工管理</button>
			<button id="dep_btn" class="btn btn-success">部门管理</button>
			<button class="btn btn-success">信息管理</button>
			<button class="btn btn-success">统计报表</button>
		</div>
		
		<div class="usercrud">
			<ul class="list-group ">
				<a href="#" class="list-group-item active">账户管理</a>
				<!--<a href="adminUi/userAdd.jsp" target="myiframe"><h5 class="list-group-item-heading">添加账户</h5></a>  -->
				<a href="doUserQuery" target="myiframe"><h5 class="list-group-item-heading">查询账户</h5></a>
				<a href="doUserQuery" target="myiframe"><h5 class="list-group-item-heading">删除账户</h5></a>
				<!-- <a href="doUserQuery" target="myiframe"><h5 class="list-group-item-heading">修改账户</h5></a> -->
			</ul>
		</div>
	
		<div class="emcrud">
			<ul class="list-group ">
				<a href="#" class="list-group-item active">员工管理</a>
				<a href="adminUi/emAdd.jsp" target="myiframe"><h5 class="list-group-item-heading">添加员工</h5></a>
				<a href="doEmQuery" target="myiframe"><h5 class="list-group-item-heading">查看员工</h5></a>
				<a href="doEmQuery" target="myiframe"><h5 class="list-group-item-heading">删除员工</h5></a>
			</ul>
		</div>
	
		<div class="depcrud">
			<ul class="list-group">
				<a href="#" class="list-group-item active">部门管理</a>
				<a href="adminUi/depAdd.jsp" target="myiframe"><h5 class="list-group-item-heading">添加部门</h5></a>
				<a href="doDepQuery" target="myiframe"><h5 class="list-group-item-heading">查看部门</h5></a>
				<a href="doDepQuery" target="myiframe"><h5 class="list-group-item-heading">删除部门</h5></a>
			</ul>
		</div>
	</div>
	<div class="em_nav">
		<div>
			<ul class="btn-group btn-group-vertical">
				<button id="btn_task" name="btn_task" class="btn btn-success">任务管理</button>
				<button id="btn_leave" name="btn_leave" class="btn btn-success">请假管理</button>
				
				
			</ul>
		</div>
	
		<div class="leavecrud ">
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<a href="emUi/leaveAdd.jsp?uid=<%=request.getAttribute("user") %> " target="myiframe" class="btn btn-primary list-group-item-heading">请假</a>
					<a href="leaveStatus?uid=<%=request.getAttribute("user") %>" target="myiframe" class="btn btn-primary list-group-item-heading">假条状态</a>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="mycont">
	<iframe class="iframe" name="myiframe" src="loginUi/index.jsp">
		
	</iframe>
</div>


<div class="myfooter">
	<div>
		@copyright com.johe.grw
	</div>
</div>
</body>
</html>