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
	<h2> Member Registration</h2>
	<%-- Displaying message --%>
	<p class="info">${ message }</p>
	
	<form action="memberRegistration" method="post">
	    <table style="with: 50%">
	        <tr>
	            <td>Resident ID</td>
	            <td><input type="text" name="resident_id" /></td>
	        </tr>
	        <tr>
	            <td>First Name</td>
	            <td><input type="text" name="first_name" /></td>
	        </tr>
	        <tr>
	            <td>Last Name</td>
	            <td><input type="text" name="last_name" /></td>
	        </tr>
	        <tr>
	            <td>Address</td>
	            <td><input type="text" name="address" /></td>
	        </tr>
	        <tr>
	            <td>Phone Number</td>
	            <td><input type="text" name="phoneNumber" /></td>
	        </tr>
	        <tr>
	            <td>Affiliation</td>
	            <td>
	                <select name="affiliation" size=auto>
	                    <option value="">---</option>
	                    <option value="Company">Company</option>
	                    <option value="School">School</option>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <td>Member Type</td>
	            <td>
	                <select name="member_type" size=auto>
	                    <option value="">---</option>
	                    <option value="PLATINUM">PLATINUM</option>
	                    <option value="GOLD">GOLD</option>
	                    <option value="SILVER">SILVER</option>
	                    <option value="BRONZE">BRONZE</option>
	                </select>
	            </td>
	        </tr>	        
	        <tr>
	            <td>Driving License Number</td>
	            <td><input type="text" name="driving_license" /></td>
	        </tr>
	        <tr>
	            <td>Email address</td>
	            <td><input type="text" name="email" /></td>
	        </tr>
	        <tr>
	            <td>Password</td>
	            <td><input type="password" name="password" /></td>
	        </tr>
	        <tr>
	            <td>Credit rate</td>
	            <td><input type="number" min="0" max="100" step="5" name="credit_rate" /></td>
	        </tr>
	        <tr>
	            <td>Credit Card Type</td>
	            <td>
	                <select name="credit_card" size=auto>
	                    <option value="">---</option>
	                    <option value="VISA">VISA</option>
	                    <option value="MASTER">MASTER</option>
	                    <option value="AMEX">AMEX</option>
	                </select>
	            </td>
	        </tr>	        	        
	    </table>
	    </br>
	    <input type="submit" value="Submit" />
	    <input type="reset" value="Reset" />
	</form>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>