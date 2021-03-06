/*
 * This class interacts with the database to perform the backend operations
 */
import java.sql.*;
import java.util.Calendar;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseUtility {
	
	private final static String salt="DGE$5SGr@3VsHYUMas23243fwd57vrfraSTRU!D!SH*)%FDSffg13sgfsg";
		
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
	 * Encrypts data using MD5 hash
	 * Returns encrypted String
	 */
	public static String encryptCardID(String cardID) throws NoSuchAlgorithmException
	{
		String salted = cardID + salt;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(salted.getBytes(), 0, salted.length());
        String md5 = new BigInteger(1, digest.digest()).toString(16);

        return md5;
	}
	
	/*
	 * Inserts new user into database and updates points
	 */
	public static void insertNewUser(String cardID, String accessID, int points) throws SQLException
	{
		Connection connection = DataConnection.createConnection();
        Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        String sql = "INSERT into points_fall_16 (cardID, accessID, points, last_checkin) values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cardID);
        statement.setString(2, accessID);
        statement.setInt(3, points);
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
