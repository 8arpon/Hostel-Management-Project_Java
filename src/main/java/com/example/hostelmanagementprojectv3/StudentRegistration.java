package com.example.hostelmanagementprojectv3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class StudentRegistration {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField studentID;
    @FXML
    private TextField department;
    @FXML
    private TextField mobile;
    @FXML
    private Button studentRegButton;
    @FXML
    private Button studentLoginButton;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private CheckBox termsAndConditionCheckBox;
    @FXML
    private Hyperlink termsAndConditionURL;
    @FXML
    private Label multipleID;

    @FXML
    protected void onClickCheckBox(ActionEvent event){
        studentRegButton.setDisable(!termsAndConditionCheckBox.isSelected());
    }

    @FXML
    protected void onClickTermsAndConditionURL(ActionEvent event) {
        try{
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI("https://docs.google.com/document/d/1IIAw3NgDXZo7WlqfZYuqSoyCOivxEbvTJuZziiRwXhs/edit?usp=sharing"));
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    String gender;
    @FXML
    protected void onClickSubmission(ActionEvent event){
        try {
            String fName = firstName.getText();
            String lName = lastName.getText();
            String sID = studentID.getText();
            String dept = department.getText();
            String mo = mobile.getText();
            String stdName = fName + " " + lName;

            if(radioMale.isSelected()){
                gender = radioMale.getText();
            }
            else if(radioFemale.isSelected()){
                gender = radioFemale.getText();
            }

            int flag = 0;
            for(Student st : StudentDataFile.allStudent){
                if(st.getStudentID().equals(sID)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                multipleID.setText("Already have an account with that ID.");
            }
            else {
                Student student = new Student(stdName, sID, dept, mo, gender);
                StudentDataFile.allStudent.add(student);
                StudentDataFile.addStudent();
                StudentDataFile.tempStudentData = student;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("studentSetPassword.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 920, 650);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClickLoginButton(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentLogin.fxml"));
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
}
