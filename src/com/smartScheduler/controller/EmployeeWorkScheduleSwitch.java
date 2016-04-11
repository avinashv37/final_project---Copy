package com.smartScheduler.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import com.smartScheduler.connection.Database;

/**
 * Servlet implementation class EmployeeWorkScheduleSwitch
 */
@WebServlet("/EmployeeWorkScheduleSwitch")
public class EmployeeWorkScheduleSwitch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeWorkScheduleSwitch() {
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
		String action="waiting";
		action = request.getParameter("click");
		System.out.println(action);
		Database loginc=new Database();
		loginc.makingConnection();
		
		if("waiting".equals(action))
		{
			System.out.println("waiting");
		try {
			result=loginc.EmployeeSwitchingStatus(1,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("accepted".equals(action))
		{
			
			System.out.println("accepted");
		try {
			result=loginc.EmployeeSwitchingStatus(2,uid);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("rejected".equals(action))
		{
			
			System.out.println("rejected");
		try {
			result=loginc.EmployeeSwitchingStatus(3,uid);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		request.setAttribute("result", result);
        request.getRequestDispatcher("SwitchingStatus.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
