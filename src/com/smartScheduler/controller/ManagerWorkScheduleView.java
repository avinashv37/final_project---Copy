package com.smartScheduler.controller;
import com.smartScheduler.connection.Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

/**
 * Servlet implementation class Signout
 */
@WebServlet("/Manager_Display")
public class ManagerWorkScheduleView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerWorkScheduleView() {
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
		HttpSession session = request.getSession();
		Database loginc = new Database();
		loginc.makingConnection();
		int i=1;
		Result result = null;
		ArrayList<String> nameList=null;

		String uid=(String) session.getAttribute("uid");
		String managerviewaction=request.getParameter("click");
		session.setAttribute("managerviewaction", managerviewaction);
		
		if("schedule".equals(managerviewaction))
		{
			i=1;
		try {
			result = loginc.ManagerWorkscheduleView(i,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("not_schedule".equals(managerviewaction))
		{
			i=2;
		try {
			result=loginc.EmployerWorkscheduleView(1,uid);
			nameList=loginc.getname();
			result = loginc.ManagerWorkscheduleView(i,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		request.setAttribute("result", result);
		request.setAttribute("name", nameList);
		request.setAttribute("verify",managerviewaction);
        request.getRequestDispatcher("ManagerScheduleView.jsp").forward(request, response);
        loginc.closingConnection();
        	
	}

}
