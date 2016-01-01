<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Activation</title>
</head>
<body>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="gmaillibrary.GmailLibrary" %>
<%@ page import="java.util.Random" %>

<% 
Random rm = new Random();
int random = rm.nextInt(1000)+9999;
String body = "Verification Code is: "+random;

// establishing mysql connection 
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");

String email = request.getParameter("mailID");

//creating session objects
HttpSession sess = request.getSession();
sess.setAttribute("email", email);

Statement st = conn.createStatement();

ResultSet rs = st.executeQuery("select activation from registration where eid = '"+email+"';");

if(!rs.next())
{
	out.println("Account information not found, try registering it again");
	out.println("<a href='Register.html'> Register new account </a>");
}
else
{
	if(rs.getBoolean("activation"))
	{
		out.println("Account seems to be activated, try getting into the account.");
		out.println("<a href='index.html'> Login into account </a>");
	}
	else
	{
		new GmailLibrary("mailtosecureyou","mailstodeliver",email,"Verification Email",body);
		out.println("<form method='post' action='regVeri' name='verification'>Verification Code: </br> <input type='password' id='code' name='code'> <input type='submit'> </form>");
		st.executeUpdate("update registration set scode="+random+" where eid='"+email+"' ;");
	}
}
%>
</body>
</html>