package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * Represents Door Re-Opening State
 * 8 sec Timer
 * 
 * Updated 4/11/23
 */
public class DoorReopeningState extends TrainState implements Notifiable {
	private static int timeValue;
	private static DoorReopeningState instance;
	private Timer timer;

	/**
	 * Private for the singleton pattern
	 */
	private DoorReopeningState() {
	}//End constructor

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static DoorReopeningState getInstance(int time) {
		if (instance == null) {
			instance = new DoorReopeningState();
		}
		timeValue = time;
		return instance;
	} //End getInstance

	/**
	 * Process onDoorReopening request
	 */
	@Override
	public void onDoorReopening() {
		//System.out.println("DoorReopeningState.onDoorReopening()");
		timer.addTimeValue(timeValue);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End onDoorReopening

	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("DoorReopeningState.onTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	} //End onTimerTick

	/**
	 * Process the timer runs out event
	 * Transition to DoorReopeningState
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("DoorReopeningState.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(DoorReopenState.getInstance());
	} //End onTimerRunsOut

	/**
	 * Initializes the state 
	 * Initialize Timer
	 * Adds itself as a listener to managers Updates the
	 * displays
	 */
	@Override
	public void enter() {
		//System.out.println("DoorReopeningState.enter()");
		timer = new Timer(this, timeValue);
		TrainContext.getInstance().showDoorReopening();
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End enter

	/**
	 * Exit state
	 * Reset Timer
	 */
	@Override
	public void leave() {
		//System.out.println("DoorReopeningState.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showDoorOpened();
		TrainContext.getInstance().showTimeLeft(0 + " sec");
	} //End leave
} //End class DoorReopeningState
