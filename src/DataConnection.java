/*
 * This class serves to connect the application to the Database.
 * To connect to the database on the PSU network, you must create an SSH tunnel. For more information on this, check ACM wiki.
 */
import java.sql.*;

public class DataConnection {
		
	private static final String HOSTNAME = "localhost:3306";
	private static final String DATABASENAME = "points";
	private static final String username = "points";
	private static final String password = "pOiNtS"; //CHANGE ON FINAL APP VERSION
	
    public static Connection createConnection() throws SQLException
    {
    	String databaseURL = "jdbc:mysql://" + HOSTNAME +"/" + DATABASENAME;
    	Connection connection = null;
    	if(connection == null)
    	{
    		connection = DriverManager.getConnection(databaseURL, username, password);
    		System.out.println("Connected.");
    	}

        return connection;
    }

}
