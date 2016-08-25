/*
 * This class contains the GUI elements of the application
 */
import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class CheckInGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	//Initialized key variables
	private JFrame loginFrame;
	private JFrame checkInFrame;
	private JLabel loginHeading;
	private JLabel checkInHeading;
	private JLabel hostName;
	private JLabel databaseName;
	private JLabel tableName;
	private JLabel userName; 
	private JLabel password;
	private JLabel cardID;
	private JTextField hostNameField;
	private JTextField databaseNameField;
	private JTextField tableNameField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField cardIDField;
	private JButton login;
	private JButton submit; 
	private CustomRadioButton general;
	private CustomRadioButton extended;
	private CustomRadioButton specialEvent;
	private ButtonGroup allRadioButtons;
	
	/*
	 * GUI Constructor
	 */
	public CheckInGUI() {
		
		setUpLoginFrame(); //sets up login frame 
        setUpCheckInFrame(); //sets up check in frame
        initiateActionListeners(); //initiates Action listeners
	}

	/*
	 * Sets up login frame for application
	 */
	public void setUpLoginFrame()
	{
		//Sets screen size for login planel
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //screensize for panel sizing   
		loginFrame = new JFrame("Login to ACM Check-In"); //login frame
		screenSize.height = (int) (screenSize.getHeight()/3);
        screenSize.width = (int) (screenSize.getWidth()/3);
        
		//All applicable subpanels 
		JPanel loginPanel = new JPanel(); 
		JPanel loginHeadingPanel = new JPanel();
		JPanel hostNamePanel = new JPanel();
		JPanel databaseNamePanel = new JPanel();
		JPanel tableNamePanel = new JPanel();
		JPanel userNamePanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel loginButtonPanel = new JPanel();
	    
		//Initializes all objects
        loginHeading = new JLabel("LOGIN TO ACM CHECK-IN SYSTEM");
        hostName = new JLabel("Host Name: ");
        databaseName = new JLabel("Database Name: ");
        tableName = new JLabel("Table Name: ");
        userName = new JLabel("User Name: ");
        password = new JLabel("Password: ");
        hostNameField = new JTextField("localhost:3306",10);
        databaseNameField = new JTextField("points", 10);
        tableNameField = new JTextField("points_fall_16",10);
        userNameField = new JTextField("points", 10);
        passwordField = new JPasswordField(10);
        login = new JButton("Login");
        
        loginHeading.setFont(new Font(loginHeading.getName(), loginHeading.getFont().getStyle(), 25)); //sets font for login heading
        
        //Adds all objects to subpanels
        loginHeadingPanel.add(loginHeading);
        hostNamePanel.add(hostName);
        hostNamePanel.add(hostNameField);
        databaseNamePanel.add(databaseName);
        databaseNamePanel.add(databaseNameField);
        tableNamePanel.add(tableName);
        tableNamePanel.add(tableNameField);
        userNamePanel.add(userName);
        userNamePanel.add(userNameField);
        passwordPanel.add(password);
        passwordPanel.add(passwordField);
        loginButtonPanel.add(login);
        
        //Adds all subpanels to main panel and frame with Box Layout
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.add(loginHeadingPanel);
        loginPanel.add(hostNamePanel);
        loginPanel.add(databaseNamePanel);
        loginPanel.add(tableNamePanel);
        loginPanel.add(userNamePanel);
        loginPanel.add(passwordPanel);
        loginPanel.add(loginButtonPanel);
        loginFrame.add(loginPanel);
        
        //Frame sizing, pack, and visibility
        loginFrame.setPreferredSize(screenSize);
        loginFrame.setResizable(false);
        loginFrame.pack();
        loginFrame.setVisible(true);
	}
	
	/*
	 * Sets up Check-In frame for application
	 */
	public void setUpCheckInFrame()
	{
		//Sets screen size for login panel
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //screensize for panel sizing   
		checkInFrame = new JFrame("ACM Check-In"); //login frame
		screenSize.height = (int) (screenSize.getHeight()/2.5);
        screenSize.width = (int) (screenSize.getWidth()/2.5);
        
        JPanel checkInPanel = new JPanel();
        JPanel checkInHeadingPanel = new JPanel();
        JPanel cardIDPanel = new JPanel();
        JPanel radioButtonPanel1 = new JPanel();
        JPanel radioButtonPanel2 = new JPanel();
        JPanel radioButtonPanel3 = new JPanel();
        JPanel submitPanel = new JPanel();
        
        checkInHeading = new JLabel("ACM CHECK-IN SYSTEM");
        checkInHeading.setFont(new Font(loginHeading.getName(), loginHeading.getFont().getStyle(), 25)); //sets font for login heading
        cardID = new JLabel("Enter Card ID: ");
        cardIDField = new JPasswordField(20);
        submit = new JButton("Submit");
        
        allRadioButtons = new ButtonGroup();
        general = new CustomRadioButton("General Meeting - 50pts", true, 50);
        extended = new CustomRadioButton("Extended Meeting - 100pts", false, 100);
        specialEvent = new CustomRadioButton("CodePSU - 150pts", false, 150);
        allRadioButtons.add(general);
        allRadioButtons.add(extended);
        allRadioButtons.add(specialEvent);
        
        checkInHeadingPanel.add(checkInHeading);
        cardIDPanel.add(cardID);
        cardIDPanel.add(cardIDField);
        radioButtonPanel1.add(general);
        radioButtonPanel2.add(extended);
        radioButtonPanel3.add(specialEvent);
        submitPanel.add(submit);
          
        checkInPanel.setLayout(new BoxLayout(checkInPanel, BoxLayout.Y_AXIS));
        checkInPanel.add(checkInHeadingPanel);
        checkInPanel.add(cardIDPanel);
        checkInPanel.add(radioButtonPanel1);
        checkInPanel.add(radioButtonPanel2);
        checkInPanel.add(radioButtonPanel3);
        checkInPanel.add(submitPanel);
        
        checkInFrame.add(checkInPanel);
        
        //Frame sizing, pack, and visibility
        checkInFrame.setPreferredSize(screenSize);
        checkInFrame.setResizable(false);
        checkInFrame.pack();
        checkInFrame.setVisible(false);
	}
	
	/*
	 * Initiates Action Listeners
	 */
	public void initiateActionListeners()
	{
		loginFrame.addWindowListener(new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        passwordField.requestFocus();
		    }
		}); 
		
		login.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					//Sets constants based on login inputs
					Constants.HOST_NAME = hostNameField.getText();
					Constants.DATABASE_NAME = databaseNameField.getText();
					Constants.TABLE_NAME = tableNameField.getText();
					Constants.USERNAME = userNameField.getText();
					Constants.PASSWORD = new String(passwordField.getPassword());
					
					try {
						
						Connection test = DataConnection.createConnection(); //tries to connect. If connection works, then goes to check in frame.
						loginFrame.setVisible(false);
						checkInFrame.setVisible(true);
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(loginFrame, "Error connecting to database. Please try again.");
					}

				}
			}
		);
		
		submit.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						submitEntry();
					}
				
				}
			);
		
		// Listens for change in text field to auto-submit upon swipe
		/*cardIDField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				// TODO Auto-generated method stub
				if(new String(cardIDField.getPassword()).length() == 59)
					submitEntry();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {}

			@Override
			public void changedUpdate(DocumentEvent e){}
		});*/
		
		cardIDField.addKeyListener(new KeyAdapter()
	    {
	        public void keyPressed(KeyEvent ke)
	        {
	        	if(new String(cardIDField.getPassword()).length() == 59)
					submitEntry();
	        }
	    });
	}
	
	/*
	 * Gets points based on selected box
	 */
	public int getPoints()
	{
		if(general.isSelected())
			return general.getPointValue();
		else if (extended.isSelected())
			return extended.getPointValue();
		else if (specialEvent.isSelected())
			return specialEvent.getPointValue();
		
		return 0;
	}
	
	/*
	 * Submits entry
	 */
	public void submitEntry()
	{
		String cardInput = Reader.getCardID(new String(cardIDField.getPassword()));
		String cardID = "";
		try {
			cardID = DatabaseUtility.encryptCardID(cardInput);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!cardID.isEmpty())
		{
			try {
				
				User user = DatabaseUtility.getUser(cardID);
				
				if(user == null)
				{
					String accessID = JOptionPane.showInputDialog(checkInFrame, "Enter PSU Access ID (xyz1234):");
					System.out.println(cardID + "    "  + accessID);
					DatabaseUtility.insertNewUser(cardID, accessID, getPoints());
				}
				else
				{
					DatabaseUtility.updateUser(user, getPoints());
				}
				
				User temp = DatabaseUtility.getUser(cardID);
				String message = "Successfully entered! You now have " + temp.getPoints() + " points!";
				JOptionPane.showMessageDialog(checkInFrame, message);
				cardIDField.setText("");
				cardIDField.requestFocus();
				
			} catch (SQLException e1) {}
		}
		else
		{
			JOptionPane.showMessageDialog(checkInFrame, "Incorrect Card ID. Please try again.");
			cardIDField.requestFocus();
		}
	}
	
	/*
	 * Main method to initiate GUI
	 */
	public static void main(String[] args) throws SQLException {
		
		CheckInGUI checkin = new CheckInGUI();

	}

}
