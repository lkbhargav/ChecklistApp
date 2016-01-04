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
 * Servlet implementation class saveChecklist
 */
@WebServlet("/saveChecklist")
public class saveChecklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String query;
	ResultSet rs;
	int i = 0;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saveChecklist() {
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
		
		HttpSession sess = request.getSession();
		String username = (String) sess.getAttribute("usn");
		int id = (int) sess.getAttribute("userid");
		int catid = (int) sess.getAttribute("catid");
		// establish database connection
		try {
			databaseConnection dC = new databaseConnection();
			databaseConnection dC2 = new databaseConnection();
			query = "select indexNumber from userTasks, registration where userTasks.userID=registration.id and registration.usn='"+username+"' and catID="+catid+";";
			rs = dC.selectQuery(query);
			String val;
			String message = "";
			i = 0;
			while(rs.next())
			{
				val = request.getParameter(""+rs.getInt(1)+"");
				if(val != null)
				{
					query = "update userTasks set checked=TRUE where indexNumber="+rs.getInt(1)+";";
					dC2.updateQuery(query);
				}
				else
				{
					query = "update userTasks set checked=FALSE where indexNumber="+rs.getInt(1)+";";
					dC2.updateQuery(query);
				}
			}
			query = "select * from userTasks where catID="+catid+" and userID="+id+" and deleted="+0+";";
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
