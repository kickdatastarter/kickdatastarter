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

<!-- 	<div class="panel-group" id="accordion" role="tablist" -->
<!-- 		aria-multiselectable="true"> -->
		
<!-- 		<div class="panel panel-default"> -->
<!-- 			<div class="panel-heading" role="tab" id="headingOne"> -->
<!-- 				<h4 class="panel-title"> -->
<!-- 					<a role="button" data-toggle="collapse" data-parent="#accordion" -->
<!-- 						href="#collapseOne" aria-expanded="true" -->
<!-- 						aria-controls="collapseOne"> Collapsible Group Item #1 </a> -->
<!-- 				</h4> -->
<!-- 			</div> -->
<!-- 			<div id="collapseOne" class="panel-collapse collapse in" -->
<!-- 				role="tabpanel" aria-labelledby="headingOne"> -->
<!-- 				<div class="panel-body">Anim 1</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="panel panel-default"> -->
<!-- 			<div class="panel-heading" role="tab" id="headingTwo"> -->
<!-- 				<h4 class="panel-title"> -->
<!-- 					<a class="collapsed" role="button" data-toggle="collapse" -->
<!-- 						data-parent="#accordion" href="#collapseTwo" aria-expanded="false" -->
<!-- 						aria-controls="collapseTwo"> Collapsible Group Item #2 </a> -->
<!-- 				</h4> -->
<!-- 			</div> -->
<!-- 			<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" -->
<!-- 				aria-labelledby="headingTwo"> -->
<!-- 				<div class="panel-body">Anim 2.</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

		<div class="container-fluid">
		<div class="row">

			<div class="col-sm-9 col-md-10 main">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Group Name</th>
							<th>Members</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="allStudygroup">
							<tr>
								<td><s:property value="key.name" /></td>
								<td>
									<s:iterator value="value">
										<s:property value="loginid" /> <br>
									</s:iterator>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>	
	

	<h3>Add a group</h3>
	<form action="${ctx}/accounts/addStudygroup" method="post">
		<label>Group name</label> 
		<input name="newGroupName">
		<button type="submit" value="login" name="action">Add</button>
	</form>
	
	<br>
	
	<h3>Add a person into a group</h3>
 	<form action="${ctx}/accounts/addpersoninStudygroup" 
 		method="post"> 
 		<label> groupid</label> <input name="newGroupid"> <label> 
 			userid</label> <input name="user1"> 
 			
		<button type="submit" value="login" name="action">Add</button>	
	</form> 
	
	<br>
	
	<h3>Delete a group</h3>
	<form action="${ctx}/accounts/deleteStudygroup" method="post">
		<label>Group name</label> 
		<input name="Groupname">
		<button type="submit" value="login" name="action">Delete</button>
	</form>
	
	<br>
	
	<h3>Delete a person from a group</h3>
	<form action="${ctx}/accounts/deletepeoplefromStudygroup" 
 		method="post"> 
 		<label> groupid</label> <input name="Groupid"> <label> 
 			userid</label> <input name="userid"> 
 			
		<button type="submit" value="login" name="action">delete</button>
		
	</form> 
</body>
</html>