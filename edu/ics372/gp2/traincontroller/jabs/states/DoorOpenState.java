package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * Represents Door Open State
 * 3 sec Timer event
 * 
 * Updated 4/13/23
 */
public class DoorOpenState extends TrainState implements Notifiable {
	private static final int THIRTYSECONDS = 30;
	private static DoorOpenState instance;
	private Timer timer;

	/**
	 * Private constructor for the singleton pattern
	 */
	private DoorOpenState() {
		instance = this;
	}

	/**
	 * For the singleton pattern
	 * 
	 * @return the object
	 */
	public static DoorOpenState getInstance() {
		if (instance == null) {
			instance = new DoorOpenState();
		}
		return instance;
	}

	/**
	 * Process onDoorOpen request
	 */
	@Override
	public void onDoorOpen() {
		//System.out.println("DoorOpenstate.onDoorOpen()");
		timer.addTimeValue(THIRTYSECONDS);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End onDoorOpen

	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("DoorOpenstate.onTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	} //End onTimerTick

	/**
	 * Process the timer runs out event
	 * Transition to DoorClosingState
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("DoorOpenstate.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(DoorClosingState.getInstance());
	} //End onTimerRunsOut

	/**
	 * Initialize the state
	 * Initialize 30 sec timer
	 * Registers as a listener
	 * Updates display
	 */
	@Override
	public void enter() {
		//System.out.println("DoorOpenstate.enter()");
		timer = new Timer(this, THIRTYSECONDS);
		TrainContext.getInstance().showDoorOpened();
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End enter
	
	/**
	 * Exit the state
	 * Resets Timer
	 */
	@Override
	public void leave() {
		//System.out.println("DoorOpenstate.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showDoorClosing();
		TrainContext.getInstance().showTimeLeft(0 + " sec");
	} //End leave
} //End class DoorOpenState