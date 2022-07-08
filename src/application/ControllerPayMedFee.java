package application;

import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerPayMedFee 
{
	@FXML
	private TextField nameField;
	@FXML
	private AnchorPane medFeePane;
	@FXML
	private Button cashButton;
	@FXML
	private Button debitButton;
	@FXML
	private Button creditButton;
	@FXML
	private Label miniPopUp;
	@FXML
	private Label patientLabel;
	@FXML
	private Line dividor;
	@FXML
	private Line dividor2;
	@FXML
	private Label paymentInfo;
	@FXML
	private Label transaction;
	@FXML
	private Label debitCardLabel;
	@FXML
	private Label enterFollowing;
	@FXML
	private Label enterCardNum;
	@FXML
	private Label enterType;
	@FXML
	private Label enterPin;
	@FXML
	private TextField cardField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField pinField;
	@FXML
	private Button confirmButton;
	@FXML
	private Button confirmButton2;
	@FXML
	private Label creditCardLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private PayMedicalFee fee;
	private String namePatient; //just in case
	private int amount;
	
	public void okButtonPressed(ActionEvent event)
	{
		String patientName = nameField.getText().trim();
		fee = new PayMedicalFee(LogInController.getEmployeeID(), patientName); //Change employeeID Later
		namePatient = patientName;
		if(fee.validateEmployeeID() == false)
		{
			transaction.setVisible(false);
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("You do not have authorization for Transaction!");
			error.setContentText("Please Go Back.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
	
			if(error.showAndWait().get() == ButtonType.OK)
				error.hide();
		}
		else		
		{
			if(fee.showPatientPaymenmtInfo() == null)
			{
				transaction.setVisible(false);
				Alert error2 = new Alert(AlertType.ERROR);
				error2.setTitle("ERROR");
				error2.setHeaderText("This Patient is Not Found!");
				error2.setContentText("Please Check Spacing and Capital Letters and Try Again!");
				Window alertWindow = error2.getDialogPane().getScene().getWindow();
				alertWindow.setOnCloseRequest(e -> e.consume());
				
				if(error2.showAndWait().get() == ButtonType.OK)
					error2.hide();
			}	
			else
			{
				transaction.setVisible(false);
				
				cashButton.setVisible(true);
				debitButton.setVisible(true);
				creditButton.setVisible(true);
				
				amount = (int) (Math.random() * 500 + 100);
				miniPopUp.setText("Patient Owes $" + amount);
				miniPopUp.setVisible(true);
				
				patientLabel.setVisible(true);
				dividor.setVisible(true);
				dividor2.setVisible(true);
				paymentInfo.setText(fee.showPatientPaymenmtInfo());
				paymentInfo.setVisible(true);
			}
		}		
	}
	
	public void payCashButtonPressed(ActionEvent event)
	{
		transaction.setVisible(true);
		fee.payByCash(amount);
		paymentInfo.setText(fee.showPatientPaymenmtInfo());
		paymentInfo.setVisible(true);
		
		debitCardLabel.setVisible(false);
		enterFollowing.setVisible(false);
		enterCardNum.setVisible(false);
		enterType.setVisible(false);
		enterPin.setVisible(false);
		cardField.setVisible(false);
		typeField.setVisible(false);
		pinField.setVisible(false);
		confirmButton.setVisible(false);
		
		debitCardLabel.setVisible(false);
		enterFollowing.setVisible(false);
		enterCardNum.setVisible(false);
		enterType.setVisible(false);
		enterPin.setVisible(false);
		cardField.setVisible(false);
		typeField.setVisible(false);
		pinField.setVisible(false);
		confirmButton.setVisible(false);
		
	}
	
	public void payCreditButtonPressed(ActionEvent event)
	{
		transaction.setVisible(false);
		
		creditCardLabel.setVisible(true);
		enterFollowing.setVisible(true);
		enterCardNum.setVisible(true);
		enterType.setVisible(true);
		cardField.setVisible(true);
		typeField.setVisible(true);
		confirmButton2.setVisible(true);
		
		debitCardLabel.setVisible(false);
		enterPin.setVisible(false);
		pinField.setVisible(false);
		confirmButton.setVisible(false);
	}
	
	public void payDebitButtonPressed(ActionEvent event)
	{
		transaction.setVisible(false);
		
		debitCardLabel.setVisible(true);
		enterFollowing.setVisible(true);
		enterCardNum.setVisible(true);
		enterType.setVisible(true);
		enterPin.setVisible(true);
		cardField.setVisible(true);
		typeField.setVisible(true);
		pinField.setVisible(true);
		confirmButton.setVisible(true);
		
		creditCardLabel.setVisible(false);
		confirmButton2.setVisible(false);
	}
	
	public void checkDebit(ActionEvent event)
	{
		String cardNumberInfo = cardField.getText().trim();
		String type = typeField.getText().trim();
		int pin = Integer.parseInt(pinField.getText().trim());
		
		Alert bank = new Alert(AlertType.CONFIRMATION);
		bank.setTitle("Hello Bank Member");
		bank.setHeaderText("A member would like to make an appointment transaction!");
		bank.setContentText("Would you like to confirm: \n Info: " + type + " " + cardNumberInfo + "\n PIN: " + pin);
		
		ButtonType okButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		bank.getButtonTypes().setAll(okButton, noButton);
	
		if(bank.showAndWait().get() == okButton)
		{
			Random ran = new Random();
			fee.payByDebit(amount, pin, type, cardNumberInfo, Integer.toString((100000 + ran.nextInt(900000))));
			bank.hide();
			transaction.setVisible(true);
			paymentInfo.setText(fee.showPatientPaymenmtInfo());
			paymentInfo.setVisible(true);
			
			debitCardLabel.setVisible(false);
			enterFollowing.setVisible(false);
			enterCardNum.setVisible(false);
			enterType.setVisible(false);
			enterPin.setVisible(false);
			cardField.setVisible(false);
			typeField.setVisible(false);
			pinField.setVisible(false);
			confirmButton.setVisible(false);
			
			cardField.clear();
			typeField.clear();
		}
		else
		{
			bank.hide();
			debitCardLabel.setVisible(false);
			enterFollowing.setVisible(false);
			enterCardNum.setVisible(false);
			enterType.setVisible(false);
			enterPin.setVisible(false);
			cardField.setVisible(false);
			typeField.setVisible(false);
			pinField.setVisible(false);
			confirmButton.setVisible(false);
			
			cardField.clear();
			typeField.clear();
			pinField.clear();
		}
			
	}
	
	public void checkCredit(ActionEvent event)
	{
		String cardNumberInfo = cardField.getText().trim();
		String type = typeField.getText().trim();
		fee.payByCredit(amount,  type, cardNumberInfo);
		transaction.setVisible(true);
		paymentInfo.setText(fee.showPatientPaymenmtInfo());
		paymentInfo.setVisible(true);
		
		creditCardLabel.setVisible(false);
		enterFollowing.setVisible(false);
		enterCardNum.setVisible(false);
		enterType.setVisible(false);
		cardField.setVisible(false);
		typeField.setVisible(false);
		confirmButton2.setVisible(false);
		
		cardField.clear();
		typeField.clear();
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
