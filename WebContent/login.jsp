<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
    	<jsp:include page="WEB-INF/menu.css"></jsp:include>
    </style>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div>
	<h2>Login</h2>
	<form action="<%=request.getContextPath()%>/login" method="post">
	<br>
		<table style="with: 50%">
		    <tr>
		        <td>Email address</td>
		        <td><input type="text" name="email" /></td>
		    </tr>
		    <tr>
		        <td>Password</td>
		        <td><input type="password" name="password" /></td>
		    </tr>
		</table>
	<input type="submit" value="Login" />
	</form>
	<br>
	<a href="register_member.jsp">Sign-up as a Member</a>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>