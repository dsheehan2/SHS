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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerTreatPatient 
{
	@FXML
	private Label searchLabel;
	@FXML
	private Label ApatientLabel;
	@FXML
	private Label treatmentLabel;
	@FXML
	private Label prescriptionLabel;
	@FXML
	private Label completeLabel;
	@FXML
	private Button logoutButton;
	@FXML
	private Button okButton;
	@FXML
	private Button confirmButton;
	@FXML
	private AnchorPane scenePane;
	@FXML
	private TextField patientNameField;
	@FXML
	private TextField treatmentField;
	@FXML
	private TextField prescriptionField;
	@FXML
	private Line dividor;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private TreatPatient item;
	
	public void okPressed(ActionEvent event) throws IOException
	{
		item = new TreatPatient(LogInController.getEmployeeID(), patientNameField.getText().trim());
		
		if(item.validateEmployeeID() == true)
		{
			if(item.checkExistingTreatement() == false)
			{
				Alert error = new Alert(AlertType.CONFIRMATION);
				error.setTitle("ERROR");
				error.setHeaderText("You already have a recent treatment/prescription for this patient!");
				error.setContentText("Would you like to change this info?");
				
				ButtonType okButton = new ButtonType("Yes", ButtonData.YES);
				ButtonType noButton = new ButtonType("No", ButtonData.NO);
				error.getButtonTypes().setAll(okButton, noButton);
				
				Window alertWindow = error.getDialogPane().getScene().getWindow();
				alertWindow.setOnCloseRequest(e -> e.consume());
				
				if(error.showAndWait().get() == okButton) //same as outer else --> yes
				{
					ApatientLabel.setText(item.showPatientMedicalInfo());
					ApatientLabel.setVisible(true);
					dividor.setVisible(true);
					treatmentLabel.setVisible(true);
					prescriptionLabel.setVisible(true);
					treatmentField.setVisible(true);
					prescriptionField.setVisible(true);
					confirmButton.setVisible(true);
					completeLabel.setVisible(false);
					treatmentField.clear();
					prescriptionField.clear();
				}
				else  //no
				{
					patientNameField.clear();
					ApatientLabel.setVisible(false);
					dividor.setVisible(false);
					treatmentLabel.setVisible(false);
					prescriptionLabel.setVisible(false);
					treatmentField.setVisible(false);
					prescriptionField.setVisible(false);
					confirmButton.setVisible(false);
					completeLabel.setVisible(false);
					treatmentField.clear();
					prescriptionField.clear();
				}
			}
			else //no patient info --> yes
			{
				//create search algo in if - statement
				if(item.checkPatientExists() == true)
				{
					ApatientLabel.setText(item.showPatientMedicalInfo());
					ApatientLabel.setVisible(true);
					dividor.setVisible(true);
					treatmentLabel.setVisible(true);
					prescriptionLabel.setVisible(true);
					treatmentField.setVisible(true);
					prescriptionField.setVisible(true);
					confirmButton.setVisible(true);
					completeLabel.setVisible(false);
					treatmentField.clear();
					prescriptionField.clear();
				}
				else
				{
					Alert anError = new Alert(AlertType.ERROR);
					anError.setTitle("ERROR");
					anError.setHeaderText("Patient Not Found!");
					anError.setContentText("Please Go Back or Re-Enter.");

					Window alertWindow3 = anError.getDialogPane().getScene().getWindow();
					alertWindow3.setOnCloseRequest(e -> e.consume());
			
					if(anError.showAndWait().get() == ButtonType.OK)
					{
						anError.hide();
						patientNameField.clear();
						ApatientLabel.setVisible(false);
						dividor.setVisible(false);
						treatmentLabel.setVisible(false);
						prescriptionLabel.setVisible(false);
						treatmentField.setVisible(false);
						prescriptionField.setVisible(false);
						confirmButton.setVisible(false);
						completeLabel.setVisible(false);
						treatmentField.clear();
						prescriptionField.clear();
					}
						
				}
				
			}
		}
		else
		{
			Alert error2 = new Alert(AlertType.ERROR);
			error2.setTitle("ERROR");
			error2.setHeaderText("You do not have authorization to Update!");
			error2.setContentText("Please Go Back.");

			Window alertWindow2 = error2.getDialogPane().getScene().getWindow();
			alertWindow2.setOnCloseRequest(e -> e.consume());
	
			if(error2.showAndWait().get() == ButtonType.OK)
				error2.hide();
		}
	}
	
	public void confirmPressed(ActionEvent event) throws IOException
	{
		String per = prescriptionField.getText().trim();
		String treat = treatmentField.getText().trim();
		
		item.updateTreatmentandPrescription(treat, per);
		ApatientLabel.setText(item.showPatientMedicalInfo());
		completeLabel.setVisible(true);
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
