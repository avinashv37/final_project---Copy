<%-- 
    Document   : manager
    Created on : Feb 2, 2016, 3:34:27 AM
    Author     : avinash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	
	<!-- Ajax and Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity
	="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

        <title>Manager</title>
    </head>
    <body>
 
        <%String fname=(String)session.getAttribute("fname");
        System.out.print(fname);
        out.println("<p>fname</p>");
        %><script>
			$(".nav a").on("click", function(){
			   $(".nav").find(".active").removeClass("active");
			   $(this).parent().addClass("active");
			});
			
			</script>
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
					<li><a href="/final_project/manager.jsp">Manager Home</a></li>
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
	
    </body>
</html>
