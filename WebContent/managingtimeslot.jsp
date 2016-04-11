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
	

<title>Time Slot</title>
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
					<li><a href="/final_project/manager.jsp">Manager Home</a></li>
					<li><a href="#">Time Slot </a></li>
					<li><a href="/final_project/ManagerWorkSchedule.jsp">Work Scheduler</a></li>
					<li><a href="#Manager_Schedule_view">Schedule View</a></li>
					<li><a href="#Manager_Sign_Out">Sign Out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div  style="height:70px;"></div>
	<div class="container-fluid" >
			<form role="form" name="frm" class="col-xs-12" action ="timeslot" method="post" 
			onSubmit="return valid2()">
			
			<div class="form-group">
			        <label for="name" >Slot Name</label>
			        <input type="text" name="slotname" class="form-control col-xs-6" placeholder="Time Slot name" >
		     </div>
		     	<div  style="height:30px;"></div>
		     <div class="form-group row">
		     <div class="col-xs-6">
			        <label for="name" >Time Slot</label>
			        <input type="text" name="timeslot" class="form-control col-xs-6" placeholder="Time Slot name" >
			 </div> 
			 <div class="col-xs-6">
			        <label for="name">Slot Available</label>
			        <input type="text" name="slotavailable" class="form-control" placeholder="Slot available" >
			 </div>
		     </div>
		     
		     <label for="name" >Time</label>
		  	<div class=" form-group row">
			  	<div class="form-group clockpicker col-xs-6" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="start_time" name="start_time" class="form-control" value="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time "></span>
				    </span>
				</div>
				
				<div class="form-group  clockpicker col-xs-6" data-placement="left" data-align="top" data-autoclose="true" >
				    <input type="text" id="end_time1" name="end_time" class="form-control" value="09:30">
				    <span class="form-group-addon">
				        <span class="glyphicon glyphicon-time"></span>
				    </span>
				</div>
			</div>
			
			<div class="input_fields_wrap form-group">
			    <button class="add_field_button btn btn-info" onclick="loadDoc()">Add More Fields</button>
			</div>
			
			  <input type="hidden" name="someField" id="someFieldId" />
			<div class="form-group">
					<button type="submit" id="submit" class="btn btn-info"  name="submit">SUBMIT</button>
			</div>
			
		</div>
			</form>
			
			<script type="text/javascript">
			jQuery(function($) {
				$('.clockpicker').clockpicker({
				    placement: 'top',
				    align: 'left',
				    donetext: 'Done'
				});
		// The line below "closes" the document ready function
		});
			</script>
			<script>
			jQuery(function($) {
			    // The original "clockpicker" code is moved to the bottom in a function
			    // No need to close and re-open script tags, so I've removed those
			    // No need for a new "document ready" function, so I've removed that
			    var max_fields      = 15; //maximum input boxes allowed
			    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
			    var add_button      = $(".add_field_button"); //Add button ID

			    var x = 0; //initlal text box count
			    $(add_button).click(function(e) { //on add input button click
			        e.preventDefault();
			        if(x < max_fields) { //max input box allowed
			            x++; //text box increment
			            $(wrapper).append('<div> <div  style="height:30px;"></div>'+
			                '<div class="form-group row">'+
			                    '<div class="col-xs-6">'+
			                      '<label for="name" >Time Slot</label>'+
			                      '<input type="text" name="timeslot" class="form-control col-xs-6" placeholder="Time Slot name" ></div>'+
			                     '<div class="col-xs-6">'+
			                        '<label for="name">Slot Available</label>'+
			                        '<input type="text" name="slotavailable" class="form-control" placeholder="Slot available" >'+
			                     '</div>'+
			                '</div>'+
			                '<label for="name" >Time</label>'+
			                '<div class=" form-group row">'+
			                    '<div class="form-group clockpicker col-xs-6" data-placement="left" data-align="top" data-autoclose="true" >'+
			                        '<input type="text" id="start_time" name="start_time" class="form-control" value="09:30">'+
			                         '<span class="form-group-addon">'+
			                            '<span class="glyphicon glyphicon-time "></span>'+
			                         '</span>'+
			                    '</div>'+
			                    '<div class="form-group  clockpicker col-xs-6" data-placement="left" data-align="top" data-autoclose="true" >'+
			                        '<input type="text" id="end_time" name="end_time" class="form-control" value="09:30">'+
			                        '<span class="form-group-addon">'+
			                            '<span class="glyphicon glyphicon-time"></span>'+
			                        '</span>'+
			                    '</div>'+
			                '</div>'+
			            '<a href="#" class="remove_field">Remove</a></div>'); //add input box
			            // NEW: Bind to the newly added clockpicker element
			            bindClockPicker();
			        }
			        document.getElementById("someFieldId").value = x;
			    });

			    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
			        e.preventDefault(); $(this).parent('div').remove(); 
			   
			        document.getElementById("someFieldId").value = x-1;
			    });

			    // This function "binds" the clockpicker
			    function bindClockPicker() {
			        $('.clockpicker').clockpicker({
			            placement: 'top',
			            align: 'left',
			            donetext: 'Done'
			        });
			    }
			    document.form[0].submit();
			    // Last but not least, call this to bind to any existing clockpicker elements
			    bindClockPicker();
			});

			
			
			
			</script>
			<script>
			function loadDoc() {
				  var xhttp = new XMLHttpRequest();
				  xhttp.onreadystatechange = function() {
				    if (xhttp.readyState == 4 && xhttp.status == 200) {
				      document.getElementById("demo").innerHTML = xhttp.responseText;
				    }
				  };
				  xhttp.open("POST", "demo_post2.asp", true);
				  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				  xhttp.send("fname=Henry&lname=Ford");
				}
			</script>
	
	<input type="hidden" name="smname" id="smname" />
	
</body>
<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	
	<script src="clockpicker-gh-pages/assets/js/bootstrap.min.js"></script>
	<script src="clockpicker-gh-pages/assets/js/highlight.min.js"></script>
	<script src="clockpicker-gh-pages/dist/jquery-clockpicker.min.js"></script>
	<script src="clockpik_add_element.js"></script>

	
	
	
</html>