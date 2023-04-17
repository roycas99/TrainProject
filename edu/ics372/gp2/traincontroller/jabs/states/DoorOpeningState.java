package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * Represent Door Opening State
 * 4 sec Timer event
 * 
 * Updated 4/13/23
 */
public class DoorOpeningState extends TrainState implements Notifiable {
	private static final int FOURSECONDS = 4;
	private static DoorOpeningState instance;
	private Timer timer;

	/**
	 * Private for the singleton pattern
	 */
	private DoorOpeningState() {
	}//End constructor

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static DoorOpeningState getInstance() {
		if (instance == null) {
			instance = new DoorOpeningState();
		}
		return instance;
	}//End getInstance

	/**
	 * Process onDoorOpening request
	 */
	@Override
	public void onDoorOpening() {
		//System.out.println("DoorOpeningState.onDoorOpening()");
		timer.addTimeValue(FOURSECONDS);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End onDoorOpening

	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("DoorOpeningState.OnTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	} //End onTimerTick

	/**
	 * Process the timer runs out event
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("DoorOpeningState.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(DoorOpenState.getInstance());
	} //End onTimerRunsOut

	/**
	 * Initializes the state 
	 * Initialize a 4 sec Timer
	 * Adds itself as a listener to managers Updates the
	 * displays
	 */
	@Override
	public void enter() {
		//System.out.println("DoorOpeningState.enter()");
		timer = new Timer(this, FOURSECONDS);
		TrainContext.getInstance().showDoorOpening();
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End enter

	/**
	 * Exit this state
	 * Resets Timer
	 */
	@Override
	public void leave() {
		//System.out.println("DoorOpeningState.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showDoorOpened();
		TrainContext.getInstance().showTimeLeft(0 + " sec");
	} //End leave
} //End class DoorOpeningState