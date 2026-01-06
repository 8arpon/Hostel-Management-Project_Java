package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class StudentApplyButtonController extends Alerts {
    @FXML
    private Button studentApplyButton;
    @FXML
    private Label monthlyHallFee;
    @FXML
    private Label hallApplicationFee;
    @FXML
    private Label approvedMessage;
    @FXML
    private Button payButton;

    @FXML
    protected void initialize(){
        if(monthlyHallFee != null){
            monthlyHallFee.setText(RoomDataFile.hostelFeePerMonth);
        }
        if(hallApplicationFee != null){
            hallApplicationFee.setText(RoomDataFile.hostelApplicationFee);
        }
        if(approvedMessage != null){
            approvedMessage.setText("Note: You have not applied for a Room.\nYour application will be reviewed by the Admin Panel as soon as possible. Your pending serial number will be available on the Home Page.");
        }
        if(RoomDataFile.currentStudentRoomDetails != null){
            if(RoomDataFile.currentStudentRoomDetails.getStudentID().equals(StudentDataFile.currentLoggedInStudent.getStudentID())) {
                studentApplyButton.setDisable(true);
                if(approvedMessage != null){
                    approvedMessage.setText("Your Room Application is in the Pending List. You can see your Serial in the Home Dashboard.");
                }
            }
            if(RoomDataFile.currentStudentRoomDetails.getStatus().equals("Approved")){
                if (approvedMessage != null){
                    approvedMessage.setText("Your Room Application has been Approved. You can check your Room Information on the Home Dashboard.");
                }
            }
        }

    }

    @FXML
    protected void clickOnPayButton(ActionEvent event) throws IOException, URISyntaxException {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.bkash.com/en/products-services/payment"));

        }
    }

    @FXML
    protected void clickOnApply(ActionEvent event) throws IOException {

        String ID = StudentDataFile.currentLoggedInStudent.getStudentID();


        boolean flag = false;
        for(String id : RoomDataFile.totalIDs){
            if(ID.equals(id)){
                flag = true;
                break;
            }
        }
        boolean flag1 = false;
        for(String id : RoomDataFile.approvedStudentID){
            if(id.equals(ID)){
                flag1 = true;
            }
        }
        String Room = "";
        String Floor = "";
        for(Rooms room : RoomDataFile.studentRoomDetails){
            if(room.getStudentID().equals(ID)){
                Room = room.getRoomNumber();
                Floor = room.getFloor();
                break;
            }
        }
        if(flag1){
            String title = "Room Application";
            String context = "Hi " + StudentDataFile.currentLoggedInStudent.getStudentName() + ", Your Room Application has been Approved.\nYour Room Details:\nRoom: " + Room + "\nFloor: " + Floor;
            informationAlerts(title, context);

        }

        else if(flag){
            String title = "Room Application";
            String context = "Hi" + " " + StudentDataFile.currentLoggedInStudent.getStudentName() + ", You have already applied for a room with your ID: " + ID + ". Our Admin will review your application as soon as possible. You can contact us through our Helpline.";
            warningAlerts(title, context);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 920, 650);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }
        else{
            Rooms room = new Rooms(ID, "Null", "Null", "Pending");
            RoomDataFile.studentRoomDetails.add(room);
            RoomDataFile.applicationsToFile();
            String title = "Application Submission";
            String context = "Your Application for a Room has been submitted successfully";
            informationAlerts(title, context);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 920, 650);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

        }


    }

}
