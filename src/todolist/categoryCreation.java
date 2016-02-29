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
		ResultSet rs = null;
		String query;
		PrintWriter pW = response.getWriter();;
		try {
			databaseConnection dC = new databaseConnection();
			//pW = response.getWriter();
			String catName = request.getParameter("categoryName");
			HttpSession sess = request.getSession();
			int id = (int) sess.getAttribute("userid");
			//String username = (String) sess.getAttribute("usn");
			//query = "select id from registration where usn='"+username+"';";
			//rs = dC.selectQuery(query);
			//pW.println(query);
			//if(rs.next())
			query = "insert into checkcat(userID, catName, deleted) values("+id+",'"+catName+"',0);"; pW.println(query);
			dC.insertQuery(query);
			String mess1 ="";
			query = "select * from checkcat where userID="+id+" and deleted=0;"; pW.println("5");
			rs = dC.selectQuery(query); pW.println("6");
			pW.println(query);
			int i = 0;
			while(rs.next())
			{
				i++;
				mess1 += "<tr> <td> <span style='font-weight: bold; font-size: large'>"+i+". "+rs.getString("catName")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input type='submit' value='View Checklist' name='"+rs.getInt("catID")+"'> </td> <td> <input type='submit' value='Delete Checklist' name='"+rs.getInt("catID")+"' form='deleteItem'> </td> </tr> <br>";			
			}
			
			pW.println(mess1);
			
			request.setAttribute("category", mess1);
			request.getRequestDispatcher("taskCategoryList.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e)
		{
			pW.println("Error inserting data");
		}
		
	}

}
