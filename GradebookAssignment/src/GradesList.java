// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/GradesList")
public class GradesList extends HttpServlet {
	private String table;
	private double average;
	private static Connection conn = null;
	public void init() throws ServletException{
		// Do required initialization
		table = "";
		average = 0.0;
		try {
			conn = DBConnection.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String assignment = request.getParameter("Assignment");
		double grades = Double.parseDouble(request.getParameter("Grades"));
		try {
			writeGrades(assignment, grades, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = getGradesList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Set response content type
		response.setContentType("text/html");
		// write to table
		table = "";
		average = 0.0;
		int count = 0;
		double gpa = 0.0;
		table += "<div class=\"container\">" + "<h2>All Customers</h2>" + "<table class=\"table table-condensed\">"
		    + "<thead>" + "<tr>" + "<th>Assignment</th>" + "<th>Grade</th>" + "</tr>"
		    +   "</thead>" + "<tbody>";	    
    	try {
			while(result.next()){
				table += "<tr>";
				//String custId = request.getParameter("customerID");
				double grade = Double.parseDouble(result.getString("Grade"));
				table += "<td>" + result.getString("Assignment") + "</td>";
				table += "<td>" + grade + "</td>";
				table += "</tr>";
				gpa += grade;
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	average = gpa / count;
		table += "</tbody>" + "</table>" + "</div>";
		
		table += "<form class=\"form-horizontal\" method=\"post\" action=\"GradesAverage\">";
		table += "<input type=\"hidden\" id=\"gpa\" name=\"gpa\" value=\"" + average + "\">";
		table += "<input id=\"submit\" name=\"submit\" type=\"submit\" value=\"View you GPA\" class=\"btn btn-primary\">";
		table += "</from>";
		request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	} 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	public void destroy(){ 
		// do nothing. 
	} 
	
	public void writeGrades(String assignment, double grades, Connection conn) throws SQLException{
		DBOperation.writeGrades(assignment, grades, conn);
	}
	
	public ResultSet getGradesList(Connection conn) throws SQLException{
		return DBOperation.getGradesList(conn);
	}
	
}