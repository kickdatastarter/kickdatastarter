<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="../top.jsp"%>
	
	<form class="form-signin" action="${ctx}/accounts/insertGroupRoom" method="post">
		

		<label for="inputinsertGroupRoomCapacity" class="sr-only">Capacity</label> <input
			type="insertGroupRoomCapacity" id="inputinsertGroupRoomCapacity" class="form-control"
			placeholder="insertGroupRoomCapacity" required name="insertGroupRoomCapacity">
			
			
		<label for="inputinsertGroupRoomName" class="sr-only">Name</label> <input
			type="insertGroupRoomName" id="inputinsertGroupRoomName" class="form-control"
			placeholder="insertGroupRoomName" required name="insertGroupRoomName">
			

		
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Submit</button>
	</form>

</body>
</html>