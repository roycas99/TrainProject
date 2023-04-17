package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author banji, say, jeffrey, abshir
 *  Represents the Stop state.
 *  1 sec Timer
 *  
 *  Updated 4/11/23
 */
public class StopState extends TrainState implements Notifiable {
	private static final int ONESECOND = 1;
	private static StopState instance;
	private Timer timer;

	/**
	 * Private for the singleton pattern
	 */
	private StopState() {
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static StopState getInstance() {
		if (instance == null) {
			instance = new StopState();
		}
		return instance;
	} //End getInstance

	/**
	 * Process Stop request
	 */
	@Override
	public void onStopped() {
		//System.out.println("StopState.onStop()");
		timer.addTimeValue(ONESECOND);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End onStopped

	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("StopState.onTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	} //End onTimerTick

	/**
	 * Process the timer runs out event
	 * Transition to DoorOpeningState
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("StopState.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(DoorOpeningState.getInstance());
	} //End onTimerRunsOut

	/**
	 * Initializes the state
	 * Initialize 1 sec Timer 
	 * Adds itself as a listener to managers Updates the
	 * displays
	 */
	@Override
	public void enter() {
		//System.out.println("StopState.enter()");
		timer = new Timer(this, ONESECOND);
		TrainContext.getInstance().showStopped(); // need to change to stopped state
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	} //End enter

	@Override
	public void leave() {
		//System.out.println("StopState.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showAccelerating();
		TrainContext.getInstance().showTimeLeft(0 + " sec");
	} //End leave
} //End class StopState
