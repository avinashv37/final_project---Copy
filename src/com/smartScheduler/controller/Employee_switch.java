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
 * Servlet implementation class Employee_switch
 */
@WebServlet("/Employee_switch")
public class Employee_switch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee_switch() {
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
		Database loginc=new Database();
		loginc.makingConnection();
		Result result=null;
		HttpSession session = request.getSession();
		
		String uid=(String) session.getAttribute("uid");
		String edit = request.getParameter("edit");
		String switchid= request.getParameter("switchid");
		System.out.println("Schedule ID "+ edit);
		System.out.println("Switch ID"+switchid);
		
		try {
			loginc.Employer_switch(edit,switchid,uid);
			result=loginc.EmployerWorkscheduleView(1,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("employer.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
