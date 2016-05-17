/*
 * This class represents a user entry from the database
 */
import java.sql.Timestamp;

public class User {
	
	private String cardID;
	private String accessID;
	private int points;
	private Timestamp time;
	
	/*
	 * Default Constructor taking in cardID, accessID, points, and last checked in
	 */
	public User(String _cardID, String _accessID, int _points, Timestamp _time)
	{
		cardID = _cardID;
		accessID = _accessID;
		points = _points;
		time = _time;
	}
	
	/*
	 * Gets Card ID
	 */
	public String getCardID()
	{
		return cardID;
	}
	
	/*
	 * Gets Access ID
	 */
	public String getAccessID()
	{
		return accessID;
	}
	
	/*
	 * Gets Points
	 */
	public int getPoints()
	{
		return points;
	}
	
	/*
	 * Gets last timestamp
	 */
	public Timestamp getTimeStamp()
	{
		return time;
	}
	
	/*
	 * Custom to string
	 */
	@Override
	public String toString()
	{
		return "Card ID = " + cardID + "\n Access ID: " + accessID + "\n Points: " + points + "\n Timestamp: " + time.toString();
	}
}
