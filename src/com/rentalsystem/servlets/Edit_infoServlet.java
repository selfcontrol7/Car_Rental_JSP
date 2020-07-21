package com.rentalsystem.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentalsystem.beans.Member;
import com.rentalsystem.beans.Type.CreditCard;
import com.rentalsystem.beans.Type.MemberType;

/**
 * Servlet implementation class member_registration
 */
@WebServlet("/Edit_infoServlet")
public class Edit_infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String resident_id = request.getParameter("resident_id");
		String name = request.getParameter("first_name") + " " + request.getParameter("last_name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String affiliation = request.getParameter("affiliation");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String driving_license = request.getParameter("driving_license");
		String member_type = request.getParameter("member_type");
		Integer credit_rate = Integer.parseInt(request.getParameter("credit_rate").trim());
		String credit_card = request.getParameter("credit_card");
		LocalDate joiningDate = LocalDate.now();
		LocalDate expir_date = joiningDate.plusYears(3);

		/**
		 * Testing the inputed values if they are not empty
		 */
		if (resident_id == null || name.trim().isEmpty() || member_type.trim().isEmpty()
				|| credit_card.trim().isEmpty()) {

			RequestDispatcher req = request.getRequestDispatcher("edit_info.jsp");
			req.forward(request, response);
		} else {
			// MemberType membertype = MemberType.valueOf(member_type);
			CreditCard creditcard = CreditCard.valueOf(credit_card);

			PrintWriter out = response.getWriter();
			out.println(creditcard);
			out.println(credit_rate);
			out.println(resident_id);
			
			Member Editmember = new Member(); 
			Editmember.modifyMembeInfo(resident_id, credit_rate, creditcard);
			  
			this.getServletContext().getRequestDispatcher("/my_info.jsp" ).forward(request, response );
		}
	}
}