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
		id = (int) sess.getAttribute("userid");
		ResultSet rs;
		String query;
		String message = "";
		try {
			databaseConnection dC = new databaseConnection();
			int catid = (int) sess.getAttribute("catid");
				query = "insert into userTasks(userID, taskTitle, taskDescription, catID, deleted) values("+id+",'"+title+"','"+description+"',"+catid+",0);";
				dC.insertQuery(query);
				query = "select * from userTasks where catID="+catid+" and userID="+id+" and deleted=0;";
				rs = dC.selectQuery(query);
				
				String mess1 = "";
				while(rs.next())
				{
					String impval = ""+rs.getInt("indexNumber");
					if(rs.getBoolean("checked"))
						mess1 = mess1 + "<tr> <td> <input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+" checked> <span id='"+impval+"' style='color:lightgray; text-decoration:line-through'>"+rs.getString("taskTitle")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input type='submit' name="+impval+" value ='View' form='View1'> </td> <td> <input type='submit' name="+impval+" value ='Delete' form='Delete1'> </td> </tr> <br>";
					else
						mess1 = mess1 + "<tr> <td> <input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+"> <span id='"+impval+"'>"+rs.getString("taskTitle")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input type='submit' name="+impval+" value ='View' form='View1'> </td> <td> <input type='submit' name="+impval+" value ='Delete' form='Delete1'> </td> </tr> <br>";
				}
				
				String message1 = "<input type='submit' value='Go Back'>";
				request.setAttribute("back", message1);
				request.setAttribute("checkboxes", mess1);
				sess.setAttribute("loggedIn", "loggedin");
				message = "Welcome "+sess.getAttribute("usn");
		        request.setAttribute("message", message); // This will be available as ${message}
		        request.getRequestDispatcher("frontPage.jsp").forward(request, response);		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		/*
		
		
		
		
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
		
		*/
		
		
		
	}

}
