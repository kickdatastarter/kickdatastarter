<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<%@include file="../top.jsp"%>

<!-- Custom styles for this template -->
<link href="${ctx}/bootstrap/docs/examples/signin/signin.css"
	rel="stylesheet">

</head>
<body>

	<form class="form-signin" action="${ctx}/accounts/signin" method="post">
		<h2 class="form-signin-heading">Please sign in</h2>
		<label for="inputEmail" class="sr-only">Email address</label> <input
			 id="inputEmail" class="form-control"
			placeholder="Email address" required autofocus name="loginName">

		<label for="inputPassword" class="sr-only">Password</label> <input
			type="password" id="inputPassword" class="form-control"
			placeholder="Password" required name="loginPassword">

		<div class="checkbox">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			value="login" name="action">Sign in</button>
	</form>

</body>
</html>