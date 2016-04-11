<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "com.smartScheduler.controller.timeslot"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<!-- Clockpicker and minified CSS -->
	<link rel="stylesheet" href="clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
	<!-- Ajax by google cdn -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	
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
<script jquery type="text/javascript">
	function valid2()
	{
	var a=document.frm.name.value;
	if(a=="")
	{
	alert ("enter Time slot name");
	document.frm.slotname.focus();
	return false;
	}
	var b=document.frm.time.value;
	if(b=="")
	{
	alert ("enter Time");
	document.frm.password.focus();
	return false;
	}
	}
	</script>
	
	
</head>
<body>
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
					<li><a href="#Manager_Home">Manager Home</a></li>
					<!--<li><a href="/final_project/managingtimeslot.jsp">Time Slot </a></li>  -->
					<li><a href="/final_project/ManagerWorkSchedule.jsp">Work Scheduler</a></li>
					<li><a id="view" href="/final_project/ManagerScheduleView.jsp">Schedule View</a></li>
					<li><a href="/final_project/ManagerSwitchingStatus.jsp">Switching Status</a></li>
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
	<div class="container-fluid" >
			<form role="form" name="frm" class="col-xs-12" action ="workschedule" method="post" 
			onSubmit="return valid2()">
			
			<div class="form-group">
			        <label for="name" >Date</label>
			        <input type="text" data-provide="datepicker" name="date" class="form-control col-xs-6" placeholder="Time Slot name" >
		     </div>
	
		     <label  for="name" class=col-xs-10>Time</label>
		      <label  for="name" class=col-xs-2>Slot</label>
		  	<div class=" form-group row">
			  	<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time1" name="start_time1" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time1" name="end_time1" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot1" name="slot1" class="form-control" placeholder="0">
				</div>
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time2" name="start_time2" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time2" name="end_time2" class="form-control" placeholder="09:30" >
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot2" name="slot2" class="form-control" placeholder="0">
				</div>
				
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time3" name="start_time3" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time3" name="end_time3" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot3" name="slot3" class="form-control" placeholder="0">
				</div>
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time4" name="start_time4" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time4" name="end_time4" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot4" name="slot4" class="form-control" placeholder="0">
				</div>
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time5" name="start_time5" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time5" name="end_time5" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot5" name="slot5" class="form-control" placeholder="0">
				</div>
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time6" name="start_time6" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time6" name="end_time6" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot6" name="slot6" class="form-control" placeholder="0">
				</div>
				
				<div class="form-group clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time7" name="start_time7" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-5" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time7" name="end_time7" class="form-control" placeholder="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
				<div class="form-group  col-xs-2" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="slot7" name="slot7" class="form-control" placeholder="0">
				</div>
				
			</div>
			<div class="form-group">
					<button type="submit" id="submit" class="btn btn-info"  name="submit">SUBMIT</button>
			</div>
			
		
			</form>
			
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
	
	<input type="hidden" name="smname" id="smname" />
	
</body>
<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	
	<script src="clockpicker-gh-pages/assets/js/bootstrap.min.js"></script>
	<script src="clockpicker-gh-pages/assets/js/highlight.min.js"></script>
	<script src="clockpicker-gh-pages/dist/jquery-clockpicker.min.js"></script>
	<script src="clockpik_add_element.js"></script>
	<script src="datepicker/js/bootstrap-datepicker.js"></script>
	<script src="datepicker/css/bootstrap-datepicker3.css"></script>

	
	
	
</html>