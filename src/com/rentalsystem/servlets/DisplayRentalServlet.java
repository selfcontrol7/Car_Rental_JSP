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
import com.rentalsystem.beans.Rental;;

/**
 * Servlet implementation class DisplayCarItemServlet
 */
@WebServlet("/DisplayRentalServlet")
public class DisplayRentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Rental DisplayRental;

    public void init() {
    	DisplayRental = new Rental();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayRentalServlet() {
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
         * To retrieve All Car Rental
         * @param Nothing
         * @return boolean This parameter is to check whether retrieving has done completely or not.
         * @exception SQLException When exception occurs, returns 'false'.
         */
        ConnectorJ.loadDriver();
        ConnectorJ.connectToServer();

		
	    String title = "Display All Car Rental";
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
		    out.println("<h2>All Car Rental</h2>");
		    
	        try{		        	
	            Statement stmt = ConnectorJ.con.createStatement();
	            StringBuilder sb = new StringBuilder();

	            String sql = sb.append("select * from car_rental_system.rental").toString();
	            System.out.println(sql);
	            
	            try{
	                ResultSet rs = stmt.executeQuery(sql);
	                /**
	                 * To print Car Item Information from Car Item table.
	                 */
	                request.getRequestDispatcher("/table_css.jsp").include(request, response);
	                out.println("<table>");
	            	out.print("<tr>"
	            	        +"<th>Rental ID</th>"
	            	        +"<th>Return Date</th>"
	            	        +"<th>Checked Out On</th>"
	            	        +"<th>Expected Return Date</th>"
	            	        +"<th>Insurance Option</th>"
	            	        +"<th>Rental Fee</th>"
	            	        +"<th>Payment Status</th>"
	            	        +"<th>Residen ID</th>"
	            	        +"<th>VIN</th>"
	            	        +"<th>Reservation ID</th>"
	            	        +"<th colspan='2'>Actions</th>"
	            	        +"</tr>");
	                
	                while(rs.next()){
	                	out.print("<tr>");
	                	out.println("<td>" + rs.getString("rentalID") + "</td>");
	                	out.println("<td>" + rs.getDate("dateDue") + "</td>");
	                	out.println("<td>" + rs.getDate("dateCheckedOut") + "</td>");
	                	out.println("<td>" + rs.getDate("dateExpectedReturn") + "</td>");
	                	out.println("<td>" + rs.getString("insuranceOption") + "</td>");
	                	out.println("<td>" + rs.getString("rentalFee") + "</td>");
	                	out.println("<td>" + rs.getInt("paymentStatus") + "</td>");
	                	out.println("<td>" + rs.getString("residentID") + "</td>");
	                	out.println("<td>" + rs.getString("VIN") + "</td>");
	                	out.println("<td>" + rs.getString("reserveID") + "</td>");
	                	out.println("<td>"
	                				+ "<a href=\"#?code=${reservation.number}\">Check Out</a><br>"
	                				+ "<a href=\"#?code=${reservation.number}\">Make Payment</a><br>"
	                				+ "<a href=\"#?code=${reservation.number}\">Extend Period</a><br></td>");
	                	out.println("<td>"
	                				+ "<a href=\"#?code=${reservation.number}\">Add a Driver</a><br>"
	                				+ "<a href=\"#?code=${reservation.number}\">Return Car</a><br>"
	                				+ "<a href=\"#?code=${reservation.number}\">Report Problem</a><br></td>");
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
