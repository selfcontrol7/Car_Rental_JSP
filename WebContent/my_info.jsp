<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
    	<jsp:include page="WEB-INF/menu.css"></jsp:include>
    </style>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div>
<h2>Member Information</h2>
	<%
	//String message = request.getParameter("message");
	String resident_id = request.getParameter("resident_id");
	session.setAttribute("edit_resident_id",resident_id);
	
	String first_name = request.getParameter("first_name");
	session.setAttribute("edit_first_name",first_name);
	
	String last_name = request.getParameter("last_name");
	session.setAttribute("edit_last_name",last_name);
	
	String address = request.getParameter("address");
	session.setAttribute("edit_address",address);
	
	String phoneNumber = request.getParameter("phoneNumber");
	session.setAttribute("edit_phoneNumber",phoneNumber);
	
	String affiliation = request.getParameter("affiliation");
	session.setAttribute("edit_affiliation",affiliation);
	
	String member_type = request.getParameter("member_type");
	session.setAttribute("edit_member_type",member_type);
	
	String driving_license = request.getParameter("driving_license");
	session.setAttribute("edit_driving_license",driving_license);
	
	String email = request.getParameter("email");
	session.setAttribute("edit_email",email);
	
	String password = request.getParameter("password");
	
	Integer credit_rate = Integer.parseInt(request.getParameter("credit_rate"));
	session.setAttribute("edit_credit_rate",credit_rate);
	
	String credit_card = request.getParameter("credit_card");
	session.setAttribute("edit_credit_card",credit_card);
	
	String joiningDate = (String) request.getAttribute("joiningDate");

	String expir_date = (String) request.getAttribute("expir_date");
	session.setAttribute("edit_expir_date",expir_date);
	%>
	
	<p> <b>Resident Id :</b> <% out.println(resident_id); %><br>
	<b>First name :</b> <% out.println(first_name); %><br>
	<b>Last name :</b> <% out.println(last_name); %><br>
	<b>Address :</b> <% out.println(address); %><br>
	<b>Phone Number :</b> <% out.println(phoneNumber); %><br>
	<b>Affiliation :</b> <% out.println(affiliation); %><br>
	<b>Member Type :</b> <% out.println(member_type); %><br>
	<b>Driving License :</b> <% out.println(driving_license); %><br>
	<b>Email : </b> <% out.println(email); %><br>
	<b>Credit Rate :</b> <% out.println(credit_rate); %> %<br>
	<b>Credit Card Number :</b> <% out.println(credit_card); %><br>
	<b>Membership Subscription :</b> <% out.println(joiningDate); %><br>
	<b>Subscription Expiration :</b> <% out.println(expir_date); %></p>
	<h4><a href="edit_info.jsp">Edit Information</a></h4>
	<h4><a href="#">Deactivate Account</a></h4>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>