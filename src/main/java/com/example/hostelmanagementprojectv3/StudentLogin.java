package com.example.hostelmanagementprojectv3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentLogin {
    @FXML
    private TextField studentLoginUsername;
    @FXML
    private PasswordField studentLoginPassword;
    @FXML
    private Button studentLoginButton;
    @FXML
    private Label incorrectLoginMessage;
    @FXML
    private Button dontHaveAnAccount;


    @FXML
    protected void onClickDontHaveAnAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentRegistration.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 920, 650);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onClickStudentLoggedInDashboard(ActionEvent event) throws IOException {
        StudentDataFile.loadPassword();
        String realPass = "";
        String realId = "";
        StudentIDPassword St = null;
        String username = studentLoginUsername.getText();
        String password = studentLoginPassword.getText();

        for(StudentIDPassword st : StudentDataFile.passID){
            if(st.getStudentId().equals(username)){
                realPass = st.getStudentPassword();
                realId = st.getStudentId();
                St = st;
            }
        }

        if(password.equals(realPass) && (!username.isBlank()) && (!password.isBlank())) {
            try {
                for(Student st : StudentDataFile.allStudent){
                    if(st.getStudentID().equals(username)){
                        StudentDataFile.currentLoggedInStudent = st;
                    }
                }
                for(Rooms currentStRoom : RoomDataFile.studentRoomDetails){
                    if (currentStRoom.getStudentID().equals(username)) {
                        RoomDataFile.currentStudentRoomDetails = currentStRoom;
                        break;
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
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
        else if(username.isBlank() && password.isBlank()){
            incorrectLoginMessage.setText("Both fields are empty");
            incorrectLoginMessage.setStyle("-fx-text-fill: red");
            studentLoginPassword.setStyle("-fx-border-color: red");
            studentLoginUsername.setStyle("-fx-border-color: red");
        }
        else if(username.isBlank()){
            incorrectLoginMessage.setText("Username field is empty.");
            incorrectLoginMessage.setStyle("-fx-text-fill: red");
            studentLoginUsername.setStyle("-fx-border-color: red");
        }
        else if(password.isBlank()){
            incorrectLoginMessage.setText("Password field is empty");
            incorrectLoginMessage.setStyle("-fx-text-fill: red");
            studentLoginPassword.setStyle("-fx-border-color: red");
        }
        else if(!password.equals(realPass)){
            incorrectLoginMessage.setText("Username or Password is  incorrect");
            incorrectLoginMessage.setStyle("-fx-text-fill: red");
            studentLoginUsername.setStyle("-fx-border-color: red");
            studentLoginPassword.setStyle("-fx-border-color: red");
        }
    }
}
