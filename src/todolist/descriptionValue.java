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
 * Servlet implementation class descriptionValue
 */
@WebServlet("/descriptionValue")
public class descriptionValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public descriptionValue() {
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
		databaseConnection dC;
		try {
			dC = new databaseConnection();
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
			query = "select taskTitle, taskDescription, catID from usertasks where indexNumber="+val+";";
			rs = dC.selectQuery(query);
			
			rs.next();
			String title = rs.getString(1);
			String desc = rs.getString(2);
			val = (int) rs.getInt(3);
			query = "select catName from checkcat where catID="+val+";";
			rs = dC.selectQuery(query);
			rs.next();
			
			
			sess.setAttribute("descr", desc);
			sess.setAttribute("title", title);
			String message = "Welcome "+sess.getAttribute("usn");
			request.setAttribute("bhargav", message);// This will be available as ${message}
			request.setAttribute("catName", rs.getString(1));
			message = "<h3>"+title+"</h3> <h4>"+desc+"</h4>";
			request.setAttribute("taskDesc", message);
		    request.getRequestDispatcher("taskDescription.jsp").forward(request, response);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
