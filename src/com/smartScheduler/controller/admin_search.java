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
 * Servlet implementation class admin_search
 */
@WebServlet("/admin_search")
public class admin_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_search() {
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

		String employeeid=null,employee=null,manager=null,manage=null;
		int i = 0,ip = 1;
		employeeid=request.getParameter("employid");
		employee=request.getParameter("employee");
		manage =request.getParameter("manage");
		String action = request.getParameter("click");
		HttpSession session = request.getSession();
		Database loginc = new Database();
		System.out.println("going in connection");
		loginc.makingConnection();
		System.out.println("going in connection");
					PrintWriter out = response.getWriter();
					out.println(i);
        Result result=null;
        System.out.println("the action is "+action);
        if("search".equals(action))
        {
        	ip=1;
        result =loginc.adminmain(ip,employeeid,manager);
		loginc.closingConnection();
        }
        if("promote".equals(action))
        {
        	ip=2;
        result =loginc.adminmain(ip,employeeid,manager);
        try {
			loginc.updaterole(employeeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginc.closingConnection();
        }
        if("display".equals(action))
        {
        	ip=0;
        result =loginc.adminmain(ip,employeeid,manager);
		loginc.closingConnection();
        }
        if("setmanager".equals(action))
        {
        	ip=3;
            result =loginc.adminmain(ip,employee,manage);
			loginc.closingConnection();
        }
        if("displaymanager".equals(action))
        {
        	ip=4;
            result =loginc.adminmain(ip,employee,manage);
            loginc.closingConnection();
            request.setAttribute("result", result);
            request.getRequestDispatcher("ManagerPromote.jsp").forward(request, response);
            return;
        }
        if("Employer".equals(action))
        {
        	ip=5;
        result =loginc.adminmain(ip,employeeid,manager);
		loginc.closingConnection();
		request.setAttribute("result", result);
        request.getRequestDispatcher("ManagerPromote.jsp").forward(request, response);
        }
        System.out.println(result);
        request.setAttribute("result", result);
        request.getRequestDispatcher("admin_main.jsp").forward(request, response);
                       
	}

}
