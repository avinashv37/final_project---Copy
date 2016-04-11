/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartScheduler.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartScheduler.connection.Database;

/**
 *
 * @author avinash
 */
@WebServlet("/SignupController")
public class Signup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int employerid=0;
        String username=null,uname=null;
        String password=null,pwd=null;
        String usertype=null,utype=null;
        String firstname=null,fname=null;
        String lastname=null,lname=null;
        String email =null,mail=null;
        String dateofbirth =null;
        Date dob=null;
        String gender=null,gnd=null;
        String country=null,cnt=null;
        String state=null,st=null;
        String city=null,cty=null;
        String occupation=null,oct=null;
        String department=null,dept=null;
        String phonenumber=null,num=null;
        String marital=null,mar=null;
        Date createdate=null,cdate=null;
        
        employerid=Integer.parseInt(request.getParameter("employerID"));
        username=request.getParameter("name");
        password=request.getParameter("password");
        usertype=request.getParameter("utype");
        firstname=request.getParameter("firstname");
        lastname=request.getParameter("lastname");
        email=request.getParameter("email");
        dateofbirth = request.getParameter("DOB");
        gender=request.getParameter("gender");
        country=request.getParameter("country");
        state=request.getParameter("state");
        city = request.getParameter("city");
        occupation = request.getParameter("occupation");
        department = request.getParameter("department");
        phonenumber= request.getParameter("phone");
        marital= request.getParameter("marital");
        
        int i=1,r=0,a=1;
        HttpSession session = request.getSession();
		Database loginc = new Database();
		Database.makingConnection();
        
		try {
	    	a=loginc.adduserinfo(employerid, username, password);
			r=loginc.getroleid(usertype);
			i=loginc.signup(employerid, firstname, lastname,
			email, dateofbirth, gender, country, state, city, 
			phonenumber, marital,r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dateofbirth);
		System.out.println("the role id is"+r);
    	
    	 if(i==0&&a==0)
         {
    		 System.out.println("some error in database signup");
    		 response.sendRedirect("signup.jsp");
    		// request.setAttribute("error message", "Employer already exists");
    		// getServletConfig().getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
         }
    	 else
    	 if(i==1&&a==1)
    	 {
    		 response.sendRedirect("login.jsp");
    	 }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
