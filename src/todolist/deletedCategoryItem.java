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
 * Servlet implementation class deletedCategoryItem
 */
@WebServlet("/deletedCategoryItem")
public class deletedCategoryItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletedCategoryItem() {
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
			ResultSet rs;
			String n = null;
			int val=0;
			Boolean status = true;
			HttpSession sess = request.getSession();
			int id = (int) sess.getAttribute("userid");
			query = "select catID from checkcat where userID="+id+";";
			rs = dC.selectQuery(query);
			while(rs.next() && status)
			{
				n = request.getParameter(""+rs.getInt(1));
				if(n != null)
				{
					val = rs.getInt(1);
					status = false;
				}
			}
			query = "update checkcat set deleted=1 where catID="+val+";";
			dC.updateQuery(query);
			query = "select * from checkcat where userID="+id+" and deleted=0;";
			rs = dC.selectQuery(query);
			String mess1 = "";
			int i = 0;
			while(rs.next())
			{
				i++;
				mess1 += "<tr> <td> <span style='font-weight: bold; font-size: large'>"+i+". "+rs.getString("catName")+"</span> </td> &nbsp &nbsp &nbsp &nbsp <td> <input type='submit' value='View Checklist' name='"+rs.getInt("catID")+"'> </td> <td> <input type='submit' value='Delete Checklist' name='"+rs.getInt("catID")+"' form='deleteItem'> </td> </tr> <br>";			}
			
			request.setAttribute("category", mess1);
			request.getRequestDispatcher("taskCategoryList.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
