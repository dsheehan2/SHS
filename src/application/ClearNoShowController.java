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

public class ClearNoShowController 
{
	@FXML
	private Button backButton;
	@FXML
	private Button confirmButton;
	@FXML
	private Label nameLabel;
	@FXML
	private TextField nameField;
	@FXML
	private Label completeLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void confirmPressed(ActionEvent event) throws IOException
	{
		if(ClearNoShow.checkPatientExists(nameField.getText().trim()) == true)
		{
			ClearNoShow.RemoveNoShow("Appointment.txt", nameField.getText().trim(), 1, ",");
			ClearNoShow.AddNameToNoShow(nameField.getText().trim());
			completeLabel.setVisible(true);
			nameField.clear();
		}
		else
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("No Patient Found!");
			error.setContentText("Please Try Again.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
			
			if(error.showAndWait().get() == ButtonType.OK)
			{
				error.hide();
				completeLabel.setVisible(false);
				nameField.clear();
			}
		}
		
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
