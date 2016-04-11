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
 * Servlet implementation class EmployeeWorkScheduleSwitchEdit
 */
@WebServlet("/ManagerWorkScheduleSwitchEdit")
public class ManagerWorkScheduleSwitchEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerWorkScheduleSwitchEdit() {
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
		String action = request.getParameter("edit");
		System.out.println(action);
		Database loginc=new Database();
		loginc.makingConnection();
		
		
			System.out.println("edit");
		try {
			loginc.ManagerScheduleSwitchEdit(1,action,uid);
			result=loginc.ManagerSwitchingStatus(1,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("result", result);
        request.getRequestDispatcher("ManagerSwitchingStatus.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
