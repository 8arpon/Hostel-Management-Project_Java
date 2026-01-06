package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class StudentChangePassController extends Alerts {
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button confirmButton;
    @FXML
    private Label changePassMessage;

    @FXML
    protected void clickOnConfirmButton(ActionEvent event) throws IOException {
        String cPass = currentPassword.getText();
        String nPass = newPassword.getText();
        String conPass = confirmPassword.getText();

        if(cPass.isBlank()){
            currentPassword.setStyle("-fx-border-color: red");
            changePassMessage.setText("Current Password Field can't be Empty.");
        }
        else if(nPass.isBlank()){
            newPassword.setStyle("-fx-border-color: red");
            changePassMessage.setText("New Password Field can't be Empty.");

        }
        else if(conPass.isBlank()){
            confirmPassword.setStyle("-fx-border-color: red");
            changePassMessage.setText("Confirm Password Field can't be Empty.");
        }
        else if(!nPass.equals(conPass)){
            changePassMessage.setText("New Password and Confirm Password Does not match.");
        }

        else if(nPass.equals(conPass) && !nPass.isBlank() && !conPass.isBlank()){
            boolean flag = false;
            for(StudentIDPassword pass : StudentDataFile.passID){
                if(pass.getStudentPassword().equals(cPass) && pass.getStudentId().equals(StudentDataFile.currentLoggedInStudent.getStudentID())){
                    pass.setStudentPassword(nPass);
                    pass.setStudentId(StudentDataFile.currentLoggedInStudent.getStudentID());

                    flag = true;
                    break;
                }
            }
            if(flag){
                String title = "Password Change";
                String context = "Are you sure?";
                boolean result = confirmationAlerts(title, context);

                if(result){
                    StudentDataFile.addPasswordFile();
                    changePassMessage.setText("Your Password has been changed.");
                    System.out.println("Pass Changed.");
                }
            }
            else if(!flag){
                currentPassword.setStyle("-fx-border-color: red");
                changePassMessage.setText("Incorrect Current Password.");
            }

        }
    }
}
