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
	<h2>Edit Member Information</h2>
	
	<form action="Edit_infoServlet" method="post">
	    <table style="with: 50%">
	        <tr>
	            <td>Resident ID</td>
	            <td><input type="text" name="resident_id" value="<% out.println(session.getAttribute("edit_resident_id")); %>"/></td>
	        </tr>
	        <tr>
	            <td>First Name</td>
	            <td><input type="text" name="first_name" value="<% out.println(session.getAttribute("edit_first_name")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Last Name</td>
	            <td><input type="text" name="last_name" value="<% out.println(session.getAttribute("edit_last_name")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Address</td>
	            <td><input type="text" name="address" value="<% out.println(session.getAttribute("edit_address")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Phone Number</td>
	            <td><input type="text" name="phoneNumber" value="<% out.println(session.getAttribute("edit_phoneNumber")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Affiliation</td>
	            <td>	            	
	            	<%String edit_affiliation = (String)session.getAttribute("edit_affiliation");%>
	                <select name="affiliation" size=auto>
	                    <option value="">---</option>
	                    <option value="Company" <%if(edit_affiliation.equals("Company")){out.println(" selected");} %>>Company</option>
	                    <option value="School" <%if(edit_affiliation.equals("School")){out.println(" selected");} %>>School</option>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <td>Member Type</td>
	            <td>
	            	<%String edit_member_type = (String)session.getAttribute("edit_member_type");%>
	                <select name="member_type" size=auto>
	                    <option value="">---</option>
	                    <option value="PLATINUM" <%if(edit_member_type.equals("PLATINUM")){out.println(" selected");} %>>PLATINUM</option>
	                    <option value="GOLD" <%if(edit_member_type.equals("GOLD")){out.println(" selected");} %>>GOLD</option>
	                    <option value="SILVER" <%if(edit_member_type.equals("SILVER")){out.println(" selected");} %>>SILVER</option>
	                    <option value="BRONZE" <%if(edit_member_type.equals("BRONZE")){out.println(" selected");} %>>BRONZE</option>
	                </select>
	            </td>
	        </tr>	        
	        <tr>
	            <td>Driving License Number</td>
	            <td><input type="text" name="driving_license" value="<% out.println(session.getAttribute("edit_driving_license")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Email address</td>
	            <td><input type="text" name="email" value="<% out.println(session.getAttribute("edit_email")); %>"/></td>
	        </tr>
	        <tr>
	            <td>Password</td>
	            <td><input type="password" name="password"  placeholder="Current or New password"/></td>
	        </tr>
	        <tr>
	        	<%Integer edit_credit_rate = (Integer) session.getAttribute("edit_credit_rate");%>
	            <td>Credit rate</td>
	            <td><input type="text" name="credit_rate" value="<% out.println(edit_credit_rate); %>"/></td>
	        </tr>
	        <tr>
	            <td>Credit Card Type</td>
	            <td>
	            	<%String edit_credit_card = (String)session.getAttribute("edit_credit_card");%>
	                <select name="credit_card" size=auto>
	                    <option value="">---</option>
	                    <option value="VISA" <%if(edit_credit_card.equals("VISA")){out.println(" selected");} %>>VISA</option>
	                    <option value="MASTER" <%if(edit_credit_card.equals("MASTER")){out.println(" selected");} %>>MASTER</option>
	                    <option value="AMEX" <%if(edit_credit_card.equals("AMEX")){out.println(" selected");} %>>AMEX</option>
	                </select>
	            </td>
	        </tr>	        	        
	    </table>
	    </br>
	    <input type="submit" value="Save Modifications" />
    	<a href="index.jsp">
   			<input type="button" value="Cancel" />
		</a> 
	</form>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>