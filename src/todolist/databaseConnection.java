package todolist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class databaseConnection
 */
@WebServlet("/databaseConnection")
public class databaseConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
	
	//Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pS;
    public databaseConnection() throws ClassNotFoundException, SQLException {
        super();
        //Class.forName("com.mysql.jdbc.Driver");
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3316/todo","root","lkbhargav123KING#@!$%");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","password");
		st = conn.createStatement();
        // TODO Auto-generated constructor stub
    }
    
    public void insertQuery(String query) throws SQLException
    {
    	//pS.executeQuery();
    	st.executeUpdate(query);
    }
    
    public ResultSet selectQuery(String query) throws SQLException
    {
    	rs = st.executeQuery(query);
    	return rs;
    }
    
    public void updateQuery(String query) throws SQLException
    {
    	st.executeUpdate(query);
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
		doGet(request, response);
	}

}
