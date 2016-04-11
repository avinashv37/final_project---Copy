package com.smartScheduler.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import com.smartScheduler.connection.Database;

/**
 * Servlet implementation class Employer_workschedule_edit
 */
@WebServlet("/Employer_workschedule_edit")
public class Employer_workschedule_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employer_workschedule_edit() {
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
		String action = request.getParameter("click");
		
		String uid=(String) session.getAttribute("uid");
		// Time formatting and parsing
		String st= null;
		String et = null;
		java.sql.Time fst = null;
		java.sql.Time fet=null;
		String datestring = null;
		
		// Date formatting and parsing
		
		datestring = request.getParameter("date");
		
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		Date date = null;
		Date sdate=null;
		String datef = null;
		try {
			date =formatter.parse(datestring);
			datef = newFormat.format(date);
			sdate = newFormat.parse(datef);
		//	date = Date.parse(datef);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date fdate=java.sql.Date.valueOf(newFormat.format(sdate));
		
		
		
		System.out.println("username is "+username);
		System.out.println(action);
		
		Database loginc=new Database();
		loginc.makingConnection();
		
		if("open_schedule".equals(action))
		{
			
		try {
			int value=1;
			//fdate=null;fst=null;fet=null;
			System.out.println("going in ");
			result=loginc.Employeraddchedule(fdate, fst,fet,value,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("display_schedule".equals(action))
		{
			
		try {
			int value=2;
			//fdate=null;fst=null;fet=null;
			System.out.println("going in ");
			result=loginc.Employeraddchedule(fdate, fst,fet,value,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if("add_me".equals(action))
		{
			// format for date
			DateFormat timeformat = new SimpleDateFormat("HH:mm");
			
			// 
			st= request.getParameter("start_time");
			et = request.getParameter("end_time");
			
			int value=3;
			try {
				result=loginc.Employeraddchedule(fdate, fst,fet,value,uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("result", result);
        request.getRequestDispatcher("EmployerWorkSchedule.jsp").forward(request, response);
        loginc.closingConnection();
	}

}
