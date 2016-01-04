<%@page import="todolist.databaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password Page</title>
</head>
<body>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="gmaillibrary.GmailLibrary" %>
<%@ page import="java.util.Random" %>

<% 
String email = request.getParameter("mailID");
//Class.forName("com.mysql.jdbc.Driver");
ResultSet rs;
String query;
databaseConnection dC = new databaseConnection();
//java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");

//Statement st = conn.createStatement();
query = "select eid from registration where eid = '"+email+"';";
//rs = st.executeQuery("select eid from registration where eid = '"+email+"';");
rs = dC.selectQuery(query);

if(!rs.next())
{
	out.println("An account registered with "+email+" was not found");
	out.println("<br><br> <a href='index.html'> Home </a>");
}
else
{
	query = "select securityQues, SQAnswer from registration where eid ='"+email+"';";
	//rs = st.executeQuery("select securityQues, SQAnswer from registration where eid ='"+email+"';");
	rs = dC.selectQuery(query);
	out.println("Answer the following question to reset password for your account<br>");
	if(rs.next())
	{
	String sq = (String) rs.getString("securityQues");
	out.println(sq);
	HttpSession sess = request.getSession();
	sess.setAttribute("sans", rs.getString(2));
	sess.setAttribute("eID", email);
	out.println("<form action='newPassword' method='post'><input type='text' value='answer' name='sqans'> <br> <input type='submit' value='Submit'></form>");
	}
}
%>
</body>
</html>