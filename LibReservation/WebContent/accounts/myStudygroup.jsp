<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@include file="../top.jsp"%>

	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne"> Collapsible Group Item #1 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body">Anim 1</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingTwo">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
						aria-controls="collapseTwo"> Collapsible Group Item #2 </a>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="headingTwo">
				<div class="panel-body">Anim 2.</div>
			</div>
		</div>
	</div>

	<form action="${ctx}/accounts/addStudygroup" method="post">
		<label>Group name</label> 
		<input type="name" id="inputname" class="form-control"
		 required autofocus name="newGroupName">
		<button type="submit" value="login" name="action">Submit</button>
	</form>
	
 	<form action="/LibReservation/accounts/addpersoninStudygroup" 
 		method="post"> 
 		<label> groupid</label> <input name="newGroupid"> <label> 
 			userid</label> <input name="user1"> 
 			
		<button type="submit" value="login" name="action">Add</button>
		
	</form> 
		<form action="${ctx}/accounts/deleteStudygroup" method="post">
		<label>Group name</label> 
		<input type="name" id="inputname" class="form-control"
		 required autofocus name="Groupname">
		<button type="submit" value="login" name="action">Delete</button>
	</form>
	<form action="/LibReservation/accounts/deletepeoplefromStudygroup" 
 		method="post"> 
 		<label> groupid</label> <input name="Groupid"> <label> 
 			userid</label> <input name="userid"> 
 			
		<button type="submit" value="login" name="action">delete</button>
		
	</form> 
</body>
</html>