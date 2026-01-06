package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;


public class AdminRoomApplicationController extends Alerts {

    @FXML
    public void initialize() throws IOException {

        if(showPendingApprovalDetailsVBox != null && showPendingApprovalDetailsVBox.getChildren().isEmpty()){
            showPendingDetails();
        }
        if(availableRoomsVBox != null && availableRoomsVBox.getChildren().isEmpty()){
            showAvailableRooms();
        }
        if(totalPendingStudents != null){
            totalPendingStudents.setText(" " + RoomDataFile.pendingApplicationsID.size());
        }
        if(totalAvailableRooms != null){
            totalAvailableRooms.setText(" " + RoomDataFile.availableRoomsList.size());
        }
    }

    @FXML
    private TextField searchByID;
    @FXML
    private Button searchButton;
    @FXML
    private VBox showPendingApprovalDetailsVBox;
    @FXML
    private Label pendingStName;
    @FXML
    private Label pendingStID;
    @FXML
    private Label totalPendingStudents;
    @FXML
    private VBox availableRoomsVBox;
    @FXML
    private Label availableRoomLabel;
    @FXML
    private Label totalAvailableRooms;

    @FXML
    protected void showAvailableRooms() throws IOException{
        availableRoomsVBox.getChildren().clear();

        for(String rooms : RoomDataFile.availableRoomsList){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminRoomApplicationAvailableRoomsShow.fxml"));
            Node card = loader.load();

            AdminRoomApplicationController controller = loader.getController();
            controller.availableRoomLabel.setText("   " + rooms);

            availableRoomsVBox.getChildren().add(card);
        }

    }
    @FXML
    protected void showPendingDetails() throws IOException {
        showPendingApprovalDetailsVBox.getChildren().clear();

        for(String id : RoomDataFile.pendingApplicationsID) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPendingStudentsContainer.fxml"));
            Node card = loader.load();

            AdminRoomApplicationController controller = loader.getController();
            controller.pendingStID.setText("   " + id);

            showPendingApprovalDetailsVBox.getChildren().add(card);

        }
    }

    @FXML
    public void clickOnSearchButton(ActionEvent event) throws IOException {

        String searchingID = searchByID.getText();

        boolean flag = false;
        for(Rooms details : RoomDataFile.studentRoomDetails){
            if(details.getStudentID().equals(searchingID)){
                flag = true;
                break;
            }
        }

        boolean flag2 = false;
        for(Student st : StudentDataFile.allStudent){
            if(st.getStudentID().equals(searchingID)){
                flag2 = true;
                break;
            }
        }

        String studentApprovedID;
        boolean flag3 = false;
        for(String id : RoomDataFile.approvedStudentID){
            if(id.equals(searchingID)){
                studentApprovedID = id;
                flag3 = true;
                break;
            }
        }

        if(searchByID.getText().isBlank()){
            String title = "Invalid Input";
            String context = "Please enter a valid ID";
            errorAlerts(title, context);
        }

        else if(flag && flag2 && !RoomDataFile.availableRoomsList.isEmpty() && RoomDataFile.pendingApplicationsID.contains(searchingID)){
            String title = "Room Confirmation";
            String context = searchingID + " Will get the Room: " + RoomDataFile.availableRoomsList.getFirst() +".";
            boolean result = confirmationAlerts(title, context);

            if(result){
                for(Rooms details : RoomDataFile.studentRoomDetails){
                    if(details.getStudentID().equals(searchingID)){
                        String roomNumber = RoomDataFile.availableRoomsList.getFirst();
                        details.setRoomNumber(roomNumber);
                        String floor = roomNumber.substring(0, 1);
                        details.setFloor(floor);
                        details.setStatus("Approved");

                        RoomDataFile.approvedStudentID.add(searchingID);
                        RoomDataFile.bookedRooms.add(details.getRoomNumber());
                        RoomDataFile.applicationsToFile();

                        RoomDataFile.availableRoomsList.removeFirst();

                        String title1 = "Student Assigned.";
                        String context1 = details.getStudentID() + " has beed Assigned to " + roomNumber;
                        informationAlerts(title1, context1);

                        RoomDataFile.pendingApplicationsID.remove(searchingID);
                        break;
                    }
                }
            }
        }
        else if(!flag2){
            String title = "Room Approval Error";
            String context = "There is no student in our Database with that ID.";
            errorAlerts(title, context);
        }

        else if(flag3){
            String roomNumber = "";
            String floor = "";
            for(Rooms id : RoomDataFile.studentRoomDetails){
                if(id.getStudentID().equals(searchingID)){
                    roomNumber = id.getRoomNumber();
                    floor = id.getFloor();
                    break;
                }
            }
            String title = "Room Approval";
            String context = "The Student with ID: " + searchingID + " is already assigned.\nFloor: " + floor + "\nRoom Number: " + roomNumber;
            warningAlerts(title, context);

        }

        else if(RoomDataFile.pendingApplicationsID.isEmpty()){
            String title = "Room Approval";
            String context = "The Student with that ID " + "(" + searchingID + ")" + " did not apply for a room.";
            errorAlerts(title, context);
        }

    }

}
