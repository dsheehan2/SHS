package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController 
{
	@FXML
	private Label titleLabel;
	@FXML
	private Label logInLabel;
	@FXML
	private Label passwordLabel;
	@FXML 
	private TextField logInField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button logInButton;
	@FXML
	private Label wrongIDLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private static int userID;
	private static String passwordID;
	
	public void logInByID(ActionEvent event) throws IOException 
	{
		try
		{
			userID = Integer.parseInt(logInField.getText().trim());
			passwordID = passwordField.getText().trim();
			logInField.clear();
			passwordField.clear();
			String theType = searchEmployee(userID, passwordID, Hashing.generate(passwordID));
			wrongIDLabel.setVisible(false);
			
			switch(theType)
			{
			case "Staff":
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StaffSelection.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Staff Interface");
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.show();
				break;
			case "Doctor":
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("TreatPatient.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Doctor Interface");
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.show();
				break;
			case "Nurse":
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("UpdateMeasurement.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Nurse Interface");
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.show();
				break;
			}
		}
		catch(Exception e) //catch nulls and out of bounds for incorrect ID and/or password from theType variable
		{ 
			wrongIDLabel.setText("ID/Password Incorrect");
			wrongIDLabel.setVisible(true);
		}	
	}
	
	//small algorithm -> returns type of Employee STRING
	public String searchEmployee(int id, String pass, byte[] ary) throws Exception
	{
		BufferedReader read = null;
    	BufferedReader prev = null;
        String userID = "", passID = "", type = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("EmployeeAccount.txt")));
            prev = new BufferedReader(new FileReader(new File("EmployeeAccount.txt")));
            while((userID = read.readLine()) != null)
            {
            	if(userID.contains(Integer.toString(id).trim()))
            	{
            		passID = read.readLine();
            		break;
            	}
            	type = prev.readLine();
            }
            read.close();
            prev.close();
        }
        catch(FileNotFoundException e) {System.out.println("File not Found given: " + e.getMessage());}
        catch(Exception e){System.out.println("Other Error Occured: " + e.getMessage());}
    	
        if(passID.substring(10).equals(pass) && (Hashing.verify(ary, pass)))
        	return type.substring(6);
        else
        	return null;
	}
	
	public static int getEmployeeID()
	{
		return userID;
		
	}
	
	public static String getPassword()
	{
		return passwordID;
	}
}
