package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MakeAppointmentController 
{
	@FXML
	private Button backButton;
	@FXML
	private Button okButton;
	@FXML
	private Label requestDoctorLabel;
	@FXML
	private Label doctorInfoLabel;
	@FXML
	private Label availableLabel;
	@FXML
	private TextField requestDoctorField;
	
	//second half
	@FXML
	private Label nameLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label completeLabel;
	@FXML
	private TextField nameField;
	@FXML
	private TextField dateField;
	@FXML
	private TextField timeField;
	@FXML
	private Button doneButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private String doctorName;
	private String patientName;
	private String date;
	private String time;
	
	public void goBack(ActionEvent event) throws IOException
	{
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("StaffSelection.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Staff Interface");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.show();
	}
	
	public void okButtonPressed(ActionEvent event)
	{
		if(MakeAppointment.checkDoctorExists(requestDoctorField.getText().trim()) == true)
		{
			doctorName = requestDoctorField.getText().trim();
			doctorInfoLabel.setText(MakeAppointment.DoctorsSchedule("Doctor_Schedule.txt",requestDoctorField.getText().trim(),1,","));
			doctorInfoLabel.setVisible(true);
			availableLabel.setVisible(true);
			
			nameLabel.setVisible(true);
			dateLabel.setVisible(true);
			timeLabel.setVisible(true);
			nameField.setVisible(true);
			dateField.setVisible(true);
			timeField.setVisible(true);
			doneButton.setVisible(true);
			
			
			nameField.clear();
			dateField.clear();
			timeField.clear();
			completeLabel.setVisible(false);
		}
		else
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("No Doctor Found");
			error.setContentText("Please Try Again.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
			
			if(error.showAndWait().get() == ButtonType.OK)
			{
				error.hide();
				
				doctorInfoLabel.setVisible(false);
				availableLabel.setVisible(false);
				requestDoctorField.clear();
				
				nameLabel.setVisible(false);
				dateLabel.setVisible(false);
				timeLabel.setVisible(false);
				nameField.setVisible(false);
				dateField.setVisible(false);
				timeField.setVisible(false);
				doneButton.setVisible(false);
				completeLabel.setVisible(false);
				nameField.clear();
				dateField.clear();
				timeField.clear();
			}
		}
		
	}
	
	public void donePressed(ActionEvent event) throws IOException
	{
		patientName = nameField.getText().trim();
		date = dateField.getText().trim();
		time = timeField.getText().trim();
		
		/*Make sure to change the txt file to the name of your doctor schedule file*/
        boolean Valid = MakeAppointment.ValidateAppointment("Doctor_Schedule.txt", doctorName, date, time); /*boolean valid can probably be removed and just put ValidateAppointment("Doctor_Schedule.txt",DoctorName,Date,Time) in if statement below*/
        
        if(Valid)
        {
            MakeAppointment.AppointmentBuilder(doctorName,patientName,date,time);
            MakeAppointment.RemoveAvailableTime("Doctor_Schedule.txt",doctorName,date,time);
            completeLabel.setVisible(true);
        }
        else
        {
        	Alert error2 = new Alert(AlertType.ERROR);
			error2.setTitle("ERROR");
			error2.setHeaderText("Not an Available Time!");
			error2.setContentText("Please Try Again.");

			Window alertWindow2 = error2.getDialogPane().getScene().getWindow();
			alertWindow2.setOnCloseRequest(e -> e.consume());
			
			if(error2.showAndWait().get() == ButtonType.OK)
			{
				error2.hide();
				nameField.clear();
				dateField.clear();
				timeField.clear();
				completeLabel.setVisible(false);
			}
        } 
	}
}
