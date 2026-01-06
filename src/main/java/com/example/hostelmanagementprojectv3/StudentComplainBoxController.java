package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;

public class StudentComplainBoxController extends Alerts {
    @FXML
    private Label stName;
    @FXML
    private Label stID;
    @FXML
    private TextArea message;
    @FXML
    private Button submitButton;
    @FXML
    private Label informationMessage;
    @FXML
    private Label adminReviewInfo;
    @FXML
    private Label adminReview;

    @FXML
    private void initialize(){
        try{
            System.out.println("List was empty");
            ComplainDataFile.loadComplainBox();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(stName != null){
            stName.setText(StudentDataFile.currentLoggedInStudent.getStudentName());
        }
        if(stID != null){
            stID.setText(StudentDataFile.currentLoggedInStudent.getStudentID());
        }
        if(informationMessage != null){
            informationMessage.setText("NOTE: You can't send another complain until the Admin reviews the previous Complain.");
        }
        for(Complain com : ComplainDataFile.complains){
            if(com.getAdminReview().equals("Pending") && com.getStID().equals(StudentDataFile.currentLoggedInStudent.getStudentID())){
                message.setDisable(true);
                submitButton.setDisable(true);
                if(informationMessage != null){
                    informationMessage.setText("You have a complain in our database. Admin will review it. You can't complain another one before the previous issue is solved.");
                }
            }
            else if(!com.getAdminReview().equals("Pending") && com.getStID().equals(StudentDataFile.currentLoggedInStudent.getStudentID())){
                if(adminReviewInfo != null) {
                    adminReviewInfo.setText("Admin's Reply of your Complain.");
                }
                if(adminReview != null){
                    adminReview.setText(com.getAdminReview());
                }
            }
        }
    }

    @FXML
    protected void clickOnSubmit(ActionEvent event){
        boolean flag = false;
        Complain comp;
        for(Complain com : ComplainDataFile.complains){
            if(com.getAdminReview().equals("Pending") && com.getStID().equals(StudentDataFile.currentLoggedInStudent.getStudentID())){
                flag = true;
                break;
            }
        }

        if(!flag) {
            String m = message.getText();
            Complain com = new Complain(StudentDataFile.currentLoggedInStudent.getStudentID(), m, "Pending");
            ComplainDataFile.complains.add(com);
            ComplainDataFile.addComplainToFile();

            String title = "Complain Box";
            String context = "Complain Received. Admin will review it soon.";
            informationAlerts(title, context);

            message.clear();
            message.setDisable(true);
            submitButton.setDisable(true);
            if(informationMessage != null){
                informationMessage.setText("You have a complain in our database. Admin will review it. You can't complain another one before the previous issue is solved.");
            }
        }

        else if(flag){

            message.clear();
            message.setDisable(true);
            submitButton.setDisable(true);
            String title = "Complain Box";
            String context = "Your complain has been taken already. Please wait for the response by the Admin.";
            informationAlerts(title, context);

        }
    }
}
