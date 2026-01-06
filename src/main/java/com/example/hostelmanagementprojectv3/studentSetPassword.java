package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

public class studentSetPassword extends Alerts {

    @FXML
    private PasswordField studentLoginPassword;
    @FXML
    private PasswordField studentLoginConfirmPassword;
    @FXML
    private Label studentSetPasswordIDLogin;
    @FXML
    private TextField studentUserNameID;
    @FXML
    private Button studentCreateAccount;
    @FXML
    private Label incorrectPassTry;

    @FXML
    public void initialize(){
        if(studentSetPasswordIDLogin != null){
            studentSetPasswordIDLogin.setText(StudentDataFile.tempStudentData.getStudentID());
        }
        if(studentUserNameID != null){
            studentUserNameID.setText(StudentDataFile.tempStudentData.getStudentID());
        }
        String title = "Username Alert";
        String context = "Your ID" + " [ " + StudentDataFile.tempStudentData.getStudentID() + " ] will be your Username";
        informationAlerts(title, context);
    }

    @FXML
    protected void createAccount(ActionEvent event){
        String password  = studentLoginPassword.getText();
        String confirmPassword = studentLoginConfirmPassword.getText();
        String stID = StudentDataFile.tempStudentData.getStudentID();

        if(password.isBlank() || confirmPassword.isBlank()){
            incorrectPassTry.setText("Password fields are blank.");
            incorrectPassTry.setStyle("-fx-text-fill: red");
            studentLoginPassword.setStyle("-fx-border-color: red");
            studentLoginConfirmPassword.setStyle("-fx-border-color: red");
        }


        if(password.equals(confirmPassword) && (!password.isBlank()) && (!confirmPassword.isBlank())  ) {
            try {
                StudentIDPassword st = new StudentIDPassword(stID, password);
                StudentDataFile.passID.add(st);
                StudentDataFile.addPasswordFile();

                for(Student currentSt : StudentDataFile.allStudent){
                    if(currentSt.getStudentID().equals(stID)){
                        StudentDataFile.currentLoggedInStudent = currentSt;
                        break;
                    }
                }
                for(Rooms currentStRoom : RoomDataFile.studentRoomDetails){
                    if (currentStRoom.getStudentID().equals(stID)) {
                        RoomDataFile.currentStudentRoomDetails = currentStRoom;
                        break;
                    }
                }

                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
                Parent root = loader1.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 920, 650);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        else if((!password.isBlank()) && (!confirmPassword.isBlank())){
            if(incorrectPassTry != null){
                incorrectPassTry.setText("Password fields do not match.");
                incorrectPassTry.setStyle("-fx-text-fill: red");
                studentLoginPassword.setStyle("-fx-border-color: red");
                studentLoginConfirmPassword.setStyle("-fx-border-color: red");
            }
        }
    }
}
