package com.smartScheduler.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartScheduler.connection.Database;

/**
 * Servlet implementation class sugestive_search
 */
@WebServlet("/sugestive_search")
public class sugestive_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sugestive_search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String buffer="";  
		try{
		   Class.forName("com.mysql.jdbc.Driver");
		   Database loginc= new Database();
		   loginc.makingConnection();
		   buffer =loginc.SuggestiveSearch(name);
		   
		   response.getWriter().println(buffer);
		}
		 catch (Exception e) {
		    e.printStackTrace();
		 }
	}

}
