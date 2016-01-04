<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<!--<h1> Sai Baba </h1>  -->
<%
HttpSession sess = request.getSession();
//sess.setAttribute("loggedIn", "");
String uid =  (String) sess.getAttribute("loggedIn");
if(!uid.equals("loggedin"))
{
%>
<h1 style="color:blue;" align="center">TO DO LIST</h1>
<form method="post" name ="login" action="loginIntoList">
Username:         <input type="text" name = "username"><br><br>
Password:         <input type="password" name = "passwrd"> <br><br>
<input type="submit" value="Submit">
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
<a href ="Register.html"> Sign up for new account </a> <br> <br>
<a href ="emailForAct.html"> Activation Link</a> <br> <br>
<a href ="resetPasswordMethods.html">Forgot Password</a>

</body>
</html>