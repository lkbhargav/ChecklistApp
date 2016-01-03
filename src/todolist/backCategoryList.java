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
 * Servlet implementation class backCategoryList
 */
@WebServlet("/backCategoryList")
public class backCategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public backCategoryList() {
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
		ResultSet rs;
		String query;
		String mess1 = "";
		HttpSession sess = request.getSession();
		int id = (int) sess.getAttribute("userid");
			try {
				databaseConnection dC = new databaseConnection();
				query = "select * from checkcat where userID="+id+";";
				rs = dC.selectQuery(query);
				int i = 0;
				while(rs.next())
				{
					i++;
					mess1 += "<span style='font-weight: bold; font-size: large'>"+i+". "+rs.getString("catName")+"</span> &nbsp &nbsp &nbsp &nbsp <input type='submit' value='View Checklist' name='"+rs.getInt("catID")+"'> <input type='submit' value='Delete Checklist' name='"+rs.getInt("catID")+"'> <br>";
				}
				
				request.setAttribute("category", mess1);
				request.getRequestDispatcher("taskCategoryList.jsp").forward(request, response);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
						
	}

}
