import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {	
	public static ResultSet getGradesList(Connection conn) throws SQLException{
		String sql = "select * from Grades";
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
	
	public static void writeGrades(String assignment, double grades, Connection conn) throws SQLException{
		String sql = "insert into Grades (assignment, grade) values ('" + assignment + "', " + grades + ")";
		PreparedStatement preStatement = conn.prepareStatement(sql);
		preStatement.executeQuery();
	}
}
