package todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class deleteValue
 */
@WebServlet("/deleteValue")
public class deleteValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteValue() {
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
		try {
			databaseConnection dC = new databaseConnection();
			String query;
			String n = null;
			int val=0;
			Boolean status = true;
			//PrintWriter pW = response.getWriter();
			HttpSession sess = request.getSession();
			int id = (int) sess.getAttribute("userid");
			query = "select indexNumber from usertasks where userID="+id+";";
			ResultSet rs = dC.selectQuery(query);
			while(rs.next() && status)
			{
				n = request.getParameter(""+rs.getInt(1));
				//pW.println(rs.getInt(1)+" "+n);
				if(n != null)
				{
					val = rs.getInt(1);
					status = false;
				}
			}
			query = "update usertasks set deleted=1 where userID="+id+" and indexNumber="+val+";";
			dC.updateQuery(query);
			int catid = (int) sess.getAttribute("catid");
			query = "select * from usertasks where catID="+catid+" and userID="+id+" and deleted=0;";
			rs = dC.selectQuery(query);
			
			String mess1 = "";
			while(rs.next())
			{
				String impval = ""+rs.getInt("indexNumber");
				if(rs.getBoolean("checked"))
					mess1 = mess1 + "<tr> <td> <input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+" checked> <span id='"+impval+"' style='color:lightgray; text-decoration:line-through'>"+rs.getString("taskTitle")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input class='btn btn-info' type='submit' name="+impval+" value ='View' form='View1'> </td> <td> <input class='btn btn-danger' type='submit' name="+impval+" value ='Delete' form='Delete1'> </td> </tr>";
				else
					mess1 = mess1 + "<tr> <td> <input type='checkbox' onClick='check123("+impval+")' id="+impval+2+" name="+impval+"> <span id='"+impval+"'>"+rs.getString("taskTitle")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input class='btn btn-info' type='submit' name="+impval+" value ='View' form='View1'> </td> <td> <input class='btn btn-danger' type='submit' name="+impval+" value ='Delete' form='Delete1'> </td> </tr>";
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
