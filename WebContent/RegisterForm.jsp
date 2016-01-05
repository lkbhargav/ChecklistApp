<%@page import="todolist.databaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
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
	String body = "Activation Code: "+random;
	// establishing mysql connection 
	//Class.forName("com.mysql.jdbc.Driver");
	databaseConnection dC = new databaseConnection();
	//java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");
	
	// getting user entered values from the form for furthur processing in java
	String usn = request.getParameter("username");
	String email = request.getParameter("emailid");
	String password = request.getParameter("passwrd");
	String fname = request.getParameter("firstname");
	String lname = request.getParameter("lastname");
	String pnum = request.getParameter("phonenumber");
	String sq = request.getParameter("sq");
	String sqa = request.getParameter("security");
	
	
	//creating session objects
	HttpSession sess = request.getSession();
	sess.setAttribute("email", email);
	
	
	// preparing database query's
	//Statement st = conn.createStatement();
	String query = "select * from registration where usn='"+usn+"';";
	ResultSet rs = dC.selectQuery(query); 
			//st.executeQuery("select * from registration where usn='"+usn+"';");
	
	if(!rs.next())
	{
		query = "select * from registration where eid='"+email+"';";
		rs = dC.selectQuery(query);
		//rs = st.executeQuery("select * from registration where eid='"+email+"';");
		if(!rs.next())
		{
			out.println("Registration is successfull, check your email to activate your account");
			new GmailLibrary("mailtosecureyou","mailstodeliver",email,"Verification Email",body);
			int logCount = 0;
			out.println("<form method='post' action='regVeri' name='verification'>Verification Code: </br> <input type='password' id='code' name='code'> <input type='submit'> </form>");
			query = "insert into registration(fname, lastname, eid, usn, pwd, phn, scode, activation, securityQues, SQAnswer, logC) values('"+fname+"','"+lname+"','"+email+"','"+usn+"','"+password+"',"+pnum+","+random+","+false+",'"+sq+"','"+sqa+"',"+logCount+");";
			dC.insertQuery(query);
			//st.executeUpdate("insert into registration(fname, lastname, eid, usn, pwd, phn, scode, activation, securityQues, SQAnswer, logC) values('"+fname+"','"+lname+"','"+email+"','"+usn+"','"+password+"',"+pnum+","+random+","+false+",'"+sq+"','"+sqa+"',"+logCount+");");
			out.println("</br><a href='index.html'>Login</a>");
		}
		else
		{
			out.println("Email exists!!!, try using another email");
			out.println("</br><a href='Register.html'>Click here to go back</a>");
		}
	}
	else
	{
		out.println("Username exists!!!, try using another username");
		out.println("</br><a href='Register.html'>Click here to go back</a>");
	}

	
%>
</body>
</html>