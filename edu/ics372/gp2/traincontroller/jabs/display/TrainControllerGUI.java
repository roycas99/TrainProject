package edu.ics372.gp2.traincontroller.jabs.display;

import edu.ics372.gp2.traincontroller.jabs.buttons.ApproachSignalButton;
import edu.ics372.gp2.traincontroller.jabs.buttons.ArriveSignalButton;
import edu.ics372.gp2.traincontroller.jabs.buttons.DoorObstructionButton;
import edu.ics372.gp2.traincontroller.jabs.buttons.GUIButton;
import edu.ics372.gp2.traincontroller.jabs.buttons.StartButton;
import edu.ics372.gp2.traincontroller.jabs.states.TrainContext;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Banji, Say, Jeffrey, Abshir
 * 
 * GUI to implement the ControllerDisplayInterface interface A pretty elementary
 * interface
 *
 * Updated 4/11/23
 */
public class TrainControllerGUI extends Application implements ControllerDisplayInterface {
	
	private GUIButton approachSignalButton;
	private GUIButton arriveSignalButton;
	private GUIButton makeDoorObstructedButton;
	private GUIButton startButton;
	private HBox hbox4;
	
	private Text doorStatus = new Text("NULL DEFAULT");
	private Text trainStatus = new Text("NULL DEFAULT");
	private Text timerValue = new Text("Default");
	
	private Text trainControllerLabel = new Text("Train Controller");

	/**
	 * Sets up the interface
	 */
	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {

		approachSignalButton = new ApproachSignalButton("Approaching Station");
		arriveSignalButton = new ArriveSignalButton("Station Arrival");
		makeDoorObstructedButton = new DoorObstructionButton("Door Obstruction");
		startButton = new StartButton("Start");

		HBox hbox1 = new HBox(10, trainControllerLabel);
		hbox1.setAlignment(Pos.CENTER);
		HBox hbox2 = new HBox(10, trainStatus, doorStatus, timerValue);
		hbox2.setAlignment(Pos.CENTER);
		hbox4 = new HBox(10, startButton, approachSignalButton, arriveSignalButton, makeDoorObstructedButton);
		hbox4.setAlignment(Pos.CENTER);
		VBox vbox1 = new VBox(10, hbox2, hbox4);
		vbox1.setPadding(new Insets(2));

		showDoorClosed();
		showStopped();
		showTimeLeft(0 + " ");

		Scene scene = new Scene(vbox1);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JABS TRAIN CONTROLLER");
		//System.out.println("TController line 82");
		TrainContext.getInstance().setDisplay(this);
		//System.out.println("TController line 84");
		primaryStage.show();
		primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent window) {
				System.exit(0);
			}
		});
	}

	/**
	 * display the remaining time
	 * 
	 * @param String value remaining
	 */
	@Override
	public void showTimeLeft(String value) {
		//System.out.println("TController.showTimeLeft() : time = " + value);
		timerValue.setText(value);
		//System.out.println("TController.showTimeLeft() line 104"); 								
	} 
	///////////// Train States //////////////// ///are in business
	
	/**
	 * Indicate that train is decelerating
	 */
	@Override
	public void showDecelerating() {
		trainStatus.setText("Train Decelerating");
	}
	
	/**
	 * Indicate that train is accelerating
	 */
	@Override
	public void showAccelerating() {
		//System.out.println("TController.showAccelerating()");
		trainStatus.setText("Train Accelerating");
		//System.out.println("TController.showAccelerating() line 123");
	}
	
	/**
	 * Indicate that train is at full speed
	 */
	@Override
	public void showFullSpeed() {
		trainStatus.setText("Train Full Speed");
	}

	/**
	 * Indicate that train is stopped
	 */
	@Override
	public void showStopped() {
		trainStatus.setText("Train Stopped");
	}

	///////////// Door states ////////////////
	/**
	 * Indicate that the door is closed
	 */
	@Override
	public void showDoorClosed() {
		doorStatus.setText("Door Closed");
	}

	/**
	 * Indicate that the door is opened
	 */
	@Override
	public void showDoorOpened() {
		doorStatus.setText("Door Fully Opened");;
	}

	/**
	 * Indicate that the door is opening
	 */
	@Override
	public void showDoorOpening() {
		doorStatus.setText("Door Opening");
	}

	/**
	 * Indicate that the door is closing
	 */
	@Override
	public void showDoorClosing() {
		doorStatus.setText("Door Closing");
	}

	/**
	 * Indicate that the door is re-opening
	 */
	@Override
	public void showDoorReopening() {
		doorStatus.setText("Door Re-opening");
	}

	/**
	 * Indicate that the door is re-opened
	 */
	@Override
	public void showDoorReopen() {
		doorStatus.setText("Door Fully Re-opened");
	}

	///////////// External Events \\\\\\\\\\\\\\\
	/**
	 * Indicate that train is approaching station
	 */
	public void approachSignalRequest() {
		trainStatus.setText("Train Approaching Station");
	}

	/**
	 * Indicate that train has arrived at station
	 */
	public void arriveSignalRequest() {
	}

	/**
	 * Indicate that the door is obstructed
	 */
	public void doorObstructionRequest() {
		doorStatus.setText("Door Obstructed");
	}
	
	/**
	 * Disable start button after first press
	 */
	public void disableStartButton() {
		startButton.setDisable(true);
	}
}//End class TrainControllerGUI