package todolist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
//import javax.sql.*;


/**
 * Servlet implementation class registrationVerification
 */
@WebServlet(name = "regVeri", urlPatterns = { "/regVeri" })
public class registrationVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//doGet(request, response);
		PrintWriter pw = response.getWriter();
		HttpSession sess = request.getSession();
		String email = (String) sess.getAttribute("email");
		int code =  Integer.parseInt(request.getParameter("code"));
		
		ResultSet rs;
		String query;
		
		try {
			databaseConnection dC = new databaseConnection();	
			query = "select scode,activation from registration where eid='"+email+"';";
			rs = dC.selectQuery(query);
			if(rs.next())
			{
				if(rs.getInt("scode") == code && !rs.getBoolean("activation"))
				{
					pw.println("Activation is successfull");
					pw.println("</br><a href='index.html'>Home</a>");
					query = "update registration set activation=TRUE where eid ='"+email+"';";
					dC.updateQuery(query);
				}
				else
				{
					pw.println("Wrong activation code, try activating it again from home page");
					pw.println("</br><a href='index.html'>Home</a>");
					
				}
			}
			else
			{
				pw.println("There seems to be no account registered with this email, try registering a new account");
				pw.println("</br><a href='Register.html'> Register new account </a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sess.removeAttribute("email");
	}

}
