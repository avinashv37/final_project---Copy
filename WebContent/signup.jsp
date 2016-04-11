
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" 
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Smart Scheduler</title>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	
	<script type="text/javascript">
	function valid2()
	{
	var a=document.frm.name.value;
	if(a=="")
	{
	alert ("enter a user name");
	document.frm.name.focus();
	return false;
	}
	var b=document.frm.password.value;
	if(b=="")
	{
	alert ("enter a password");
	document.frm.password.focus();
	return false;
	}
	var c=document.frm.number.value;
	if(c.NAN)
		{
		alert ("enter a phone number");
		document.frm.number.focus();
		return false;
		}
	
	}
	
	</script>



</head>

<body>

<div class="container">

        <h3> Register</h3>
        
        <form class="form-horizontal"  ng-app="" role="form" name="frm"  
        action ="SignupController" method="post" onSubmit="return valid2()">
		
			<div class="form-group">
				<label class=col-xs-2>Employer ID</label>
				<div class=col-xs-10>
		       		<input type="text"  class="form-control" name="employerID" placeholder="enter your employer ID" >
        		</div>
        	</div>
        	<div class="form-group">
		        <label class=col-xs-2>User Name</label>
		        <div class=col-xs-10>
		        	<input type="text"  class=form-control name="name" placeholder="enter a username" >
		        </div>
	        </div>
			<div class="form-group">
		        <label class=col-xs-2>Password</label>
		        <div class=col-xs-10>
		        	<input type="password" class=form-control name="password" placeholder="enter a password" >
		        </div>
	        </div>
        	<div class="form-group">
		        <label class=col-xs-2>Password</label>
		        <div class=col-xs-10>
		        	<input type="password" class=form-control name="repassword" placeholder="re-type password" >
		        </div>
	        </div>
			<div class="form-group">
					<label class=col-xs-2 for="utype">User Type:</label>
					<div class=col-xs-10>
			        	<select class="form-control" name="utype">
			        	<option value="select">--Select--</option>
			        	<option value="Manager">Manager</option>
			        	<option value="Employer">Employer</option></select>
			        </div>
				</div>
			<div class="form-group">
		        <label class=col-xs-2>Name</label>
			        <div class=col-xs-5>
			        	<input type="text" class=form-control name="firstname" placeholder="First name" >
			        </div>
			        <div class=col-xs-5>
			        	<input type="text" class=form-control name="lastname" placeholder="Last name" >
			        </div>
	        </div>
	        <div class="form-group">
		        <label class=col-xs-2>E-mail</label>
		        <div class=col-xs-10>
		        	<input type="email" class=form-control name="email" placeholder="@email.com" ng-model="text" >
		        	<span ng-show="myForm.myAddress.$error.email">Not a valid e-mail address</span>
		        </div>
	        </div>
        	<div class="form-group">
		        <label class=col-xs-2>Date of Birth</label>
		        <div class=col-xs-10>
		        	<input type="DOB" class=form-control name="DOB" placeholder="mm-dd-year" >
		        </div>
	        </div>
			<div class="form-group">
		        <div class=col-xs-2><label>Gender</label></div>
		        <div class=col-xs-5>
		        	<label class=radio-inline><input type="radio"  value=male name="gender" placeholder="re-type password">Male</label>
		        </div>
		        <div class=col-xs-5>
		        	<label class=radio-inline><input type="radio"  value=female name="gender" placeholder="re-type password">Female </label>
		        </div>
	        </div>
	        <div class="form-group">
		        <label class=col-xs-2>Phone number</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control  name="phone" placeholder="phone" >
		        </div>
	        </div>
	        <div class="form-group">
					<label class=col-xs-2 for="utype">Marital Status</label>
					<div class=col-xs-10>
			        	<select class="form-control" name="marital">
			        	<option value="select"> </option>
			        	<option value="single">Single</option>
			        	<option value="married">Married</option></select>
			        </div>
			</div>
			<div class="form-group">
		        <label class=col-xs-2>Country</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control name="country" placeholder="country" >
		        </div>
	        </div>
			<div class="form-group">
		        <label class=col-xs-2>State</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control name="state" placeholder="state" >
		        </div>
	        </div>
			<div class="form-group">
		        <label class=col-xs-2>City</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control name="city" placeholder="city" >
		        </div>
	        </div>
	         <div class="form-group">
		        <label class=col-xs-2>Occupation</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control name="occupation" placeholder="occupation" >
		        </div>
	        </div>
	        <div class="form-group">
		        <label class=col-xs-2>Department</label>
		        <div class=col-xs-10>
		        	<input type="text" class=form-control name="department" placeholder="department" >
		        </div>
	        </div>
	         <div class="form-group">
		        	<button type="submit" class="btn btn-info btn-md btn-block" name="submit" placeholder="submit" >Sign up</button>
	        </div>

    </div>


</div>


</form>
</body>
</html>
