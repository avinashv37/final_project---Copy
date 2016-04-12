package com.smartScheduler.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Manager_edit
 */
@WebServlet("/Manager_edit")
public class Manager_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager_edit() {
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
		PrintWriter out = response.getWriter();
	    String firstName = request.getParameter("a");
	    System.out.println("From Servlet: " + firstName);
		String edit = request.getParameter("edit");
		String delete= request.getParameter("delete");
		String id = request.getParameter("eid");
		System.out.println("Schedule ID "+ edit);
		String managerviewaction=(String)session.getAttribute("managerviewaction");
		
		try {
			loginc.ManagerWorkscheduleEdit(edit,id,delete,uid,managerviewaction);
			if(id!=null )
			{
			result=loginc.EmployerWorkscheduleView(2,uid);
			System.out.println("delete ");
			}
			else
			{
				result=loginc.EmployerWorkscheduleView(1,uid);
				System.out.println("not delete ");
			}
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("ManagerScheduleView.jsp").forward(request, response);
        loginc.closingConnection();
	}


}
