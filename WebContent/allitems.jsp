<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>

<%@page import="com.rentalsystem.conn.ConnectorJ"%>
<%@page import="com.rentalsystem.beans.CarItem"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Items</title>
    <style>
    	<jsp:include page="WEB-INF/menu.css"></jsp:include>
    </style>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div>
	<h2>List of all the items</h2>
</div>
<%@ page ConnectorJ.loadDriver();%>
ConnectorJ.connectToServer();

        try{
            Statement stmt = ConnectorJ.con.createStatement();
            StringBuilder sb = new StringBuilder();

            String sql = sb.append("select * from car_rental_system.carItem").toString();

            System.out.println(sql);
            

            PrintWriter out = response.getWriter();
            String title = "All Car Items";
            String pageTitle = "All Car Items";
            String docType = "<!DOCTYPE html>\n";
            out.println(docType +
                    "<html lang=\"en\">\n" + "<head><meta charset=\"UTF-8\"><title>" + pageTitle + "</title>"
                    + "<style><jsp:include page=\"WEB-INF/menu.css\"></jsp:include></style></head>\n");
            out.print("<body><jsp:include page=\"_header.jsp\"></jsp:include><jsp:include page=\"_menu.jsp\"></jsp:include>");
            
            try{
                ResultSet rs = stmt.executeQuery(sql);
                /**
                 * To print Car Item Information from Car Item table.
                 */
                out.println("<table>");
                while(rs.next()){
%>                
                	<tr>"
                	        +"<th>VIN</th>"
                	        +"<th>Car Color</th>"
                	        +"<th>Car Status</th>"
                	        +"<th>Purchased Price</th>"
                	        +"<th>Purchased Date</th>"
                	        +"<th>Purchased Discarded</th>"
                	        +"<th>Fee For One Day</th>"
                	        +"<th>Car Model Code</th>"
                	        +"<th>Action</th>"
                	        +"</tr>");
                	
                	out.print("<tr>"
                			+"<th>VIN</th>"
                			+"<th>Car Color</th>"
                			+"<th>Car Status</th>"
                			+"<th>Purchased Price</th>"
                			+"<th>Purchased Date</th>"
                			+"<th>Purchased Discarded</th>"
                			+"<th>Fee For One Day</th>"
                			+"<th>Car Model Code</th>"
                			+"<th>Action</th>"
                			+"</tr>");
                	out.println("<td>" + rs.getString("VIN") + "</td>");
                	out.println("<td>" + rs.getString("color") + "</td>");
                	out.println("<td>" + rs.getString("CarItemStatus") + "</td>");
                	out.println("<td>" + rs.getString("purchasedPrice") + "</td>");
                	out.println("<td>" + rs.getDate("datePurchased") + "</td>");
                	out.println("<td>" + rs.getDate("dateDiscarded") + "</td>");
                	out.println("<td>" + rs.getString("feePerDay") + "</td>");
                	out.println("<td>" + rs.getString("modelCode") + "</td>");
                	out.println("<td>"
                				+ "<a href=\"#?code=${reservation.number}\">Check Out</a>"
                				+ "<a href=\"#?code=${reservation.number}\">Make Payment</a>"
                				+"<a href=\"#?code=${reservation.number}\">Extend Period</a>"
                				+"<a href=\"#?code=${reservation.number}\">Add a Driver</a>"
                				+"<a href=\"#?code=${reservation.number}\">Return Car</a>"
                				+"<a href=\"#?code=${reservation.number}\">Report Problem</a>"
                				+"</td>");             	
                }
                out.println("</table>");
                
                out.print("<jsp:include page=\\\"_footer.jsp\\\"></jsp:include></body></html>");                
                
                ConnectorJ.disconnectFromServer();
                return;

            }catch(SQLException e){
                e.printStackTrace();
                ConnectorJ.disconnectFromServer();
                return;
            }

        }catch(SQLException e){
            e.printStackTrace();
            ConnectorJ.disconnectFromServer();
            return;
        }
		
	}


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>