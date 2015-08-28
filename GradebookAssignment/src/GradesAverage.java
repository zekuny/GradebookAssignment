// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/GradesAverage")
public class GradesAverage extends HttpServlet {
	public void init() throws ServletException{
		// Do required initialization
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String gpa = request.getParameter("gpa");
		response.setContentType("text/html");
		request.setAttribute("gpa", gpa); // the first is the jsp attribute, the second is the servlet attribute
		getServletContext().getRequestDispatcher("/average.jsp").forward(request, response);
	} 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}