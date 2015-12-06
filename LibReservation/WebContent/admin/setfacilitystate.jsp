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
	
	<form class="form-signin" action="${ctx}/accounts/setfacilitystate" method="post">
		
			
		<label for="inputFacilityid" class="sr-only">Facilityid</label> <input
			type="Facilityid" id="inputFacilityid" class="form-control"
			placeholder="Facilityid" required name="Facilityid">
			
<!-- 		<label for="inputMaintainstatus" class="sr-only">Status</label> <input -->
<!-- 			type="Maintainstatus" id="inputMaintainstatus" class="form-control" -->
<!-- 			placeholder="Maintainstatus" required name="Maintainstatus"> -->
			
			
	 <select id="code"  name="Maintainstatus" onclick = "choose(this)">
     <option value="Available">Available</option>
     <option value="Maintaining">Maintaining</option>
     </select> 
	

		
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Submit</button>
	</form>
</body>
</html>