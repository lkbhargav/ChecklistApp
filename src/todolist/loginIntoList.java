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

/**
 * Servlet implementation class loginIntoList
 */
@WebServlet("/loginIntoList")
public class loginIntoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginIntoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("passwrd");
		PrintWriter pw = response.getWriter();
		HttpSession sess = request.getSession();
		sess.setAttribute("usn", username);
		ResultSet rs;
		String query;
		
		try {
			databaseConnection dC = new databaseConnection();
			query = "select activation from registration where usn='"+username+"';";
			rs = dC.selectQuery(query);
			String mess1 = "";
			if(rs.next())
			{
				if(rs.getBoolean("activation") == true)
				{
					query = "select pwd, logC, id from registration where usn='"+username+"';";
					rs = dC.selectQuery(query);
					if(rs.next())
					{
						sess.setAttribute("userid", rs.getInt("id"));
						String pwd = rs.getString(1);
						int logonCount = rs.getInt(2);
						int id = rs.getInt(3);
						if(password.equals(pwd))
						{
							logonCount += 1;
							query = "update registration set logC="+logonCount+" where usn ='"+username+"';";
							dC.updateQuery(query);
							
							query = "select * from checkcat where userID="+id+" and deleted=0;";
							rs = dC.selectQuery(query);
							int i = 0;
							while(rs.next())
							{
								i++;
								mess1 += "<tr> <td> <span style='font-weight: bold; font-size: large'>"+i+". "+rs.getString("catName")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input type='submit' value='View Checklist' name='"+rs.getInt("catID")+"'> </td> <td> <input type='submit' value='Delete Checklist' name='"+rs.getInt("catID")+"' form='deleteItem'> </td> </tr>";
							}
							
							request.setAttribute("category", mess1);
							request.getRequestDispatcher("taskCategoryList.jsp").forward(request, response);
						}
						else
						{
							pw.println("Wrong Password entered");
							pw.println("</br><a href='index.jsp'>Home</a>");
						}
					}
				}
				else
				{
					pw.println("Your account seeems to be not activated, try activating your account before logging in");
					pw.println("</br><a href='index.jsp'>Home</a>");
					
				}
			}
			else
			{
				pw.println("Invalid username or password");
				pw.println("</br><a href='Register.html'> Register new account </a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
