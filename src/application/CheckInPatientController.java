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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CheckInPatientController 
{
	@FXML
	private Button backButton;
	@FXML
	private Button okButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button newButton;
	@FXML
	private Label nameLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label apptLabel;
	@FXML
	private Label appointmentBox;
	@FXML
	private Label patientInfoBox;
	@FXML
	private Label patientInfoLabel;
	@FXML
	private TextField nameField;
	@FXML
	private TextField dateField;
	@FXML
	private Line line1;
	@FXML
	private Line line2;
	@FXML
	private Line line3;
	
	//for new patient / existing update
	@FXML
	private Label newPatientLabel;
	@FXML
	private Label newPatientIDLabel;
	@FXML
	private Label newAddressLabel;
	@FXML
	private Label newPhoneLabel;
	@FXML
	private Label newEmailLabel;
	@FXML
	private Label newSSNLabel;
	@FXML
	private Label newInsuranceLabel;
	@FXML
	private TextField newPatientIDField;
	@FXML
	private TextField newAddressField;
	@FXML
	private TextField newPhoneField;
	@FXML
	private TextField newEmailField;
	@FXML
	private PasswordField newSSNField;
	@FXML
	private TextField newInsuranceField;
	@FXML
	private Button createButton;
	@FXML
	private Label completeLabel;
	
	//for old patient, already in the system
	@FXML
	private Label changeInfoLabel;
	@FXML
	private Button noButton;
	@FXML
	private Button changeButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private CheckInPatient checkIn;
	
	
	public void okPressed(ActionEvent event) throws IOException
	{
		String name = nameField.getText().trim();
		String birthday = dateField.getText().trim();	
		checkIn = new CheckInPatient(name, birthday, LogInController.getEmployeeID());
		
		if(checkIn.validateEmployeeID() == false)
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("You do not have authorization to Check In!");
			error.setContentText("Please Go Back.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
	
			if(error.showAndWait().get() == ButtonType.OK)
			{
				error.hide();
				newPatientLabel.setVisible(false);
				newPatientIDLabel.setVisible(false);
				newAddressLabel.setVisible(false);
				newPhoneLabel.setVisible(false);
				newEmailLabel.setVisible(false);
				newSSNLabel.setVisible(false);
				newInsuranceLabel.setVisible(false);
				newPatientIDField.setVisible(false);	
				newAddressField.setVisible(false);
				newPhoneField.setVisible(false);
				newEmailField.setVisible(false);
				newSSNField.setVisible(false);
				newInsuranceField.setVisible(false);	
				createButton.setVisible(false);
				completeLabel.setVisible(false);
				newButton.setVisible(false);
				updateButton.setVisible(false);
				changeInfoLabel.setVisible(false);
				noButton.setVisible(false);
				changeButton.setVisible(false);
				nameField.clear();
				dateField.clear();
				newPatientIDField.clear();	
				newAddressField.clear();	
				newPhoneField.clear();	
				newEmailField.clear();	
				newSSNField.clear();	
				newInsuranceField.clear();	
			}				
		}
		else
		{
			if(checkIn.checkAppointment() == true)
			{
				appointmentBox.setText(checkIn.displayAppointment());
				appointmentBox.setVisible(true);
				apptLabel.setVisible(true);
				line1.setVisible(true);
				line2.setVisible(true);
				line3.setVisible(true);
				newPatientLabel.setVisible(false);
				newPatientIDLabel.setVisible(false);
				newAddressLabel.setVisible(false);
				newPhoneLabel.setVisible(false);
				newEmailLabel.setVisible(false);
				newSSNLabel.setVisible(false);
				newInsuranceLabel.setVisible(false);
				newPatientIDField.setVisible(false);	
				newAddressField.setVisible(false);
				newPhoneField.setVisible(false);
				newEmailField.setVisible(false);
				newSSNField.setVisible(false);
				newInsuranceField.setVisible(false);	
				createButton.setVisible(false);
				completeLabel.setVisible(false);
				newButton.setVisible(false);
				updateButton.setVisible(false);
				changeInfoLabel.setVisible(false);
				noButton.setVisible(false);
				changeButton.setVisible(false);
				newPatientIDField.clear();	
				newAddressField.clear();	
				newPhoneField.clear();	
				newEmailField.clear();	
				newSSNField.clear();	
				newInsuranceField.clear();
	
				
				if(checkIn.checkPatientExists() == true)
				{
					patientInfoBox.setText(checkIn.displayPatientInformation());
					patientInfoBox.setVisible(true);
					patientInfoLabel.setVisible(true);
					updateButton.setVisible(true);
					noButton.setVisible(true);
				}
				else
				{
					patientInfoBox.setText("Patient is New,\nPlease Create Account!");
					patientInfoBox.setVisible(true);
					newButton.setVisible(true);
				}
			}
			else
			{
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("ERROR");
				error.setHeaderText("Patient does not have an Appointment!");
				error.setContentText("Please try agian or exit!");
	
				Window alertWindow = error.getDialogPane().getScene().getWindow();
				alertWindow.setOnCloseRequest(e -> e.consume());
		
				if(error.showAndWait().get() == ButtonType.OK)
				{
					error.hide();
					appointmentBox.setText(checkIn.displayAppointment());
					appointmentBox.setVisible(false);
					apptLabel.setVisible(false);
					line1.setVisible(false);
					line2.setVisible(false);
					line3.setVisible(false);
					updateButton.setVisible(false);
					patientInfoLabel.setVisible(false);
					patientInfoBox.setVisible(false);
					newPatientLabel.setVisible(false);
					newPatientIDLabel.setVisible(false);
					newAddressLabel.setVisible(false);
					newPhoneLabel.setVisible(false);
					newEmailLabel.setVisible(false);
					newSSNLabel.setVisible(false);
					newInsuranceLabel.setVisible(false);
					newPatientIDField.setVisible(false);	
					newAddressField.setVisible(false);
					newPhoneField.setVisible(false);
					newEmailField.setVisible(false);
					newSSNField.setVisible(false);
					newInsuranceField.setVisible(false);	
					createButton.setVisible(false);
					completeLabel.setVisible(false);
					newButton.setVisible(false);
					updateButton.setVisible(false);
					changeInfoLabel.setVisible(false);
					noButton.setVisible(false);
					nameField.clear();
					dateField.clear();
					newPatientIDField.clear();	
					newAddressField.clear();	
					newPhoneField.clear();	
					newEmailField.clear();	
					newSSNField.clear();	
					newInsuranceField.clear();
				}
					
			}
		}
		
	}
	
	public void updatePressed(ActionEvent event) throws IOException
	{
		changeButton.setVisible(true);
		changeInfoLabel.setVisible(true);

		newPatientLabel.setVisible(false);
		createButton.setVisible(false);
		
		newPatientIDLabel.setVisible(false);
		newPatientIDField.setVisible(false);
		
		newPatientIDField.clear();	
		newAddressField.clear();	
		newPhoneField.clear();	
		newEmailField.clear();	
		newSSNField.clear();	
		newInsuranceField.clear();
		
		newAddressLabel.setVisible(true);
		newPhoneLabel.setVisible(true);
		newEmailLabel.setVisible(true);
		newSSNLabel.setVisible(true);
		newInsuranceLabel.setVisible(true);
		newAddressField.setVisible(true);
		newPhoneField.setVisible(true);
		newEmailField.setVisible(true);
		newSSNField.setVisible(true);
		newInsuranceField.setVisible(true);	
	}
	
	public void changePressed(ActionEvent event) throws IOException
	{
		String address = newAddressField.getText();
		String phone = newPhoneField.getText();
		String email = newEmailField.getText();
		String ssn = newSSNField.getText();
		String insurance = newInsuranceField.getText();
		
		checkIn.changeAllFiles(address, phone, email, ssn, insurance); 
		completeLabel.setVisible(true);
		patientInfoBox.setText(checkIn.displayPatientInformation());
		
		checkIn.delApp();
	}
	
	public void newPressed(ActionEvent event) throws IOException
	{
		newPatientLabel.setVisible(true);
		createButton.setVisible(true);
		
		changeButton.setVisible(false);
		changeInfoLabel.setVisible(false);
		
		newPatientIDField.clear();	
		newAddressField.clear();	
		newPhoneField.clear();	
		newEmailField.clear();	
		newSSNField.clear();	
		newInsuranceField.clear();
		
		patientInfoLabel.setVisible(true);
		newPatientIDLabel.setVisible(true);
		newAddressLabel.setVisible(true);
		newPhoneLabel.setVisible(true);
		newEmailLabel.setVisible(true);
		newSSNLabel.setVisible(true);
		newInsuranceLabel.setVisible(true);
		newPatientIDField.setVisible(true);	
		newAddressField.setVisible(true);
		newPhoneField.setVisible(true);
		newEmailField.setVisible(true);
		newSSNField.setVisible(true);
		newInsuranceField.setVisible(true);	
			
	}
	
	public void createPressed(ActionEvent event) throws IOException
	{
		String patientID = newPatientIDField.getText();
		String address = newAddressField.getText();
		String phone = newPhoneField.getText();
		String email = newEmailField.getText();
		String ssn = newSSNField.getText();
		String insurance = newInsuranceField.getText();
		
		checkIn.updateAllFiles(patientID, address, phone, email, ssn, insurance);
		completeLabel.setVisible(true);
		patientInfoBox.setText(checkIn.displayPatientInformation());
		
		checkIn.delApp();
	}
	
	public void noUpdate(ActionEvent event) throws IOException
	{
		completeLabel.setVisible(true);
		
		newPatientLabel.setVisible(false);
		newPatientIDLabel.setVisible(false);
		newAddressLabel.setVisible(false);
		newPhoneLabel.setVisible(false);
		newEmailLabel.setVisible(false);
		newSSNLabel.setVisible(false);
		newInsuranceLabel.setVisible(false);
		newPatientIDField.setVisible(false);	
		newAddressField.setVisible(false);
		newPhoneField.setVisible(false);
		newEmailField.setVisible(false);
		newSSNField.setVisible(false);
		newInsuranceField.setVisible(false);	
		createButton.setVisible(false);
		changeInfoLabel.setVisible(false);
		changeButton.setVisible(false);
		newPatientIDField.clear();	
		newAddressField.clear();	
		newPhoneField.clear();	
		newEmailField.clear();	
		newSSNField.clear();	
		newInsuranceField.clear();
		
		checkIn.delApp();
	}
	
	
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
}
