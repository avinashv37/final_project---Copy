
package com.smartScheduler.controller;

import com.smartScheduler.connection.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
///import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;


/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=null,uname=null;
		String password=null,pword=null;
		int i = 0;
		username=request.getParameter("name");
		password=request.getParameter("password");
		HttpSession session = request.getSession();
		Database loginc = new Database();
		Result result=null;
		System.out.println("going in connection");
		Database.makingConnection();
		System.out.println("going in connection");
                    try {
                            i= loginc.authenticate(username, password);
                            PrintWriter out = response.getWriter();
                            out.println(i);
                                    
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                        if(i==1)
                        {
                        session.setAttribute("fname",loginc.name);
                        session.setAttribute("uid", loginc.uid);
                        request.setAttribute("var", " ");
                        request.getRequestDispatcher("manager.jsp").forward(request, response);
                        }
                        if(i==2)
                        {	
                        session.setAttribute("fname",loginc.name);
                        session.setAttribute("uid", loginc.uid);
                        request.setAttribute("var", " ");
            			try {
							result=loginc.EmployerWorkscheduleView(1,loginc.uid);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            			request.setAttribute("result", result);
            	        request.getRequestDispatcher("employer.jsp").forward(request, response);
            	  
            			
                        }

                        if(i==0)
                        {
                                System.out.println("not logingin");
                                request.setAttribute("var", "The password or user name entered is not valid");
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                        //response.sendRedirect("login.jsp");
                        }
                    loginc.closingConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
