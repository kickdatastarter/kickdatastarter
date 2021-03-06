<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="edu.neu.cs5200.finalproj.model.KUser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%@include file="include.jsp"%>

<!-- Custom styles for this template -->
<link href="${ctx}/bootstrap/docs/examples/theme/theme.css" rel="stylesheet">

</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">LibReservation</a>
		</div>

		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${ctx}/index">Home</a></li>
			</ul>

			<%
				KUser curUser = (KUser)session.getAttribute("user");
				if (curUser == null) {
			%>
			<div class="navbar-right navbar-form">
				<a class="btn btn-primary" href="${ctx}/accounts/signin.jsp">Sign in</a> 
			</div>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${sessionScope.user["loginid"]}
					 <span class="caret"></span></a> 
					
					<ul class="dropdown-menu">
					<%
						if (curUser.getRole().getid() == 7) {   // User Manager
					%>
						<li><a href="${ctx}/admin/insertUser.jsp">insert user</a></li>
						<li><a href="${ctx}/admin/deleteUser.jsp">delete user</a></li>
						<li><a href="${ctx}/admin/updateUser.jsp">update user</a></li>
						
					<%
						} else if (curUser.getRole().getid() == 6) {   // Facility Manager
					%>
						<li><a href="${ctx}/admin/insertComputer.jsp">insert computer</a></li>
						<li><a href="${ctx}/admin/deleteComputer.jsp">delete computer</a></li>
						<li><a href="${ctx}/admin/insertGroupRoom.jsp">insert group room</a></li>
						<li><a href="${ctx}/admin/deleteGroupRoom.jsp">delete group room</a></li>
						<li><a href="${ctx}/admin/insertIndividualRoom.jsp">insert individual room</a></li>
						<li><a href="${ctx}/admin/deleteIndividualRoom.jsp">delete individual room</a></li>
						<li><a href="${ctx}/admin/setfacilitystate.jsp">set facility state</a></li>
					
					<%
						} else {
					%>
						<li><a href="${ctx}/accounts/getMyResv">My Reservations</a></li>
						<li><a href="${ctx}/accounts/getMyStudygroup">My StudyGroup</a></li>
						
					<%
						}
					%>
						<li role="separator" class="divider"></li>
						<li><a href="${ctx}/accounts/signout">Sign out</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>

	</div>
	</nav>

</body>
</html>
