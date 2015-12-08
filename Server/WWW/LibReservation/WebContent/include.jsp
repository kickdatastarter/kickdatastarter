<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if(pageContext.getAttribute("ctx") == null) {	
		pageContext.setAttribute("ctx", pageContext.getServletContext().getContextPath());
	}
%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
	
	
<script src="${ctx}/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/bootstrap/docs/assets/js/docs.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="${ctx}/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${ctx}/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">