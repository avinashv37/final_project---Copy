package com.smartScheduler.connection;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.omg.PortableInterceptor.INACTIVE;

import com.smartScheduler.controller.Employee_edit;
import com.smartScheduler.controller.Signin;

public class Database {


private static final int Main_Slot_name = 0;
private static Connection conn;
public String uid;
public static String name;
public String employee_edit;
public String Manager_edit;


	public static void makingConnection()
	{
		try
		{
                Class.forName("com.mysql.jdbc.Driver");
                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_scheduler1","root","nothing1");

               
                }
                catch(Exception e)
                {
                	System.out.println("no connection");
                System.out.println(e);
                }
		
	}
	
	public static void closingConnection()
	{
		try {
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int authenticate(String username,String password) throws SQLException
	{
		PreparedStatement ptmt,ptmt1,ptmt2;
		String uname=null,pword=null;
		int i = 0;
                try
                {
        ptmt = conn.prepareStatement("select * from user_info where username='"+username+"' AND BINARY password='" +password+"'");  
        ptmt1=conn.prepareStatement("select Manager_ID from manager_info where Manager_ID=(SELECT `user_ID` FROM user_info where UserName='"+username+"' and password='" +password+"')");
	    ptmt2=conn.prepareStatement("select * from employee_info where Employee_ID=(SELECT `user_ID` FROM user_info where UserName='"+username+"' and password='" +password+"')");
        ResultSet rs1=ptmt.executeQuery();
		ResultSet rs2=ptmt1.executeQuery();
		ResultSet rs3=ptmt2.executeQuery();
                    while(rs1.next())
                    {
                        System.out.println("rs1");
                        if(rs2.next()&&rs3.next())
                        	
                        	
                        {
                        	  System.out.println("rs2 & rs3");
                        	name=rs3.getString("FirstName");
                            uid=rs1.getString("user_ID");
                            i =1;
                            System.out.println(uid);
                            System.out.println(name);
                        }
                        else if(rs3.next())
                        {
                        	  System.out.println("rs3");
                        	name=rs3.getString("FirstName");
                            uid=rs1.getString("user_ID");
                            // Signin signed= new Signin();
                            i =2;     
                            System.out.println(uid);
                            System.out.println(name);
                        }
                    }
                    rs2.close();
                    rs1.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
		return i;
	}
	
	public int adduserinfo(int employeeid,String username,String password) throws SQLException
	{
		PreparedStatement ps;
        ps= conn.prepareStatement("INSERT INTO user_info (user_id,UserName,Password)VALUES(?,?,?)");
        ps.setInt(1,employeeid);
        ps.setString(2,username);
        ps.setString(3,password);
        int x=ps.executeUpdate();
        
		return x;
	
	}

	public int getroleid(String usertype)  {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int i=0;
		int x=0;
		ResultSet rs;
		try {
		ps= conn.prepareStatement("SELECT role_ID from role where description='"+usertype+"'");
		rs = ps.executeQuery();
		while(rs.next())
        {
            i++;
            x=rs.getInt("role_id");
        }
		 rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return x;
    }

	
	public int signup(int employeeid,String firstname,String lastname,String email, String dob,
	 String gender,String country,String state,String city ,String phonenumber,String marital,int a) throws SQLException
	{
		PreparedStatement ps;
		
        ps= conn.prepareStatement("INSERT INTO Employee_info (Employee_id,FirstName,LastName,EmailID,dob,Gender,Country,State,City,PhoneNumber,MaritalStatus,role_id)"
        		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1,employeeid);
        ps.setString(2,firstname);
        ps.setString(3,lastname);
        ps.setString(4, email);
        ps.setString(5, dob);
        ps.setString(6, gender);
        ps.setString(7, country);
        ps.setString(8, state);
        ps.setString(9, city);
        ps.setString(10, phonenumber);
        ps.setString(11, marital);
        ps.setInt(12, 1);
        int x=ps.executeUpdate();
        return x;
	}

	public int admincheck(String username, String password) {
		// TODO Auto-generated method stub
		
		PreparedStatement ptmt;
		String uname=null,pword=null;
		int i = 0;
                try
                {
        ptmt = conn.prepareStatement("select * from admin_info where UserName='"+username+"' AND Password='" +password+"'");  
       	ResultSet rs1=ptmt.executeQuery();
                    while(rs1.next())
                    {
                        i++;
                       
                           uname=rs1.getString("username");
                           pword=rs1.getString("password");
                           // Signin signed= new Signin();
                            System.out.println("not rs1");
                        
                    }
                    rs1.close();
                }
                catch(Exception e)
                {
                    System.err.println(e);
                }
		return i;
	}
	public Result adminmain(int i,String employeeid,String managerid)  {
		// TODO Auto-generated method stub
		
		PreparedStatement ptmt;
		PreparedStatement ptmt1;
		ResultSet rs = null;
		ResultSet rs1=null;
		Result result = null;
		   try
		   {
		    if(i==0)
		    {
       	ptmt = conn.prepareStatement("SELECT employee_info.employee_id,employee_info.firstname,employee_info.lastname,role.description,employee_info.managerid FROM employee_info LEFT JOIN role ON employee_info.Role_ID=role.Role_ID ");  
		rs=ptmt.executeQuery();
		    }
		    if(i==1)
		    {
		    	System.out.println("the number"+employeeid);
		ptmt = conn.prepareStatement("SELECT employee_info.employee_id,employee_info.firstname,employee_info.lastname,role.description,employee_info.managerid FROM employee_info LEFT JOIN role ON employee_info.Role_ID=role.Role_ID where Employee_ID='"+employeeid+"'");  
		rs=ptmt.executeQuery();
		    }
		    if(i==2)
		    {
		    	System.out.println("the number"+employeeid);
		 ptmt=conn.prepareStatement("INSERT INTO manager_info (Manager_ID)"+ "VALUE('"+employeeid+"')");
		 ptmt.executeUpdate();
		 ptmt.close();
		    }
		    if(i==3)
		    {
		    	System.out.println("the number"+employeeid);
		 ptmt=conn.prepareStatement("UPDATE employee_info SET ManagerID ='"+managerid+"' WHERE Employee_ID = '"+employeeid+"'");
		 ptmt.executeUpdate();
		 ptmt.close();
		    }
		   if(i==4)
		   {
			   ptmt=conn.prepareStatement("SELECT Employee_ID,FirstName,LastName,PhoneNumber FROM employee_info INNER JOIN manager_info ON manager_info.Manager_ID=employee_info.Employee_ID;");
				 rs=ptmt.executeQuery();   
				 
		   }
		   
		   if(i==5)
		   {
			   ptmt=conn.prepareStatement("SELECT Employee_ID,FirstName,LastName,PhoneNumber FROM employee_info where role_id=1");
			   rs=ptmt.executeQuery();
		   }
		   result = ResultSupport.toResult(rs);
		   }
		   catch(Exception e)
		   {
			   
		   }
               
		return result;
	}
	public void updaterole(String employeeid) throws SQLException
	{
		PreparedStatement ptmt1;
		ptmt1=conn.prepareStatement("UPDATE employee_info SET Role_ID='2' WHERE Employee_ID = '"+employeeid+"'");
		 ptmt1.executeUpdate();
		 ptmt1.close();
	}
	public int timeslot(String slotname,Map slot,int count,String uid2 ) throws SQLException, ParseException
	{
		int i,x = 0;
		i=2;
		for(i=0;i<=count;i++)
		{
		PreparedStatement ps;
		
		ps=conn.prepareStatement("INSERT INTO time_slot_memory (Main_Slot_name,Slot_Name,Manager_ID,Start_time,end_time)"
		+ "VALUES(?,?,?,?,?)");
	
		ps.setString(1,slotname);
		System.out.println(count);
		ps.setString(2,(String) slot.get("timeslot"+i));
		ps.setString(3,uid2);
		System.out.println("database time slot key "+(String) slot.get("timeslot"+i));
		System.err.println("database start time "+(String) slot.get("st"+i));
		
		// fromatting time.
		DateFormat timeformat = new SimpleDateFormat("HH:mm");
		
		//setting the formatted time and parsing time to format.
		ps.setTime(4, new java.sql.Time(timeformat.parse((String) slot.get("st"+i)).getTime()));
		ps.setTime(5, new java.sql.Time(timeformat.parse((String) slot.get("et"+i)).getTime()));
		//ps.setString(6,(String) slot.get("slotavailable"+i));
		x=ps.executeUpdate();
		ps.close();
		}
		return x;
	}

	public void managerscheduler(String sdate,DateFormat df,Map schedule,String uid2) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		DateFormat timeformat = new SimpleDateFormat("HH:mm");
		PreparedStatement ps;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ResultSet rs;
		
		for(int i=1;i<=7;i++)
		{
		
		java.sql.Date sqldate=null;
		java.sql.Time sqlst=null;
		java.sql.Time sqlet=null;
		
		if(sdate!=""&& (String) schedule.get("st"+i)!=""&&(String) schedule.get("st"+i)!="")
		{
		sqldate=java.sql.Date.valueOf(sdate);
		sqlst=new java.sql.Time(timeformat.parse((String) schedule.get("st"+i)).getTime());
		sqlet=new java.sql.Time(timeformat.parse((String) schedule.get("et"+i)).getTime());
		}
		if(schedule.get("st"+i)!=null && (String) schedule.get("sl"+i)!="" )
		{
			System.out.println(Integer.parseInt(((String) schedule.get("sl"+i)).trim()));
			System.out.println("manager schedule "+sdate);
		
		//ps=conn.prepareStatement("Select date,start_time,end_time from time_slot where );	
		//rs=ps.executeQuery();
		//rs.getInt("manager_id");
			
		ps1=conn.prepareStatement("INSERT INTO time_slot (Date,Manager_ID,Start_time,End_time) VALUE(?,?,?,?)");
		ps1.setDate(1,(sqldate));
		ps1.setInt(2,Integer.parseInt(uid2));
		ps1.setTime(3, sqlst);
		ps1.setTime(4, sqlet);
		ps1.executeUpdate();
		ps1.closeOnCompletion();
		int slot =Integer.parseInt((String)schedule.get("sl"+i));
		for(int j=0;j<slot;j++){
		// ps for insterting in work scheduleCT slot_id FROM ti
		ps2=conn.prepareStatement("INSERT INTO work_schedule (Slot_ID) VALUE( (SELECT slot_id FROM time_slot where date='"+sqldate+"' AND Start_time='"+sqlst+"' AND end_time='"+sqlet+"' limit 1));");
		ps2.executeUpdate();
		ps2.closeOnCompletion();
		}
		}
		}
	}

	public Result EmployerWorkscheduleView(int value,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+uid2);
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs=null;
		Result result = null;
		if(value==1)
		{
			employee_edit="display";
		ps=conn.prepareStatement("SELECT work_schedule.Employee_ID,Schedule_ID,employee_info.FirstName,DATE_FORMAT(date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join manager_info on manager_info.Manager_ID=time_slot.Manager_ID join employee_info on employee_info.Employee_ID= manager_info.Manager_ID where work_schedule.Employee_ID='"+uid2+"' AND date>=DATE_FORMAT(NOW(), '%m-%d-%Y') order by date asc, start_time asc;"  );
		rs=ps.executeQuery();
		result=ResultSupport.toResult(rs);
		ps.closeOnCompletion();
		rs.close();
		}
		if(value==2)
		{
			employee_edit="open";
		ps=conn.prepareStatement("SELECT Schedule_ID,time_slot.Slot_ID,employee_info.FirstName,DATE_FORMAT(date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join manager_info on manager_info.Manager_ID=time_slot.Manager_ID join employee_info on employee_info.Employee_ID= manager_info.Manager_ID where work_schedule.Employee_ID is NULL AND date>=DATE_FORMAT(NOW(), '%m-%d-%Y')order by date asc, start_time asc;");
		rs=ps.executeQuery();
		result=ResultSupport.toResult(rs);
		ps.closeOnCompletion();
		rs.close();
		}
		System.out.println(result);
		return result;
		
	}

	public Result Employeraddchedule(java.sql.Date date, Time fst, Time fet, int value,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs=null;
		Result result = null ;
		int slotid;
		System.out.println(value);
		if(value==1)
		{
			ps=conn.prepareStatement("SELECT employee_info.FirstName,DATE_FORMAT(date, '%m-%d-%Y')AS date,start_time,end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join manager_info on manager_info.Manager_ID=time_slot.Manager_ID join employee_info on employee_info.Employee_ID= manager_info.Manager_ID  where time_slot.Date='"+date+"'AND work_schedule.employee_id IS NULL order by date asc, start_time asc");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			System.out.println(result);
			ps.closeOnCompletion();
		}
		if(value==2)
		{
			ps=conn.prepareStatement("SELECT employee_info.FirstName,DATE_FORMAT(date, '%m-%d-%Y')AS date,start_time,end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join manager_info on manager_info.Manager_ID=time_slot.Manager_ID join employee_info on employee_info.Employee_ID= manager_info.Manager_ID  where time_slot.Date='"+date+"'AND work_schedule.employee_id ='"+uid2+"' order by date asc, start_time asc");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			System.out.println(result);
			ps.closeOnCompletion();
		}
		else
		if(value==3)
		{
		ps1= conn.prepareStatement("select min(Schedule_ID)as schedid from work_schedule JOIN time_slot ON time_slot.Slot_ID=work_schedule.Slot_ID where date ='"+date+"' AND employee_id is null");
		rs=ps1.executeQuery();
		String a = null;
		int x;
		//result=ResultSupport.toResult(rs);
		while (rs.next())
		{
		a=rs.getString("schedid");
		
		}
		System.out.println("schedule_id is "+a);
		x=Integer.parseInt(a);
		//slotid=rs.getInt("slot_id");
//		/System.out.println("the slot id is "+slotid);
		ps=conn.prepareStatement("UPDATE work_schedule set employee_id='"+uid2+"'where schedule_id='"+a+"'");
		ps.executeUpdate();
		result=ResultSupport.toResult(rs);
		ps.closeOnCompletion();
		}
		return result;
	}

	public Result ManagerWorkscheduleView(int i,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		PreparedStatement ps1;
		ResultSet rs=null;
		Result result = null ;
		int slotid;
			if(i==1)
			{
				Manager_edit="schedule";
			ps=conn.prepareStatement("SELECT work_schedule.Schedule_ID,employee_info.FirstName,employee_info.employee_id,DATE_FORMAT(date, '%m-%d-%Y')AS date,start_time,end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join employee_info on employee_info.Employee_ID= work_schedule.Employee_ID order by date");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			System.out.println(result);
			}
			if(i==2)
			{
				Manager_edit="not_schedule";
			ps=conn.prepareStatement("SELECT work_schedule.slot_id,work_schedule.Schedule_ID,DATE_FORMAT(date, '%m-%d-%Y')AS date,start_time,end_time FROM work_schedule join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID join employee_info on employee_info.Employee_ID= time_slot.Manager_ID Where work_schedule.Employee_ID is null order by date");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			System.out.println(result);
			}
			ps.closeOnCompletion();
		return result;
	}

	public void EmployerWorkscheduleEdit(String edit,String uid2,String employee_edit1) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(employee_edit1+uid2);
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs=null;
		if(employee_edit1.equals("display"))
		{
		ps=conn.prepareStatement("UPDATE `smart_scheduler1`.`work_schedule` SET `Employee_ID`=NULL WHERE `Schedule_ID`='"+edit+"'");
		ps.executeUpdate();
		ps.closeOnCompletion();
		ps1=conn.prepareStatement("UPDATE schedule_switch  SET Status = NULL where Schedule_ID='"+edit+"';");
		ps1.executeUpdate();
		ps1.closeOnCompletion();
		}
		else
		{
		CallableStatement cs = conn.prepareCall("CALL InsertOpen('"+uid2+"','"+edit+"')");
		rs=cs.executeQuery();
		System.out.println("calling statement");
		rs.close();
		}
		
		
	}

	/**
	 * @param edit : 
	 * @param id :
	 * @param delete :
	 * @throws SQLException
	 */
	public void ManagerWorkscheduleEdit(String edit,String id, String delete,String uid2,String Manager_edit1 ) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(Manager_edit1+uid2);
		PreparedStatement ps = null;
		PreparedStatement ps1=null;
		CallableStatement cs= null;
		ResultSet rs=null;
		//System.out.println("passing the manager edit");
		if(Manager_edit1.equals("not_schedule"))
		{
			System.out.println("passing the manager edit");
			if(delete!=null)
			{
				System.out.println("deleting time slot"+delete+id);
				ps=conn.prepareStatement("DELETE FROM work_schedule where Schedule_ID= '"+delete+"'");
				ps.executeUpdate();
				ps.closeOnCompletion();
			}
			else 
			{
			System.out.println("updating the manager"+edit+id);
		cs=conn.prepareCall("CALL InsertOpen('"+id+"','"+edit+"')");
		rs=cs.executeQuery();
		System.out.println("calling statement");
		rs.close();
			}
		
		}
		else if(Manager_edit1.equals("schedule"))
		{
			if(delete!=null)
			{
			System.out.println("deleting the manager row"+delete+id);
		ps=conn.prepareStatement("DELETE FROM `smart_scheduler1`.`work_schedule` WHERE `Schedule_ID`='"+delete+"'");
		ps.executeUpdate();
		ps.closeOnCompletion();
			}
			if(edit!=null)
			{
			System.out.println("removing the manager row"+edit+id);
		ps=conn.prepareStatement("UPDATE `smart_scheduler1`.`work_schedule` SET `Employee_ID`=NULL WHERE `Schedule_ID`='"+edit+"'");
		ps.executeUpdate();
		ps.closeOnCompletion();
		ps1= conn.prepareStatement("UPDATE schedule_switch SET status='D' where Schedule_ID='"+edit+"'");	
		ps1.executeUpdate();
		ps1.closeOnCompletion();
			}
		}
		
		
	}

	public void Employer_switch(String edit, String switchid,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		ps=conn.prepareStatement("INSERT INTO schedule_switch (DATE, Requesting_Employee_ID, Schedule_ID,Switch_Employee_ID) values(NOW(),'"+uid2+"','"+edit+"',(SELECT Employee_ID from employee_info where FirstName='"+switchid+"'));");
		System.out.println(uid2);
		ps.executeUpdate();
		ps.closeOnCompletion();
	}

	public Result EmployerWorkSwitch(int i,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+uid2);
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		int edit = 0;
		Result result = null;
		if(i==1)
		{
			ps=conn.prepareStatement("SELECT schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,employee_info.FirstName,DATE_FORMAT(time_slot.date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time "
					+ "FROM work_schedule "
					+ "join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID "
					+ "join manager_info on manager_info.Manager_ID=time_slot.Manager_ID "
					+ "join schedule_switch on work_schedule.Schedule_ID=schedule_switch.Schedule_ID "
					+ "join employee_info on schedule_switch.Requesting_Employee_ID=employee_info.Employee_ID"
					+ " where Switch_Employee_ID='"+uid2+"' AND time_slot.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND status='Y' order by date asc, start_time asc;");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		if(i==2)
		{
			ps=conn.prepareStatement("SELECT DISTINCT schedule_switch.Schedule_ID,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,employee_info.FirstName,DATE_FORMAT(time_slot.date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time "
					+ "FROM work_schedule "
					+ "join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID "
					+ "join manager_info on manager_info.Manager_ID=time_slot.Manager_ID "
					+ "join schedule_switch on work_schedule.Schedule_ID=schedule_switch.Schedule_ID "
					+ "join employee_info on schedule_switch.Requesting_Employee_ID=employee_info.Employee_ID"
					+ " where Switch_Employee_ID='"+uid2+"' AND time_slot.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND status IS NULL order by date asc, start_time asc;");

			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		
		return result;
	}

	public void EmployerSwitchAdd(String edit,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		CallableStatement cs;
		ResultSet rs;
		cs=conn.prepareCall("CALL InsertSwitch('"+uid2+"','"+edit+"')");
		rs=cs.executeQuery();
		System.out.println("calling statement");
		rs.close();
		System.out.println(uid2);
	}

	public Result EmployeeSwitchingStatus(int i,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+uid2);
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		int edit = 0;
		Result result = null;
		if(i==1)
		{
			ps=conn.prepareStatement("SELECT status,switch_ID,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,employee_info.FirstName,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y')AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time "
					+ "FROM work_schedule "
					+ "join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID "
					+ "join manager_info on manager_info.Manager_ID=time_slot.Manager_ID "
					+ "join schedule_switch on work_schedule.Schedule_ID=schedule_switch.Schedule_ID "
					+ "join employee_info on schedule_switch.switch_Employee_ID=employee_info.Employee_ID"
					+ " where requesting_Employee_ID='"+uid2+"' AND schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND status is null  order by schedule_switch.date, start_time asc;");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		if(i==2)
		{
			ps=conn.prepareStatement("SELECT status,switch_ID,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,employee_info.FirstName,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y')AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time "
					+ "FROM work_schedule "
					+ "join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID "
					+ "join manager_info on manager_info.Manager_ID=time_slot.Manager_ID "
					+ "join schedule_switch on work_schedule.Schedule_ID=schedule_switch.Schedule_ID "
					+ "join employee_info on schedule_switch.switch_Employee_ID=employee_info.Employee_ID"
					+ " where requesting_Employee_ID='"+uid2+"' AND schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND status='Y'  order by schedule_switch.date, start_time asc;");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		if(i==3)
		{
			ps=conn.prepareStatement("SELECT status,switch_ID,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,employee_info.FirstName,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y')AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y')AS date,Date_format(start_time,'%r') as start_time,Date_format(end_time,'%r')as end_time "
					+ "FROM work_schedule "
					+ "join time_slot on work_schedule.Slot_ID = time_slot.Slot_ID "
					+ "join manager_info on manager_info.Manager_ID=time_slot.Manager_ID "
					+ "join schedule_switch on work_schedule.Schedule_ID=schedule_switch.Schedule_ID "
					+ "join employee_info on schedule_switch.switch_Employee_ID=employee_info.Employee_ID"
					+ " where requesting_Employee_ID='"+uid2+"' AND schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND status='N' or status='D'  order by schedule_switch.date, start_time asc;");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		
		return result;

	}

	public void EmployeeScheduleSwitchEdit(int i, String switch_id,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+switch_id);
		PreparedStatement ps;
		
			ps=conn.prepareStatement("UPDATE smart_scheduler1.schedule_switch SET Status='DE' where switch_id='"+switch_id+"' ");
			System.out.println(uid2);
			ps.executeUpdate();
			ps.closeOnCompletion();
		
	}
	
	public Result ManagerSwitchingStatus(int i,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+uid2);
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		int edit = 0;
		Result result = null;
		if(i==1)
		{
			ps=conn.prepareStatement("SELECT status,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,empl_info1.FirstName as switching_employee,empl_info2.FirstName as requesting_employee,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y') AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y') AS slotdate,start_time,end_time,switch_ID FROM work_schedule"
				   +" JOIN time_slot ON work_schedule.Slot_ID = time_slot.Slot_ID"
				   +" JOIN manager_info ON manager_info.Manager_ID = time_slot.Manager_ID"
				   +" JOIN schedule_switch ON work_schedule.Schedule_ID = schedule_switch.Schedule_ID"
				   +" JOIN employee_info empl_info1 ON schedule_switch.switch_Employee_ID = empl_info1.Employee_ID"
				   +" JOIN employee_info empl_info2 ON schedule_switch.requesting_Employee_ID = empl_info2.Employee_ID"
				   +" where schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND time_slot.Manager_ID=3 AND status is null  order by schedule_switch.date");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		if(i==2)
		{
			ps=conn.prepareStatement("SELECT status,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,empl_info1.FirstName as switching_employee,empl_info2.FirstName as requesting_employee,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y') AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y') AS slotdate,start_time,end_time,switch_ID FROM work_schedule"
					   +" JOIN time_slot ON work_schedule.Slot_ID = time_slot.Slot_ID"
					   +" JOIN manager_info ON manager_info.Manager_ID = time_slot.Manager_ID"
					   +" JOIN schedule_switch ON work_schedule.Schedule_ID = schedule_switch.Schedule_ID"
					   +" JOIN employee_info empl_info1 ON schedule_switch.switch_Employee_ID = empl_info1.Employee_ID"
					   +" JOIN employee_info empl_info2 ON schedule_switch.requesting_Employee_ID = empl_info2.Employee_ID"
					+ " where schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND time_slot.Manager_ID=3 AND status='Y'  order by schedule_switch.date");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		if(i==3)
		{
			ps=conn.prepareStatement("SELECT status,schedule_switch.Switch_Employee_ID,schedule_switch.Requesting_Employee_ID,empl_info1.FirstName as switching_employee,empl_info2.FirstName as requesting_employee,DATE_FORMAT(schedule_switch.date, '%m-%d-%Y') AS switchdate,DATE_FORMAT(time_slot.date, '%m-%d-%Y') AS slotdate,start_time,end_time,switch_ID FROM work_schedule"
					   +" JOIN time_slot ON work_schedule.Slot_ID = time_slot.Slot_ID"
					   +" JOIN manager_info ON manager_info.Manager_ID = time_slot.Manager_ID"
					   +" JOIN schedule_switch ON work_schedule.Schedule_ID = schedule_switch.Schedule_ID"
					   +" JOIN employee_info empl_info1 ON schedule_switch.switch_Employee_ID = empl_info1.Employee_ID"
					   +" JOIN employee_info empl_info2 ON schedule_switch.requesting_Employee_ID = empl_info2.Employee_ID"
					+ " where schedule_switch.date>=DATE_FORMAT(NOW(), '%m-%d-%Y') AND time_slot.Manager_ID=3 AND status='N' or status='D'  order by schedule_switch.date");
			rs=ps.executeQuery();
			result=ResultSupport.toResult(rs);
			ps.closeOnCompletion();
			rs.close();
		}
		
		return result;

	}
	
	public void ManagerScheduleSwitchEdit(int i, String switch_id,String uid2) throws SQLException {
		// TODO Auto-generated method stub
		System.err.println("user id "+switch_id);
		PreparedStatement ps;
		
			ps=conn.prepareStatement("UPDATE smart_scheduler1.schedule_switch SET Status='DM' where switch_id='"+switch_id+"' ");
			System.out.println(uid2);
			ps.executeUpdate();
			ps.closeOnCompletion();
		
	}
	
	public String SuggestiveSearch(String name) throws SQLException
	{
		String buffer="";  
		Statement st=conn.createStatement();
		   ResultSet rs=st.executeQuery("select * from employee_info where FirstName like '"+name+"%'");
		    while(rs.next())
		    {
		    buffer=buffer+"'"+rs.getString("FirstName")+"',";
		    }
		return null;
		
	}

	
}
