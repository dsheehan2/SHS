package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller 
{
	
	@FXML
	private Button logoutButton;
	@FXML
	private Button apptButton;
	@FXML
	private Button medButton;
	@FXML
	private Button clearButton;
	@FXML
	private AnchorPane scenePane;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
	
	public void payMedFee(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("PayMedicalFee.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Pay Medical Fee");
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void makeAppt(ActionEvent event) throws IOException
	{
			root = FXMLLoader.load(getClass().getResource("MakeAppointment.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Make Appointment");
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
	}
	
	public void clearNoShow(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("ClearNoShow.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Clear No Show Patients");
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	//goes to authenticate
	public void checkIn(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Authorize");
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}