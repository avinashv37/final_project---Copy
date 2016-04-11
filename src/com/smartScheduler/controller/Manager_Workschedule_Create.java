package com.smartScheduler.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartScheduler.connection.Database;

/**
 * Servlet implementation class workschedule
 */
@WebServlet("/workschedule")
public class Manager_Workschedule_Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager_Workschedule_Create() {
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
		String datestring = request.getParameter("date");
		int i;
		DateFormat formatter;
		Map Map = null;
		Map schedule= new HashMap<String,String>();
		 System.out.println(datestring);
		 
		formatter = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null,sdate=null;
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
		System.out.println("date formatted"+formatter.format(date));
		System.out.println("my new date string is "+datef);
		System.out.println("date new formatted"+newFormat.format(date));
		String st1=request.getParameter("start_time1");
		String st2=request.getParameter("start_time2");
		String st3=request.getParameter("start_time3");
		String st4=request.getParameter("start_time4");
		String st5=request.getParameter("start_time5");
		String st6=request.getParameter("start_time6");
		String st7=request.getParameter("start_time7");
		String et1=request.getParameter("end_time1");
		String et2=request.getParameter("end_time2");
		String et3=request.getParameter("end_time3");
		String et4=request.getParameter("end_time4");
		String et5=request.getParameter("end_time5");
		String et6=request.getParameter("end_time6");
		String et7=request.getParameter("end_time7");
		String sl1=request.getParameter("slot1");
		String sl2=request.getParameter("slot2");
		String sl3=request.getParameter("slot3");
		String sl4=request.getParameter("slot4");
		String sl5=request.getParameter("slot5");
		String sl6=request.getParameter("slot6");
		String sl7=request.getParameter("slot7");
	//	DateFormat formatter = new SimpleDateFormat("HH:mm");
		
		try {
			System.out.println(sl1);

			schedule.put("st1",st1);
			schedule.put("et1",et1);
			schedule.put("sl1",sl1);
			schedule.put("st2",st2);
			schedule.put("et2",et2);
			schedule.put("sl2",sl2);
			schedule.put("st3",st3);
			schedule.put("et3",et3);
			schedule.put("sl3",sl3);
			schedule.put("st4",st4);
			schedule.put("et4",et4);
			schedule.put("sl4",sl4);
			schedule.put("st5",st5);
			schedule.put("et5",et5);
			schedule.put("sl5",sl5);
			schedule.put("st6",st6);
			schedule.put("et6",et6);
			schedule.put("sl6",sl6);
			schedule.put("st7",st7);
			schedule.put("et7",et7);
			schedule.put("sl7",sl7);
			
			HttpSession session = request.getSession();
			String uid=(String) session.getAttribute("uid");

			Database loginc = new Database();
			loginc.makingConnection();
			loginc.managerscheduler(newFormat.format(sdate),newFormat,schedule,uid);
			response.sendRedirect("ManagerWorkSchedule.jsp");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
