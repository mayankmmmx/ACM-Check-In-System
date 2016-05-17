/*
 * This class interacts with the database to perform the backend operations
 */
import java.sql.*;
import java.util.Calendar;

public class DatabaseUtility {
	
	/*
	 * Gets user using cardID as parameter
	 * Returns User object
	 */
	public static User getUser(String cardID) throws SQLException
	{
		Connection connection = DataConnection.createConnection();
		String sql = "SELECT * FROM points_fall_16 WHERE cardID = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cardID);
        ResultSet rs = statement.executeQuery();
		
        if(rs.next())
        {
        	User temp = new User(rs.getString("cardID"), rs.getString("accessID"), rs.getInt("points"), rs.getTimestamp("last_checkin"));
        	return temp;
        }
        
        return null;
	}
	
	/*
	 * Inserts new user into database and updates points
	 */
	public static void insertNewUser(User user, int points) throws SQLException
	{
		Connection connection = DataConnection.createConnection();
        Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        String sql = "INSERT into points_fall_16 (cardID, accessID, points, last_checkin) values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getCardID());
        statement.setString(2, user.getAccessID());
        statement.setInt(3, (user.getPoints() + points));
        statement.setTimestamp(4, currentTimestamp);        
        statement.executeUpdate();
	}
	
	/*
	 * Updates current user with new points
	 */
	public static void updateUser(User user, int points) throws SQLException
	{
		Connection connection = DataConnection.createConnection();
	    Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());	    
	    
	    String sql = "update points_fall_16 set points = ? where cardID = ?";
	    PreparedStatement preparedStmt = connection.prepareStatement(sql);
	    preparedStmt.setInt(1, (user.getPoints() + points));
	    preparedStmt.setString(2, user.getCardID());
	    preparedStmt.executeUpdate();
	    
	    String sql2 = "update points_fall_16 set last_checkin = ? where cardID = ?";
	    PreparedStatement preparedStmt2 = connection.prepareStatement(sql2);
	    preparedStmt2.setTimestamp(1, currentTimestamp);
	    preparedStmt2.setString(2, user.getCardID());
	    preparedStmt2.executeUpdate();
	}

}
