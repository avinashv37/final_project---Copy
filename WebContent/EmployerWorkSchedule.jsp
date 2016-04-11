<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="com.smartScheduler.connection.Database" %>
<%@ page import="com.smartScheduler.controller.admin" %>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"
import = "javax.servlet.jsp.jstl.sql.ResultSupport" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html manifest="demo_html.appcache">
<head>
		<!-- Clockpicker and minified CSS -->
	<link rel="stylesheet" href="clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<!-- Ajax and Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity
	="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>



<title>Work Schedule</title>
</head>
	<script>
		function valid_display()
		{
		var a=document.frm.date.value;
		if(a=="")
		{
		alert ("Enter a date to display the schedule or add schedule \n \n Go to Employee home to check entire schedule ");
		document.frm.date.focus();
		return false;
		}
		}
		
		function valid_add()
		{
		var a=document.frm.date.value;
		if(a=="")
		{
		alert ("Enter a date to display the schedule or add schedule \n \n Go to Employee home to check entire schedule ");
		document.frm.date.focus();
		return false;
		}
		var b=document.frm.start_time.value;
		if(b=="")
		{
		alert ("Enter start time to your schedule ");
		document.frm.start_time.focus();
		return false;
		}
		var c=document.frm.end_time.value;
		if(c=="")
		{
		alert ("Enter end time to your schedule ");
		document.frm.end_time.focus();
		return false;
		}
		}
	// the below script does not work as of now still in testing.
		$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
		
	</script>
<body data-spy="scroll" data-target=".navbar-collapse">
	
		 <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	     <div class="container-fluid" id="navfluid">
	       <div class="navbar-header">
		       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigationbar" >
			       <span class="sr-only">Toggle navigation</span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
		      </button>
	     
	    	</div>
			<div class="collapse navbar-collapse" id="navigationbar">
				<ul class="nav navbar-nav">
					<li><a href="/final_project/employer.jsp">Employer Home</a></li>
					<li><a href="/final_project/SwitchingStatus.jsp">Switching Status</a></li>
					<li><a href="/final_project/Employee_switch.jsp">Employee Switch</a></li>
					<li><a href="/final_project/EmployerWorkSchedule.jsp">Schedule Add</a></li>
					
				</ul>
				
				<ul class="nav navbar-nav navbar-right"> 
					<li>
		               <form name="submitForm" method="POST" action="Signout">
		    			<input type="hidden" name="param1" value="param1Value"><a href="login.jsp" class="navbar-brand"><span class="glyphicon glyphicon-log-out">
		    			</span> Log out</a></form>
            		</li>
				</ul>
			</div>
		</div>
	</nav>

	
	<div  style="height:70px;"></div>
	<form role="form" name="frm" class="col-xs-12" action ="Employer_workschedule_edit" method="post" >
		
		<div class="form-group row col-xs-12">	
			<div class="form-group row col-xs-4">	
				<label for="name" >Date</label>
			</div>
			<div class="form-group row col-xs-4">
				<label for="name" >Start time</label>
			</div>
			<div class="form-group row col-xs-4">
				<label for="name" >End time</label>
			</div>
		</div>
		<div class="form-group row col-xs-12">	
			<div class="form-group row col-xs-4">
			        <input type="text" name="date" data-provide="datepicker" class="form-control" placeholder="date" >
			</div>
			<div class="form-group  clockpicker col-xs-4" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time" name="start_time" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
			<div class="form-group  clockpicker col-xs-4" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time" name="end_time" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
		</div>
		<div class="form-group row col-xs-12">
			<button type="submit" class="btn btn-info" value="open_schedule" name="click"	onclick="return valid_display()">OPEN SCHEDULE</button>
			<button type="submit" class="btn btn-info" value="display_schedule" name="click"	onclick="return valid_display()">MY SCHEDULE</button>
			<button type="submit" class="btn btn-info" value="add_me" name="click" onclick="return valid_add()">ADD ME</button>
		</div>
	</form>
		<div  style="height:70px;"></div>
		
		<div class= "container table-responsive" id="Admin_home">
		<table  class="table table-striped responsive">
            <!-- column headers -->
            <thead>
				<tr>
					<th>Date</th>
					<th>Manager ID</th>
					<th>Start time</th>
					<th>End time<th>
				</tr>
			</thead>
			
            <!-- column data  -->
            <tbody>
            <% Result result = (Result)request.getAttribute("result");
          %>
            <c:forEach var="row" items="${result.rows}">
                <tr>
                        <td><c:out value="${row.Date}"/></td>
                        <td><c:out value="${row.FirstName}"/></td>
                        <td><c:out value="${row.Start_time}"/></td>
                        <td><c:out value="${row.end_time}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        
	</div>
	</body>
	<script type="text/javascript">
			jQuery(function($) {
				$('.clockpicker').clockpicker({
				    placement: 'bottom',
				    align: 'right',
				    donetext: 'Done'
				});
		// The line below "closes" the document ready function
		});

			

			</script>
	<script src="clockpicker-gh-pages/assets/js/bootstrap.min.js"></script>
	<script src="clockpicker-gh-pages/assets/js/highlight.min.js"></script>
	<script src="clockpicker-gh-pages/dist/jquery-clockpicker.min.js"></script>
	<script src="clockpik_add_element.js"></script>
	<script src="datepicker/js/bootstrap-datepicker.js"></script>
	<script src="datepicker/css/bootstrap-datepicker3.css"></script>

</html>