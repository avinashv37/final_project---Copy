package com.smartScheduler.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import com.smartScheduler.connection.Database;

/**
 * Servlet implementation class Employer_workschedule
 */
@WebServlet("/Employer_workschedule_view")
public class Employer_workschedule_View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employer_workschedule_View() {
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
		String username=null;
		Result result=null;
		HttpSession session = request.getSession();
		username=request.getParameter("name");
		System.out.println("username is "+username);
		
		String uid=(String) session.getAttribute("uid");
		String action="display_schedule";
		action = request.getParameter("click");
		session.setAttribute("viewaction", action);
		System.out.println(action);
		Database loginc=new Database();
		loginc.makingConnection();
		
		if("display_schedule".equals(action))
		{
			
		try {
			result=loginc.EmployerWorkscheduleView(1,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("open_schedule".equals(action))
		{
			
			System.out.println("open schedule");
		try {
			result=loginc.EmployerWorkscheduleView(2,uid);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		request.setAttribute("result", result);
        request.getRequestDispatcher("employer.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
