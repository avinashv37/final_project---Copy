
package com.smartScheduler.controller;

import com.smartScheduler.connection.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
///import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;


/**
 * Servlet implementation class MainController
 */
@WebServlet("/AdminLogin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
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
		String username=null,uname=null;
		String password=null,pword=null;
		String employeeid=null;
		String manager=null;
		int i = 0,ip = 0;
		username=request.getParameter("name");
		password=request.getParameter("password");
		HttpSession session = request.getSession();
		Database loginc = new Database();
		System.out.println("going in connection");
		loginc.makingConnection();
		System.out.println("going in connection");
                    i= loginc.admincheck(username, password);
					PrintWriter out = response.getWriter();
					out.println(i);
                        if(i==1)
                        {
                        session.setAttribute("uname",uname);
                        session.setAttribute("pwd",pword);
                        Result result=null;
                        result =loginc.adminmain(ip,employeeid,manager);
                        System.out.println(result);
                        request.setAttribute("result", result);
                        request.getRequestDispatcher("admin_main.jsp").forward(request, response);
                        return;
                        }

                        if(i==0)
                        {
                                System.out.println("not logingin");
                                response.sendRedirect("admin.jsp");
                        }
	}

}
