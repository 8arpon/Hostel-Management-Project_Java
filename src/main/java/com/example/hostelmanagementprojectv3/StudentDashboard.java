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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class StudentDashboard extends Alerts {
    @FXML
    private AnchorPane showEverythingContainer;
    @FXML
    private Label applicationFee;
    @FXML
    private Button appyRoomButton;
    @FXML
    private Button complainBoxButton;
    @FXML
    private Button homeButton;
    @FXML
    private Label studentID;
    @FXML
    private Label studentName;
    @FXML
    private Label hallFeePerMonth;
    @FXML
    private Button logOut;
    @FXML
    private AnchorPane showRoomDetailsAnchorPane;
    @FXML
    private Label showFloor;
    @FXML
    private Label showRoomNumber;
    @FXML
    private Label showStatus;
    @FXML
    private Label showPendingPosition;
    @FXML
    private Label showPositionText;
    @FXML
    private Button changePassword;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label showApprovePendingMessage;

    public void initialize() throws IOException {
        StudentDataFile.loadStudent();
        StudentDataFile.loadPassword();
        RoomDataFile.loadRoomDetails();

        if(applicationFee != null){
            applicationFee.setText(RoomDataFile.hostelApplicationFee);
        }
        if(studentID != null){
            studentID.setText(StudentDataFile.currentLoggedInStudent.getStudentID() + "    ");
        }
        if(studentName != null){
            studentName.setText(StudentDataFile.currentLoggedInStudent.getStudentName() + "   ");
        }
        if(hallFeePerMonth != null){
            hallFeePerMonth.setText(RoomDataFile.hostelFeePerMonth);
        }
        if(showEverythingContainer != null && showEverythingContainer.getChildren().isEmpty()) {
            RoomDataFile.loadRoomDetails();
            clickOnShowButton("StudentHomeDashboard.fxml");
        }

        if(RoomDataFile.currentStudentRoomDetails != null){
            RoomDataFile.loadRoomDetails();
             if(RoomDataFile.currentStudentRoomDetails.getStatus().equals("Approved")){
                if(showRoomDetailsAnchorPane != null && showRoomDetailsAnchorPane.getChildren().isEmpty()){
                    roomDetailsHomeDashboard("StudentForApprovedRoomShow.fxml");
                }
                if(showFloor != null){
                    showFloor.setText(RoomDataFile.currentStudentRoomDetails.getFloor());
                }
                if(showRoomNumber != null){
                    showRoomNumber.setText(RoomDataFile.currentStudentRoomDetails.getRoomNumber());
                }
                if(showStatus != null){
                    showStatus.setText(RoomDataFile.currentStudentRoomDetails.getStatus());
                }
            }
            else if(RoomDataFile.currentStudentRoomDetails.getStatus().equals("Pending")){
                 if(showRoomDetailsAnchorPane != null && showRoomDetailsAnchorPane.getChildren().isEmpty()){
                    roomDetailsHomeDashboard("StudentApprovedOrPendingContainer.fxml");
                }
                 if(showApprovePendingMessage != null){
                     showApprovePendingMessage.setText("Your Room  application is in the pending List. Wait for the Approval of the Admin.");
                 }
                int position = -1;
                for(String pos : RoomDataFile.pendingApplicationsID){
                    if(pos.equals(RoomDataFile.currentStudentRoomDetails.getStudentID())){
                        position = RoomDataFile.pendingApplicationsID.indexOf(pos) + 1;
                        break;
                    }
                }
                if(showPositionText != null){
                    showPositionText.setText("Your position number:");
                }
                 if (showPendingPosition != null) {
                     showPendingPosition.setText("" + position);
                 }
            }
        }
        else{

            if(showRoomDetailsAnchorPane != null && showRoomDetailsAnchorPane.getChildren().isEmpty()){
                roomDetailsHomeDashboard("StudentApprovedOrPendingContainer.fxml");
            }
            if(showApprovePendingMessage != null){
                showApprovePendingMessage.setText("You  have not applied for a Room yet. Click on the Apply Button and provide all the information to apply.");
            }

        }
    }

    protected void roomDetailsHomeDashboard(String fileName){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            showRoomDetailsAnchorPane.getChildren().clear();
            showRoomDetailsAnchorPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void clickOnShowButton(String fileName){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            showEverythingContainer.getChildren().clear();
            showEverythingContainer.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clickOnApplyRoomButton(ActionEvent event) throws IOException {
        clickOnShowButton("StudentApplyButtonContainer.fxml");
    }
    @FXML
    public void clickOnComplainBox(ActionEvent event){clickOnShowButton("StudentComplainBoxContainer.fxml");}
    @FXML
    public void clickOnHomeButton(ActionEvent event){clickOnShowButton("StudentHomeDashboard.fxml");}
    @FXML
    protected void clickOnChangePassButton(ActionEvent event){clickOnShowButton("StudentChangePassword.fxml");}
    @FXML
    public void clickOnLogOutButton(ActionEvent event) throws IOException {

        String title = "Log Out";
        String context = "Are you sure?";
        boolean result = confirmationAlerts(title, context);


        if(result){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstDisplayAdminStudent.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 920, 650);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }

    }
}
