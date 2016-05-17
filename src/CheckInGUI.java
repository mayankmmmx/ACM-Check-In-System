/*
 * This class contains the GUI elements of the application
 */
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class CheckInGUI extends JFrame{

	private JFrame loginFrame;
	private JFrame checkInFrame;
	private JLabel loginName; 
	
	public CheckInGUI() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();       
		loginFrame = new JFrame("Login to ACM Check-In");
		
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        screenSize.height = (int) (screenSize.getHeight()/3);
        screenSize.width = (int) (screenSize.getWidth()/3);
        
        loginFrame.setPreferredSize(screenSize);
        
        loginFrame.pack();
        loginFrame.setVisible(true);
        
	}
	
	
	
	
	public static void main(String[] args) throws SQLException {
		
		CheckInGUI checkIn = new CheckInGUI();
		
		
		// TODO Auto-generated method stub
		
		//System.out.println(DatabaseUtility.getUser("1234567890"));
		
		//User user = new User("987654321", "xyz111", 50, null);
		
	//	DatabaseUtility.updateUser(user, 50);

	}

}
