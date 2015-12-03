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
	<%@include file="left.jsp"%>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Header</th>
					<th>Header</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="allResv">
					<tr>
						<td><s:property value="facility" /></td>
						<td><s:property value="facility" /></td>
						<td><s:property value="facility" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>

</body>
</html>