/*
 * This Custom JRadioButton class allows for weighted buttons so that specific points can be awarded for different selected buttons.
 */

import javax.swing.JRadioButton;

public class CustomRadioButton extends JRadioButton {
	
	private int pointValue;
	
	/*
	 * Constructor that takes in general parameters of JRadioButton with the added points parameter to add different weights to the button
	 */
	public CustomRadioButton(String text, Boolean selected , int points)
	{
		super(text, selected);
		pointValue = points;
	}
	
	/*
	 * This method returns the point value of the button
	 */
	public int getPointValue()
	{
		return pointValue;
	}
	
}
