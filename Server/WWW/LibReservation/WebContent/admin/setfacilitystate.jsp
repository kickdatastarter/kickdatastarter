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


	<form class="form-signin" action="${ctx}/accounts/setfacilitystate"
		method="post">


		<label for="inputFacilityid" class="sr-only">Facilityid</label> <input
			type="Facilityid" id="inputFacilityid" class="form-control"
			placeholder="Facilityid" required name="maintainfacilityID">

		<!-- 		<label for="inputMaintainstatus" class="sr-only">Status</label> <input -->
		<!-- 			type="Maintainstatus" id="inputMaintainstatus" class="form-control" -->
		<!-- 			placeholder="Maintainstatus" required name="Maintainstatus"> -->


		<select id="code" name="Maintainstatus" onclick="choose(this)">
			<option value="Available">Available</option>
			<option value="Maintaining">Maintaining</option>
		</select> <label>Date: </label> <input id="maintaindate"
			name="maintaindate"> <br> 
			
		<label>Start Hour:
		</label> <select name="maintainstartHour" id="maintainstartHour"></select>
		
		<label>End Hour: </label> <select name="maintainendHour"
			id="maintainendHour"></select> <br>

		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Submit</button>
	</form>


	<script type="text/javascript">
		var selectedDate;
		var now = new Date(); 
	
		$(function() {
			
// 			// Get all facilities
// 			$.ajax({
// 				type : "get",
// 				url : "/LibReservation/accounts/getAllFacilitiesAjax",
// 				dataType : "json",
// 			}).success(function(result) {
// 				$("#facilityDdl_makeResv").empty();
// 				$.each(result, function(key, val) {
// 					$("#facilityDdl_makeResv").append("<option value=" + key + ">" + val + "</option>");
// 				});
// 			});
			
			// Date and time
			$("#maintaindate").datepicker({
					minDate: now,
					maxDate: new Date(now.getFullYear() + 1, 11, 31),
					onSelect: datePickerOnSelected
			});
		});
		
		var datePickerOnSelected = function(dateText, inst){
			selectedDate = new Date(dateText);
			$("#maintainstartHour").empty();
			
			if (now.getFullYear() === selectedDate.getFullYear() &&
				now.getMonth() === selectedDate.getMonth() &&
				now.getDate() === selectedDate.getDate()) {
				for(var i=now.getHours()+1; i<25; i++) {
					$("#maintainstartHour").append("<option value=" + i + ">" + i + "</option>");
				}
			} else {
				for(var i=0; i<25; i++) {
					$("#maintainstartHour").append("<option value=" + i + ">" + i + "</option>");
				}
			}
			
			$("#maintainstartHour").on("change", function() {
				$("#maintainendHour").empty();
				for(var i=Number($(this).val())+1; i<25; i++) {
					$("#maintainendHour").append("<option value=" + i + ">" + i + "</option>");
				}
			});
		};
	</script>
</body>
</html>