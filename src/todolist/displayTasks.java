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

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Servlet implementation class displayTasks
 */
@WebServlet("/displayTasks")
public class displayTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //final static Logger logger12 = Logger.getLogger(displayTasks.class);
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
		
		//PropertyConfigurator.configure("log4j.properties");
		String query;
		HttpSession sess = request.getSession();
		int id = (int) sess.getAttribute("userid");
		int cid = 0;
		databaseConnection dC;
		try {
			dC = new databaseConnection();
			query = "select catid from checkcat where userid="+id+";";
			ResultSet rs = dC.selectQuery(query);
			String catID = null;
			while(rs.next() && catID == null)
			{
				String idd = rs.getInt("catID")+"";
				catID = request.getParameter(idd);
				//logger12.debug("Category ID is: "+catID);
				System.out.println(catID);
				if(!(catID == null))
				{
					cid = rs.getInt("catID");
				}
				else
				{
					catID=null;
				}
			}
			
			if(cid!=0)
			{
				query = "select * from usertasks where catid="+cid+" and userid="+id+" and deleted="+0+";";
				rs = dC.selectQuery(query);
				sess.setAttribute("catid", cid);
			}
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
			String message = "Welcome "+sess.getAttribute("usn");
	        request.setAttribute("message", message); // This will be available as ${message}
	        request.getRequestDispatcher("frontPage.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
