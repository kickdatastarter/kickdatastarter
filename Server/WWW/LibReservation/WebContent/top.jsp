<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				if (session.getAttribute("user") == null) {
			%>
			<div class="navbar-right navbar-form">
				<a class="btn btn-primary" href="${ctx}/accounts/signin.jsp">Sign in</a> 
				<a class="btn btn-default" href="${ctx}/accounts/signup.jsp">Sign up</a>
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
						<li><a href="${ctx}/accounts/getMyResv">My Reservations</a></li>
						<li><a href="${ctx}/accounts/getMyStudygroup">My StudyGroup</a></li>
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