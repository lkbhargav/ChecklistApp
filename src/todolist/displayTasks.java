package todolist;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class displayTasks
 */
@WebServlet("/displayTasks")
public class displayTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayTasks() {
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
		String query;
		HttpSession sess = request.getSession();
		int id = (int) sess.getAttribute("userid");
		int cid = 0;
		databaseConnection dC;
		try {
			dC = new databaseConnection();
			query = "select catID from checkcat where userID="+id+";";
			ResultSet rs = dC.selectQuery(query);
			String catID = "";
			while(rs.next() && catID.equals(""))
			{
				String idd = rs.getInt("catID")+"";
				catID = request.getParameter(idd);
				if(!catID.equals(null))
				{
					cid = rs.getInt("catID");
				}
				else
				{
					catID="";
				}
			}
			
			if(cid!=0)
			{
				query = "select * from userTasks where catID="+cid+" and userID="+id+";";
				rs = dC.selectQuery(query);
			}
			String mess1 = "";
			while(rs.next())
			{
				String impval = ""+rs.getInt("indexNumber");
				if(rs.getBoolean("checked"))
					mess1 = mess1 + "<input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+" checked> <span id='"+impval+"' style='color:lightgray; text-decoration:line-through'>"+rs.getString("taskTitle")+":"+"</span> <span>"+rs.getString("taskDescription")+"</span> &nbsp &nbsp &nbsp &nbsp <input type='submit' value ='View' form='View1'> <input type='submit' value ='Delete' form='Delete1'> <br>";
				else
					mess1 = mess1 + "<input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+"> <span id='"+impval+"'>"+rs.getString("taskTitle")+":"+"</span> <span>"+rs.getString("taskDescription")+"</span> &nbsp &nbsp &nbsp &nbsp <input type='submit' value ='View' form='View1'> <input type='submit' value ='Delete' form='Delete1'> <br>";
			}
			
			String message1 = "<input type='submit' value='Go Back'>";
			request.setAttribute("back", message1);
			request.setAttribute("checkboxes", mess1);
			sess.setAttribute("loggedIn", "loggedin");
			String message = "Welcome "+sess.getAttribute("usn");
	        request.setAttribute("message", message); // This will be available as ${message}
	        request.getRequestDispatcher("frontPage.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
