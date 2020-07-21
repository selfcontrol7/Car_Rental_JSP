<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CAR RENTAL SYSTEM</title>
    <style>
    	<jsp:include page="WEB-INF/menu.css"></jsp:include>
    </style>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div>
	<h2>Reserve a Car</h2>
	<form action="carReservation" method="post">
	    <table style="with: 50%">
	        <tr>
	            <td>Car model</td>
	            <td>
	                <select name="car_model" size=auto>
	                    <option value="">Select the model of car</option>
	                    <option value="Toyota Corolla">Toyota Corolla</option>
	                    <option value="Toyota Camry">Toyota Camry</option>
	                    <option value="Toyota HiLux">Toyota HiLux</option>
	                    <option value=""></option>
	                    <option value="Mercedes-Benz CLS-Class">Mercedes-Benz CLS-Class</option>
	                    <option value="Mercedes-Benz A-Class">Mercedes-Benz A-Class</option>
	                    <option value="Mercedes-Benz E-Class">Mercedes-Benz E-Class</option>
	                    <option value="BMW X7">BMW X7</option>
	                    <option value="BMW 8 SERIES">BMW 8 SERIES</option>
	                    <option value="BMW M6">BMW M6</option>
	                    <option value="Hyundai Ioniq">Hyundai Ioniq</option>
	                    <option value="Hyundai Palisade">Hyundai Palisade</option>
	                    <option value="Hyundai Grandeur">Hyundai Grandeur</option>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <td>Car pick-up date</td>
	            <td><input type="date" name="pickup_date"></td>
	        </tr>
	        <tr>
	            <td>Expected return date</td>
	            <td><input type="date" name="expect_return_date"></td>
	        </tr>
	        <tr>
	            <td>Pick-up place</td>
	            <td><input type="text" name="expect_return_place" /></td>
	        </tr>
	        <tr>
	            <td>Expected return place</td>
	            <td><input type="text" name="last_name" /></td>
	        </tr>
	    </table>
	    </br>
	    <input type="Submit" value="Make Reservation" />
	    <input type="reset" value="Reset form" />
	</form>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>