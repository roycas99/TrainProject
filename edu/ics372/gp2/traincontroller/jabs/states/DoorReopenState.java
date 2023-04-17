package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * Represents Door Re-Open State
 * 8 sec Timer
 * 
 * Updated 4/13/23
 */
public class DoorReopenState extends TrainState implements Notifiable {
	private static final int EIGHTSECONDS = 8;
	private static DoorReopenState instance;
	private Timer timer;

	/**
	 * Private for the singleton pattern
	 */
	private DoorReopenState() {
	}//End Constructor

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static DoorReopenState getInstance() {
		if (instance == null) {
			instance = new DoorReopenState();
		}
		return instance;
	}//End getInstance

	/**
	 * Process DoorReopen request
	 */
	@Override
	public void onDoorReopen() {
		//System.out.println("DoorReopenState.onDoorReopen()");
		timer.addTimeValue(EIGHTSECONDS);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End onDoorReopen
	
	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("DoorReopenState.onTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	} //End onTimerTick

	/**
	 * Process the timer runs out event
	 * Transition to DoorClosingState
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("DoorReopenState.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(DoorClosingState.getInstance());
	} //End onTimerRunsOut

	/**
	 * Initializes the state
	 * Initialize 8 sec Timer 
	 * Adds itself as a listener to managers Updates the
	 * displays
	 * 
	 */
	@Override
	public void enter() {
		//System.out.println("DoorReopenState.enter()");
		timer = new Timer(this, EIGHTSECONDS);
		TrainContext.getInstance().showDoorReopen();
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End enter
	
	/**
	 * Exit this state
	 * Reset Timer
	 */
	@Override
	public void leave() {
		//System.out.println("DoorReopenState.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showDoorClosing();
		TrainContext.getInstance().showTimeLeft(0 + " sec");
	} //End leave
} //End class DoorReopenState
