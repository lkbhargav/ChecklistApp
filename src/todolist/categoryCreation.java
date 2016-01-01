package todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class categoryCreation
 */
@WebServlet(name = "catCre", urlPatterns = { "/catCre" })
public class categoryCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categoryCreation() {
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
			ResultSet rs = null;
			String query;
			PrintWriter pW = response.getWriter();
			String catName = request.getParameter("categoryName");
			HttpSession sess = request.getSession();
			String username = (String) sess.getAttribute("usn");
			query = "select id from registration where usn='"+username+"';";
			rs = dC.selectQuery(query);
			if(rs.next())
			{
				query = "insert into checkCat(userID, catName) values("+rs.getInt(1)+",'"+catName+"');";
				dC.insertQuery(query);
			}
			else
			{
				pW.println("User doesn't exists!!!");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
