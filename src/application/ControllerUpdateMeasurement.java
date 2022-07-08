package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerUpdateMeasurement 
{
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane scenePane;
	@FXML
	private Button okButton;
	@FXML
	private Button updateButton;
	@FXML
	private Label patientLabel;
	@FXML
	private Label reasonLabel;
	@FXML
	private Label vitalLabel;
	@FXML
	private Label patientInfoLabel;
	@FXML
	private Label updateCompleteLabel;
	@FXML
	private TextField nameField;
	@FXML
	private TextField reasonField;
	@FXML
	private TextField vitalField;
	@FXML
	private Line dividor;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private UpdateMeasurement update;
	private String namefield;
	
	public void okPressed(ActionEvent event) throws IOException
	{
		String patientName = nameField.getText().trim();
		namefield = patientName;
		update = new UpdateMeasurement(LogInController.getEmployeeID(), patientName);
		
		if(update.validateEmployeeID() == false)
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("You do not have authorization for Updating!");
			error.setContentText("Please Go Back.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
	
			if(error.showAndWait().get() == ButtonType.OK)
			{
				error.hide();
				updateButton.setVisible(false);
				reasonLabel.setVisible(false);
				vitalLabel.setVisible(false);
				patientInfoLabel.setVisible(false);
				reasonField.setVisible(false);
				vitalField.setVisible(false);
				dividor.setVisible(false);
				updateCompleteLabel.setVisible(false);
				nameField.clear();
			}
				
		}
		else		
		{
			if(update.showPatientMedicalInfo() == null)
			{
				Alert error2 = new Alert(AlertType.ERROR);
				error2.setTitle("ERROR");
				error2.setHeaderText("This Patient is Not Found!");
				error2.setContentText("Please Check Spacing and Capital Letters and Try Again!");
				Window alertWindow = error2.getDialogPane().getScene().getWindow();
				alertWindow.setOnCloseRequest(e -> e.consume());
				
				if(error2.showAndWait().get() == ButtonType.OK)
				{
					error2.hide();
					updateButton.setVisible(false);
					reasonLabel.setVisible(false);
					vitalLabel.setVisible(false);
					patientInfoLabel.setVisible(false);
					reasonField.setVisible(false);
					vitalField.setVisible(false);
					dividor.setVisible(false);
					updateCompleteLabel.setVisible(false);
					nameField.clear();
				}
					
			}	
			else
			{
				patientInfoLabel.setText(update.showPatientMedicalInfo());
				updateButton.setVisible(true);
				patientLabel.setVisible(true);
				reasonLabel.setVisible(true);
				vitalLabel.setVisible(true);
				patientInfoLabel.setVisible(true);
				nameField.setVisible(true);
				reasonField.setVisible(true);
				vitalField.setVisible(true);
				dividor.setVisible(true);
				updateCompleteLabel.setVisible(false);
			}
		}	
			
	}
	
	public void updatePressed(ActionEvent event)
	{
		String visitInfo = reasonField.getText();
		String vitalInfo = vitalField.getText();
		update.updateVisitandMeasurements(visitInfo, vitalInfo);
		patientInfoLabel.setText(update.showPatientMedicalInfo());
		updateCompleteLabel.setVisible(true);
		reasonField.clear();
		vitalField.clear();
	}
	
	public void logOut(ActionEvent event) throws IOException 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Are you Sure?");
		
		if(alert.showAndWait().get() == ButtonType.OK)
		{
			stage = (Stage) scenePane.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Welcome Employee");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.show();
		}
	}
}
