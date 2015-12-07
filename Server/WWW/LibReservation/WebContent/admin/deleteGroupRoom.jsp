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
	
	<form class="form-signin" action="${ctx}/accounts/deleteGroupRoom" method="post">
		
			
		<label for="inputdeleteGroupRoomName" class="sr-only">Name</label> <input
			type="deleteGroupRoomName" id="inputdeleteGroupRoomName" class="form-control"
			placeholder="deleteGroupRoomName" required name="deleteGroupRoomName">
			

		
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Submit</button>
	</form>

</body>
</html>