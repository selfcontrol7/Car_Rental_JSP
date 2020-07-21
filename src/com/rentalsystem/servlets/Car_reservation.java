package com.rentalsystem.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentalsystem.beans.CarModel;

/**
 * Servlet implementation class Car_reservation
 */
@WebServlet("/Car_reservation")
public class Car_reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Car_reservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		CarModel listcar = new CarModel();
		
        try {
        	 
            List<CarModel> listCarModel = listcar.displayAllModels();
            request.setAttribute("listCarModel", listCarModel);
 
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservaton.jsp");
            dispatcher.forward(request, response);
 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//String modelCode = request.getParameter("modelCode");
		//int categoryId = Integer.parseInt(request.getParameter("category"));
		 
	    //request.setAttribute("selectedCatId", categoryId);
	 
	    //listCategory(request, response);		
	}

}
