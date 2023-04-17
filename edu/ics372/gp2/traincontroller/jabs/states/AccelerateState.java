package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.timer.Notifiable;
import edu.ics372.gp2.traincontroller.jabs.timer.Timer;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * Represents Accelerate State
 * 6sec Timer event, ApproachSignalButton Event
 * 
 * Updated 4/13/23
 */
public class AccelerateState extends TrainState implements Notifiable {
	private static final int SIXSECONDS = 6;
	private static AccelerateState instance;
	private Timer timer;

	/**
	 * Private for the singleton pattern
	 */
	private AccelerateState() {
	}//End constructor

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static AccelerateState getInstance() {
		if (instance == null) {
			instance = new AccelerateState();
		}
		return instance;
	}//End getInstance

	/**
	 * Initiate acceleration
	 */
	@Override
	public void onAccelerate() {
		//System.out.println("AccelerateState.onAccelerate()");
		timer = new Timer(this, SIXSECONDS);
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	}//End onAccelerate

	/**
	 * Process approach signal
	 * Transitions to DecelerateState
	 */
	@Override
	public void onApproachSignal() {
		//System.out.println("AccelerateState.onApproachSignal()");
		TrainContext.getInstance().changeState(DecelerateState.getInstance());
	}//End onApproachSignal

	/**
	 * Process clock tick event
	 * @param int timerValue
	 */
	@Override
	public void onTimerTick(int timerValue) {
		//System.out.println("AccelerateState.onTimerTick() timerValue = " + timerValue);
		TrainContext.getInstance().showTimeLeft(timerValue + " sec");
	}//End onTimerTick

	/**
	 * Process the timer runs out event
	 * Transition to FullSpeedState
	 */
	@Override
	public void onTimerRunsOut() {
		//System.out.println("AccelerateState.onTimerRunsOut()");
		TrainContext.getInstance().showTimeLeft(0 + " sec");
		TrainContext.getInstance().changeState(FullSpeedState.getInstance());
	}//End onTimerRunsOut

	/**
	 * Initializes the state 
	 * Initialize a 6 sec timer
	 * Adds itself as a listener to managers Updates the
	 * displays
	 * 
	 */
	@Override
	public void enter() {
		//System.out.println("AccelerateState.enter()");
		timer = new Timer(this, SIXSECONDS);
		// this.onAccelerate();
		TrainContext.getInstance().showAccelerating();
		TrainContext.getInstance().showTimeLeft(timer.getTimeValue() + " sec");
	}//End enter
	
	/**
	 * Exits this state
	 * resets timer
	 */
	@Override
	public void leave() {
		//System.out.println("AccelerateState.leave()");
		timer.stop();
		timer = null;
		// TrainContext.getInstance().showFullSpeed();
		TrainContext.getInstance().showTimeLeft(0 + " ");
	}//End leave
}//End Class AccelerateState
