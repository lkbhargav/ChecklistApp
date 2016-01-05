<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
td {
    padding-top: 15px;
    padding-right: 10px;
    padding-bottom: 10px;
}

th{
	text-align: left;
}
.links{
	padding-bottom: 15px;
}
</style>
</head>
<body>
<!--<h1> Sai Baba </h1>  -->
<header ><center><embed src="fB.swf" height="135" type="application/x-shockwave-flash" width="550"></embed></center></header>
<%
HttpSession sess = request.getSession();
//sess.setAttribute("loggedIn", "");
String uid =  (String) sess.getAttribute("loggedIn");
if(!uid.equals("loggedin"))
{
%>
<form method="post" name ="login" action="loginIntoList">
<table>
<tr>
<th> Username: </th>
<td> <input type="text" name = "username"> </td>
</tr>
<tr>
<th> Password: </th>
<td> <input type="password" name = "passwrd"> </td>
</tr>
<!-- Username:         <input type="text" name = "username"><br><br>
Password:         <input type="password" name = "passwrd"> <br><br> -->
<tr>  </tr>
</table>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input type="submit" value="Submit">
</form>
<%
}
else
{
%>
<a href='saveChecklist'>Tasks</a>
<form method='post' action='logout' name='logof'>
<input type='submit' value='logout'>
</form>
<%
}
%>
<br>
<table class="links">
<tr>
<th> <a href ="Register.html"> Sign up for new account </a> </th>
</tr>
<tr>
<th> <a href ="emailForAct.html"> Activation Link</a> </th>
</tr>
<tr>
<th> <a href ="resetPasswordMethods.html">Forgot Password</a> </th>
</tr>
</table>

</body>
</html>