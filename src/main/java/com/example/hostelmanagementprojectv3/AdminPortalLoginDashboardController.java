package com.example.hostelmanagementprojectv3;

import javafx.collections.FXCollections;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPortalLoginDashboardController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button adminLoginButton;
    @FXML
    private Label errorMessage;
    @FXML
    private Button adminPortalLoginBackPage;

    @FXML
    protected void onClickBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstDisplayAdminStudent.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 920, 650);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    protected void onClickAdminLoginButton(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(username.equals("Arpon") && password.equals("Arpon")){
            try{

                RoomDataFile.loadRoomDetails();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 920, 650);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            if(username.isBlank()){
                usernameField.setStyle("-fx-border-color: red");
                errorMessage.setText("Invalid Username");
                errorMessage.setStyle("-fx-text-fill: red");
            }
            else if(password.isBlank()){
                passwordField.setStyle("-fx-border-color: red");
                errorMessage.setText("Invalid Password");
                errorMessage.setStyle("-fx-text-fill: red");
            }
            else {
                usernameField.setStyle("-fx-border-color: red");
                passwordField.setStyle("-fx-border-color: red");
                errorMessage.setText("Username or Password is incorrect.");
                errorMessage.setStyle("-fx-text-fill: red");
            }
        }
    }
}
