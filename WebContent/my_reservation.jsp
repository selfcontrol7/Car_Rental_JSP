<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Reservation.html</title>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #dddddd;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            padding: 8px;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 60%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        table#t01 {
            width: 60%;
            background-color: #f1f1c1;
        }
    </style>
</head>
<body>
<% String username = request.getParameter("email"); %>

<h1>CAR RENTAL SYSTEM</h1>
<ul>
    <li><a href="index.jsp">Home</a></li>
    <li><a href="reservation.jsp">Reserve a Car</a></li>
    <li><a href="my_reservation.jsp">My Reservation</a></li>
    <li> | </li>
    <li><a>Welcome <b><% out.println(username); %></b></a></li>
    <li><a href="my_info.jsp">My Account</a></li>
</ul>

<h2>Recent Reservations</h2>
<table>
    <caption> Car Reservation Information</caption>
    <tr>
        <th>Reservation number</th>
        <th>Booked on</th>
        <th>Car model</th>
        <th>Car pick-up date</th>
        <th>Expected return date</th>
        <th>Pick-up place</th>
        <th>Expected return place</th>
        <th>Rental number</th>
        <th>Action</th>
    </tr>
    <tr>
        <td>RS002357</td>
        <td>20/12/2018</td>
        <td>Hyundai Palisade</td>
        <td>28/12/2018</td>
        <td>05/01/2019</td>
        <td>Soongsil University Car agency</td>
        <td>Soongsil University Car agency</td>
        <td>RE002356</td>
        <td>
            <a href="edit_reservation?code=${reservation.number}">Edit</a>
            <a href="cancel_reservation?code=${reservation.number}">Cancel</a>
        </td>
    </tr>
    <tr>
        <td>RS002356</td>
        <td>01/02/2018</td>
        <td>Mercedes-Benz E-Class</td>
        <td>24/05/2019</td>
        <td>01/06/2019</td>
        <td>Soongsil University Car agency</td>
        <td>Soongsil University Car agency</td>
        <td>RE002356</td>
        <td>
            <a href="edit_reservation?code=${reservation.number}">Edit</a>
            <a href="cancel_reservation?code=${reservation.number}">Cancel</a>
        </td>
    </tr>
</table>
<br>

<table id="t01">
    <caption> Car Rental Information</caption>
    <tr>
        <th>Rental number</th>
        <th>Return Due on</th>
        <th>Returned on</th>
        <th>Insurance Type</th>
        <th>Penalty Fee</th>
        <th>Total Rental Fee</th>
        <th>Payment Status</th>
        <th>Car Item</th>
        <th>Reservation number</th>
    </tr>
    <tr>
        <td>RS002356</td>
        <td>28/12/2018</td>
        <td>29/12/2018</td>
        <td>Full covered</td>
        <td>50,000 ₩</td>
        <td>200,000 ₩</td>
        <td>Paid</td>
        <td>HP-251489</td>
        <td>RS002357</td>
    </tr>
</table>

</body>
</html>