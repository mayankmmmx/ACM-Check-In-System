/*
 * This class serves to connect the application to the Database.
 * To connect to the database on the PSU network, you must create an SSH tunnel. For more information on this, check ACM wiki.
 */
import java.sql.*;

public class DataConnection {

    public static Connection createConnection() throws SQLException
    {
    	String databaseURL = "jdbc:mysql://" + Constants.HOST_NAME +"/" + Constants.DATABASE_NAME;
    	Connection connection = null;
    	if(connection == null)
    	{
    		connection = DriverManager.getConnection(databaseURL, Constants.USERNAME, Constants.PASSWORD);
    	}

        return connection;
    }

}
