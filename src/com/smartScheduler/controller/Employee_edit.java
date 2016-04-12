package com.smartScheduler.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Employee_edit
 */
@WebServlet("/Employee_edit")
public class Employee_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee_edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());	}

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
		PrintWriter out = response.getWriter();
	    String firstName = request.getParameter("a");
	    System.out.println("From Servlet: " + firstName);
		String edit = request.getParameter("edit");
		String viewaction=(String) session.getAttribute("viewaction");
		String asa = request.getParameter("resolution");
		System.out.println("Schedule ID "+ edit);
		System.out.println("display schedule");
		
		try {
			loginc.EmployerWorkscheduleEdit(edit,uid,viewaction);
			result=loginc.EmployerWorkscheduleView(2,uid);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("employer.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
