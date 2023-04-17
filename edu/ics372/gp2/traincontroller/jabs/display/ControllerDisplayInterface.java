package edu.ics372.gp2.traincontroller.jabs.display;

/**
 * @author Banji, Say, Jeffrey, Abshir
 * 
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 * 
 * Updated 4/11/23
 * updated 4/17/23
 */
public interface ControllerDisplayInterface {
	
	/**
	 * Displays the  left time remaining
	 * 
	 * @param String remaining time
	 */
	public void showTimeLeft(String time);

	/**
	 * Indicate that the door is now closed
	 */
	public void showDoorClosed();
	
	/**
	 * Indicate that the door is opening
	 */
	public void showDoorOpening();

	/**
	 * Indicate that the door is now open
	 */
	public void showDoorOpened();
	
	/**
	 * Indicate that the door is closing
	 */
	public void showDoorClosing();
	
	/**
	 * Indicate that the door is reopening
	 */
	public void showDoorReopening ();
	
	/**
	 * Indicate that the door is reopened
	 */
	public void showDoorReopen ();
	
	/**
	 * Indicate that train is accelerating
	 */
	public void showAccelerating ();
	
	/**
	 * Indicate that train is at full speed
	 */
	public void showFullSpeed ();
	
	/**
	 * Indicate that train is decelerating
	 */
	public void showDecelerating ();
	
	/**
	 * Indicate that train is stopped
	 */
	public void showStopped ();
}//End Interface ControllerDisplayInterface