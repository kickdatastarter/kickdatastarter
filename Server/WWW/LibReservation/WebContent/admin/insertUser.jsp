<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="../admin.jsp"%>
	
	<form class="form-signin" action="${ctx}/accounts/insertUser" method="post">

		<label for="inputinsertUserRole" class="sr-only">Role</label> <input
			type="insertUserRole" id="inputinsertUserRole" class="form-control"
			placeholder="insertUserRole" required name="insertUserRole">
			
		<label for="inputinsertUserName" class="sr-only">Name</label> <input
			type="insertUserName" id="inputinsertUserName" class="form-control"
			placeholder="insertUserName" required name="insertUserName">
			
		<label for="inputinsertUserLoginid" class="sr-only">Loginid</label> <input
			type="insertUserLoginid" id="inputinsertUserLoginid" class="form-control"
			placeholder="insertUserLoginid" required name="insertUserLoginid">
			
		<label for="inputinsertUserNuid" class="sr-only">Nuid</label> <input
			type="insertUserNuid" id="inputinsertUserNuid" class="form-control"
			placeholder="insertUserNuid" required name="insertUserNuid">

		
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Submit</button>
	</form>

</body>
</html>