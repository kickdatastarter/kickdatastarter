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
					<li class="active"><a href="#">My Reservations <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">My Study Groups</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Facility</th>
							<th>Group Name</th>
							<th>Start Time</th>
							<th>End Time</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="allResv">
							<tr>
								<td><s:property value="facility.name" /></td>
								<td><s:property value="group.name" /></td>
								<td><s:date name="starttime" format="MM/dd/yyyy HH:mm:ss" /></td>
								<td><s:date name="endtime" format="MM/dd/yyyy HH:mm:ss" /></td>
								<td><s:a class="btn btn-primary" href="/LibReservation/accounts/deleteResv?resvID_delResv=%{id}">Delete</s:a>
									<s:a class="btn btn-primary" href="" data-toggle="modal" data-target="#myModal" data-resvid="%{id}">Update</s:a>
									<%-- <s:a> tag automatically adds href, while i cannot have href here. --%>
								</td> 
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>	
			
	<!-- modal for updating a reservation -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">

					<form class="form-signin" action="${ctx}/accounts/updateResv" method="post">
						<!-- Date and time -->
						<label>Date: </label> <input id="datePicker_updateResv" name="datePicker_updateResv"> 
						<br>
						<label>Start Hour: </label> <select name="startHour_updateResv" id="startHour_updateResv"></select> 
						<label>End Hour: </label> <select name="endHour_updateResv" id="endHour_updateResv"></select>

						<input type="hidden" name="resvID_updateResv" id="resvID_updateResv" value=""/>
						<br>
						<button class="btn btn-lg btn-primary btn-block" type="submit" value="login" name="action">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
		var now = new Date();
	
		$(function() {			
			
			// Alert TransactionFailMsg
			var TransactionFailMsg = '<%=request.getAttribute("TransactionFailMsg")%>';
			if(TransactionFailMsg !== "null") {
				alert(TransactionFailMsg);
			}
			
			$('#myModal').on('show.bs.modal', function (event) {
				var button = $(event.relatedTarget);
  				var resvID = button.data('resvid');
  				$("#resvID_updateResv").val(resvID);
			});
			
			$("#datePicker_updateResv").datepicker({
				minDate: now,
				maxDate: new Date(now.getFullYear() + 1, 11, 31),
				onSelect: datePickerOnSelected
			});
		});
		
		var datePickerOnSelected = function(dateText, inst){
			selectedDate = new Date(dateText);
			$("#startHour_updateResv").empty();
			
			if (now.getFullYear() === selectedDate.getFullYear() &&
				now.getMonth() === selectedDate.getMonth() &&
				now.getDate() === selectedDate.getDate()) {
				for(var i=now.getHours()+1; i<25; i++) {
					$("#startHour_updateResv").append("<option value=" + i + ">" + i + "</option>");
				}
			} else {
				for(var i=0; i<25; i++) {
					$("#startHour_updateResv").append("<option value=" + i + ">" + i + "</option>");
				}
			}
			
			$("#startHour_updateResv").on("change", function() {
				$("#endHour_updateResv").empty();
				for(var i=Number($(this).val())+1; i<25; i++) {
					$("#endHour_updateResv").append("<option value=" + i + ">" + i + "</option>");
				}
			});
		};
		
	</script>
	

</body>
</html>