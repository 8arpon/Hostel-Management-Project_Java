package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class AdminDashboard extends Alerts {

    public void initialize() throws IOException {
        RoomDataFile.loadRoomDetails();
        RoomDataFile.availableRooms();
        if(adminPortalContainer != null && adminPortalContainer.getChildren().isEmpty()){
            showAllContainer("AdminHomeDashboard.fxml");
        }
        if(totalRegisteredStudent != null){
            totalRegisteredStudent.setText("" + StudentDataFile.allStudent.size());
        }
        if(totalRooms != null){
            totalRooms.setText("" + RoomDataFile.allRoomList.size());
        }
        if(totalAvailableRoom != null){
            totalAvailableRoom.setText("" + RoomDataFile.availableRoomsList.size());
        }
        if(totalBookedRooms != null){
            totalBookedRooms.setText("" + RoomDataFile.bookedRooms.size());
        }
        if(totalApplications != null){
            totalApplications.setText("" + RoomDataFile.totalIDs.size());
        }
        if(pendingStudents != null){
            pendingStudents.setText("" + RoomDataFile.pendingApplicationsID.size());
        }
    }


    @FXML
    private Button Logout;
    @FXML
    private Button removeStudent;
    @FXML
    private AnchorPane adminPortalContainer;
    @FXML
    private Button studentInformationButton;
    @FXML
    private Button adminRoomApplicationsButton;
    @FXML
    private Button homeButton;
    @FXML
    private ImageView logoImage;
    @FXML
    private  Label totalRegisteredStudent;
    @FXML
    private Label totalRooms;
    @FXML
    private Label totalAvailableRoom;
    @FXML
    private  Label totalBookedRooms;
    @FXML
    private Label totalApplications;
    @FXML
    private Label pendingStudents;
    @FXML
    private Button complainButton;

    public void showAllContainer(String fileName) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            adminPortalContainer.getChildren().clear();
            adminPortalContainer.getChildren().add(root);

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClickComplainShow(ActionEvent event){
        showAllContainer("AdminComplainBoxContainer.fxml");
    }
    @FXML
    protected void onClickStudentRemoveButton(ActionEvent event){
        showAllContainer("AdminRemoveStudent.fxml");
    }
    @FXML
    protected void onClickStudentInformationButton(ActionEvent event){
        showAllContainer("AdminStudentInformationShow.fxml");
    }
    @FXML
    protected void onClickApplicationsButton(ActionEvent event){
        showAllContainer("AdminRoomApplications.fxml");
    }
    @FXML
    protected void onClickHomeButton(ActionEvent event){
        showAllContainer("AdminHomeDashboard.fxml");
    }
    @FXML
    protected void onClickLogoutButton(ActionEvent event) throws IOException {

        String title = "Log Out";
        String context = "Are you sure? ";
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