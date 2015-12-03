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

	<%@include file="top.jsp"%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Classroom</th>
				<th>1</th>
				<th>2</th>
				<th>3</th>
				<th>4</th>
				<th>5</th>
				<th>6</th>
				<th>7</th>
				<th>8</th>
				<th>9</th>
				<th>10</th>
				<th>11</th>
				<th>12</th>
				<th>13</th>
			</tr>
		</thead>

		<tbody>
			<s:iterator value="resvData">
				<tr>
					<td><s:property value="key" /></td>

					<%
						for (int i = 1; i < 14; i++) {
					%>
					<s:if test="value.contains(1)">
						<td style="background-color: blue"></td>
					</s:if>
					<s:else>
						<td></td>
					</s:else>
					<%
						}
					%>
				</tr>
			</s:iterator>
		</tbody>

	</table>

	<button type="button" class="btn btn-primary btn-lg" id="modalBtn">Make
		a reservation</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">

					<form class="form-signin" action="${ctx}/accounts/makeResv" method="post">
						<label>Facility: </label> <select name="facilityDdl_makeResv" id="facilityDdl_makeResv"></select>
						<br>
						<label>Group: </label> <select name="studygroupDdl_makeResv" id="studygroupDdl_makeResv"></select> 
						<br>
	
						<!-- Date and time -->
						<label>Date: </label> <input id="datePicker_makeResv" name="datePicker_makeResv"> 
						<br>
						<label>Start Hour: </label> <select name="startHour_makeResv" id="startHour_makeResv"></select> 
						<label>End Hour: </label> <select name="endHour_makeResv" id="endHour_makeResv"></select>

						<br>
						<button class="btn btn-lg btn-primary btn-block" type="submit" value="login" name="action">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var selectedDate;
		var now = new Date(); 
	
		$(function() {
			
			var isMakeResvSucceed = '<%=request.getAttribute("isMakeResvSucceed")%>';
			alert(isMakeResvSucceed);
			
			$("#modalBtn").on('click', function() {
				// Get my groups
				$.ajax({
					type : "get",
					url : "/LibReservation/accounts/getMyStudygroupAjax",
					dataType : "json",
				}).success(function(result) {
					$("#studygroupDdl_makeResv").empty();
					$.each(result, function(key, val) {
						$("#studygroupDdl_makeResv").append("<option value=" + key + ">" + val + "</option>");
					});
				});
				
				// Get all facilities
				$.ajax({
					type : "get",
					url : "/LibReservation/accounts/getAllFacilitiesAjax",
					dataType : "json",
				}).success(function(result) {
					$("#facilityDdl_makeResv").empty();
					$.each(result, function(key, val) {
						$("#facilityDdl_makeResv").append("<option value=" + key + ">" + val + "</option>");
					});
				});
				
				// Date and time
				$("#datePicker_makeResv").datepicker({
 					minDate: now,
 					maxDate: new Date(now.getFullYear() + 1, 11, 31),
 					onSelect: datePickerOnSelected
				});
				
				$("#myModal").modal();
			});
		});
		
		var datePickerOnSelected = function(dateText, inst){
			selectedDate = new Date(dateText);
			$("#startHour_makeResv").empty();
			
			if (now.getFullYear() === selectedDate.getFullYear() &&
				now.getMonth() === selectedDate.getMonth() &&
				now.getDate() === selectedDate.getDate()) {
				for(var i=now.getHours()+1; i<25; i++) {
					$("#startHour_makeResv").append("<option value=" + i + ">" + i + "</option>");
				}
			} else {
				for(var i=0; i<25; i++) {
					$("#startHour_makeResv").append("<option value=" + i + ">" + i + "</option>");
				}
			}
			
			$("#startHour_makeResv").on("change", function() {
				$("#endHour_makeResv").empty();
				for(var i=Number($(this).val())+1; i<25; i++) {
					$("#endHour_makeResv").append("<option value=" + i + ">" + i + "</option>");
				}
			});
		};
	</script>

</body>
</html>