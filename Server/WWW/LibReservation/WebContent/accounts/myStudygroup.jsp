<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

    <%@include file="../top.jsp"%>
    <!-- Custom styles for this template -->
<link href="${ctx}/bootstrap/docs/examples/dashboard/dashboard.css"
    rel="stylesheet">
    
</head>
<body>



		<div class="container-fluid">
		<div class="row">

            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="${ctx}/accounts/getMyResv">My Reservations <span
                            class="sr-only">(current)</span></a></li>
                    <li class="active"><a href="${ctx}/accounts/getMyStudygroup">My Study Groups</a></li>
                </ul>
            </div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
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
				
				<br><br>
				
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
			</div>
		</div>
	</div>	
	

	
</body>
</html>