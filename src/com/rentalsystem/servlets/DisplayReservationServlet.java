package com.rentalsystem.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentalsystem.conn.ConnectorJ;
import com.rentalsystem.beans.Reservation;

/**
 * Servlet implementation class DisplayCarItemServlet
 */
@WebServlet("/DisplayReservationServlet")
public class DisplayReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Reservation DisplayReservation;

    public void init() {
    	DisplayReservation = new Reservation();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        
        /**
         * To retrieve All Car Reservation
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

		
	    String title = "Display All Car Reservation";
	    response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
		out.println("<html>");
		out.println("<head>"); 
			out.println("<title>" + title + "</title>");
		  	//String pathVar = request.getContextPath()+ "/WebContent/WEB-INF/menu.css";
		  	//out.println("<link rel='stylesheet' type='text/css' href= '" + pathVar + "' />");
		  	//out.println("<link rel='stylesheet' type='text/css' href= '../WebContent/WEB-INF/menu.css' />");
		  	//out.println(pathVar);
		out.println("</head>"); 
		out.println("<body>");
			request.getRequestDispatcher("/_header.jsp").include(request, response);
		    request.getRequestDispatcher("/_menu_css.jsp").include(request, response);
		    out.println("<h2>All Car Reservation</h2>");
		    
	        try{		        	
	            Statement stmt = ConnectorJ.con.createStatement();
	            StringBuilder sb = new StringBuilder();

	            String sql = sb.append("select * from car_rental_system.reservation").toString();
	            System.out.println(sql);
	            
	            try{
	                ResultSet rs = stmt.executeQuery(sql);
	                /**
	                 * To print Car Item Information from Car Item table.
	                 */
	                request.getRequestDispatcher("/table_css.jsp").include(request, response);
	                out.println("<table>");
	            	out.print("<tr>"
	            	        +"<th>Reserve ID</th>"
	            	        +"<th>Reservation Date</th>"
	            	        +"<th>Pick Up Date</th>"
	            	        +"<th>Expected Return Date</th>"
	            	        +"<th>Pick Up Place</th>"
	            	        +"<th>Customer Resident ID</th>"
	            	        +"<th>Car Model Code</th>"
	            	        +"<th>Actions</th>"
	            	        +"</tr>");
	                
	                while(rs.next()){
	                	out.print("<tr>");
	                	out.println("<td>" + rs.getString("reserveID") + "</td>");
	                	out.println("<td>" + rs.getDate("dateReserved") + "</td>");
	                	out.println("<td>" + rs.getDate("datePickUP") + "</td>");
	                	out.println("<td>" + rs.getDate("dateExpectedReturn") + "</td>");
	                	out.println("<td>" + rs.getString("pickUpPlace") + "</td>");
	                	out.println("<td>" + rs.getString("residentID") + "</td>");
	                	out.println("<td>" + rs.getString("modelCode") + "</td>");
	                	out.println("<td>"
	                				+ "<a href=\"#?code=${reservation.number}\">Edit</a><br>"
	                				+ "<a href=\"#?code=${reservation.number}\">Cancel</a><br>"
	                				+ "</td>");
	                	out.print("</tr>");
	                }
	                out.println("</table>");
	                
					request.getRequestDispatcher("/_footer.jsp").include(request, response);
					out.println("</body>"); 
					out.println("</html>");
					
					rs.close();
					stmt.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
