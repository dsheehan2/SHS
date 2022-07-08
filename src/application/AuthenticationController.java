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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;

public class AuthenticationController
{
	@FXML
	private Button backButton;
	@FXML
	private Label authorizeLabel;
	@FXML
	private Button authorizeButton;
	@FXML
	private PasswordField authenticateField;
	
	private Authentication authy;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void authorize(ActionEvent event) throws IOException
	{
		authy = new Authentication(LogInController.getEmployeeID());
		
		if(authy.pinAuth(Integer.parseInt(authenticateField.getText().trim())))
		{
			root = FXMLLoader.load(getClass().getResource("CheckInPatient.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Check In Patient");
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setHeaderText("Incorrect Pin");
			error.setContentText("Please Go Back or Enter your correct Pin.");

			Window alertWindow = error.getDialogPane().getScene().getWindow();
			alertWindow.setOnCloseRequest(e -> e.consume());
	
			if(error.showAndWait().get() == ButtonType.OK)
			{
				error.hide();	
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