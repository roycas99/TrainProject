package edu.ics372.gp2.traincontroller.jabs.states;

import edu.ics372.gp2.traincontroller.jabs.display.TrainControllerGUI;

/**
 * @author Banji, Say, Jeffrey, Abshir 
 * The context is an observer for the clock and stores the
 * context info for states which need the clock and implement
 * Notifiable.
 * 
 * The states which need TrainContext are Accelerate DoorOpening
 * DoorReOpening DoorReOpen DoorOpen DoorClosed DoorClosingState Stop
 * 
 * Updated 4/11/23
 */
public class TrainContext {
	private TrainControllerGUI display;
	private TrainState currentState;
	private static TrainContext instance;

	/**
	 * Make it a singleton
	 */
	private TrainContext() {
		instance = this;
		currentState = AccelerateState.getInstance();
	}//End constructor

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static TrainContext getInstance() {
		if (instance == null) {
			instance = new TrainContext();
		}
		return instance;
	}//End getInstance

	/**
	 * The display could change. So we have to get its reference.
	 * 
	 * @param display The current display object
	 */
	public void setDisplay(TrainControllerGUI display) {
		//System.out.println("TContext setDisplay");
		this.display = display;
		// initialize();
	}//End setDisplay

	/**
	 * Lets door closed state be the starting state 
	 * adds the object as an observable
	 * for clock
	 */
	public void initialize() {
		//System.out.println("TContext initialize");
		// instance.changeState(DoorClosedState.getInstance());
		currentState = DoorClosedState.getInstance();
		TrainContext.getInstance().showDoorClosed();
		display.disableStartButton();
		currentState.enter();
	}//End Initialize

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState the next state
	 */
	public void changeState(TrainState nextState) {
		//System.out.println("TContext.changeState()");
		currentState.leave();
		currentState = nextState;
		currentState.enter();
	} //End changeState

	/**
	 * The onRequest methods switch context to the next state
	 */

	public void onAccelerateRequest() {
		//System.out.println("TContext.onAccelerateRequest()");
		currentState.onAccelerate();
	} //End onAccelerateRequest
	
	public void onStopRequest() {
		currentState.onStopped();
	} //End onStopRequest

	public void onDoorOpeningRequest() {
		currentState.onDoorOpening();
	} //End onDoorOpeningRequest

	public void onDoorOpenRequest() {
		currentState.onDoorOpen();
	}//End onDoorOpenRequest

	public void onDoorReopenRequest() {
		currentState.onDoorReopen();
	}//End onDoorReopenRequest

	public void onDoorReOpeningRequest() {
		currentState.onDoorReopening();
	}//End onDoorReopeningRequest

	public void onDoorClosingRequest() {
		currentState.onDoorClosing();
	}//End onDoorClosingRequest

	public void onDoorClosedRequest() {
		currentState.onDoorReopen();
	}//End onDoorClosedRequest

	/**
	 * The show family of methods switch the display context
	 * 
	 * @param String time left for cooking
	 */
	public void showTimeLeft(String time) {
		//System.out.println("TContext.showTimeLeft() : time = " + time);
		display.showTimeLeft(time);
		//System.out.println("TContext.showTimeLeft() line 126 ");
	}//End showTimeLeft

	public void showAccelerating() {
		//System.out.println("TContext.showAccelerating()");
		display.showAccelerating();
	}//End showAccelerating

	public void showFullSpeed() {
		display.showFullSpeed();
	}//End showFullSpeed

	public void showDecelerating() {
		display.showDecelerating();
	}//End showDecelerating

	/*
	 * public void showDoorClosing
	 * updates display
	 */
	public void showDoorClosing() {
		display.showDoorClosing();
	}

	public void showDoorOpening() {
		display.showDoorOpening();
	}//End showDoorOpening

	public void showDoorReopening() {
		display.showDoorReopening();
	}//End showDoorReopening

	public void showDoorReopen() {
		display.showDoorReopen();
	}//End showDoorReopen

	public void showDoorOpened() {
		display.showDoorOpened();
	}//End showDoorOpened

	public void showDoorClosed() {
		display.showDoorClosed();
	}//End showDoorClosed

	public void approachSignalRequest() {
		currentState.onApproachSignal();
		// display.approachSignalRequest();
	}//End approachSignalRequest

	public void arriveSignalRequest() {
		currentState.onArriveSignal();
		// display.arriveSignalRequest();
	}

	public void doorObstructionRequest() {
		currentState.onDoorObstructedSignal();
		// display.doorObstructionRequest();
	}

	public void showStopped() {
		display.showStopped();
	}//End showStopped
} //End class TrainContext
