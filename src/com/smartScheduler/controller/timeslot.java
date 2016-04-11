package com.smartScheduler.controller;

import java.io.IOException;
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
 * Servlet implementation class timeslot
 */
@WebServlet("/timeslot")
public class timeslot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public timeslot() {
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
		int i;
		String slotname=null;
		Map Map = null;
		Map slot= new HashMap<String,String>();
		int count=0;
		String somefield= request.getParameter("someField");
		if(somefield!= "")
		{
		count = Integer.parseInt(somefield);
		}
		else
		{
			count=0;
		}
		// getting same input names fromthe jsp page as array
		String[] timeslot = request.getParameterValues("timeslot");
		String[] slotavailable = request.getParameterValues("slotavailable");
		String[] start_time = request.getParameterValues("start_time");
		String[] end_time = request.getParameterValues("end_time");
		
		System.out.println(count);
		
//		count=Integer.parseInt(somefield);
		//System.out.println ("somefield"+somefield);
		System.out.println("count is "+count);
		System.out.println();
		
		//time slot dynamic screening
		System.out.println("tim slot 0"+timeslot[0]);
		System.out.println("slot available"+slotavailable[0]);
		System.out.println("start_time"+start_time[0]);
		System.out.println("end_time"+end_time[0]);
		
		slotname=request.getParameter("slotname");
		for(i=0;i<=count;i++)
		{
			System.out.println(i);
		slot.put("timeslot"+i,timeslot[i]);
		//System.out.println("param"+request.getParameter("timeslot"+i));
		slot.put("slotavailable"+i,slotavailable[i]);
		slot.put("st"+i,start_time[i]);
		System.out.println("start time"+i+start_time[i]);
		slot.put("et"+i,end_time[i]);
		
		}
		HttpSession session = request.getSession();
		Database loginc = new Database();
		Database.makingConnection();
		String uid=(String) session.getAttribute("uid");

		
        try
        {
        	i=loginc.timeslot(slotname, slot,count,uid);
        	System.out.println(i);
        }
        catch (Exception e)
        {
        	System.out.println("Sssssssssssa");
        	System.out.println(e);
        }
		
		
	}

}
