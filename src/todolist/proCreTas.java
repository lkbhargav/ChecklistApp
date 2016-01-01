package todolist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class proCreTas
 */
@WebServlet("/proCreTas")
public class proCreTas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proCreTas() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		int id;
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		HttpSession sess = request.getSession();
		String username = (String) sess.getAttribute("usn");
		ResultSet rs;
		String query;
		String message = "";
		try {
			databaseConnection dC = new databaseConnection();
			query = "select id from registration where usn='"+username+"';";
			rs = dC.selectQuery(query);
			if(rs.next())
			{
				id = rs.getInt("id");
				query = "insert into userTasks(userID, taskTitle, taskDescription) values("+rs.getInt("id")+",'"+title+"','"+description+"');";
				dC.insertQuery(query);
				query = "select * from userTasks where userID='"+id+"';";
				rs = dC.selectQuery(query);
				
				while(rs.next())
				{
					String impval = ""+rs.getInt("indexNumber");
					if(rs.getBoolean("checked"))
						message = message + "<input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+" checked> <span id='"+impval+"' style='color:lightgray; text-decoration:line-through'>"+rs.getString("taskTitle")+":"+"</span> <span>"+rs.getString("taskDescription")+"</span> &nbsp &nbsp &nbsp &nbsp <input type='submit' value ='View' form='View1'> <input type='submit' value ='Delete' form='Delete1'> <br>";
					else
						message = message + "<input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+"> <span id='"+impval+"'>"+rs.getString("taskTitle")+":"+"</span> <span>"+rs.getString("taskDescription")+"</span> &nbsp &nbsp &nbsp &nbsp <input type='submit' value ='View' form='View1'> <input type='submit' value ='Delete' form='Delete1'> <br>";
				}
				request.setAttribute("checkboxes", message); // This will be available as ${message}
				message = "Welcome "+username;
				request.setAttribute("message", message);
			    request.getRequestDispatcher("frontPage.jsp").forward(request, response);				
			}
			else
			{
				
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
