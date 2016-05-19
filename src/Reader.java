/*
 * This class is used to read and translate the input from the card reader/manual input
 */
public class Reader {
	
	public static String getCardID(String cardID)
	{
		String card = "";
		
		if(cardID.length() == 59) //if swiped as each swipe is 59 characters
		{
			card = cardID.substring(29, 45);
		}
		else if(cardID.length() == 16) //if manually entered
		{
			card = cardID;
		}
		
		return card;
	}
	
}
