package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class AdminRemoveStudent extends Alerts {
    @FXML
    private TextField sIDToRemoveTextField;
    @FXML
    private Button removeButton;
    @FXML
    private Label removalMessage;



    @FXML
    protected void onClickRemoveButton() throws IOException {
        String idToRemove = sIDToRemoveTextField.getText();
        boolean flag = false;

        for(Student st : StudentDataFile.allStudent){
            if(st.getStudentID().equals(idToRemove)){

                String title = "Student Removal Request";
                String context = "Are you sure to remove the Student from the Database?";
                boolean result = confirmationAlerts(title, context);

                if(result) {
                    StudentDataFile.allStudent.remove(st);
                    StudentDataFile.addStudent();

                    for(StudentIDPassword stPass : StudentDataFile.passID){
                        if(stPass.getStudentId().equals(st.getStudentID())){
                            StudentDataFile.passID.remove(stPass);
                            StudentDataFile.addPasswordFile();
                            break;
                        }
                    }
                    for(Rooms room : RoomDataFile.studentRoomDetails){
                        if(room.getStudentID().equals(st.getStudentID())){
                            RoomDataFile.studentRoomDetails.remove(room);
                            RoomDataFile.applicationsToFile();
                        }
                    }
                    removalMessage.setText("The Student with ID: " + st.getStudentID() + " has been removed from the Database.");
                    removalMessage.setStyle("-fx-text-fill: red");
                }
                flag = true;
                break;
            }
        }

    }

}
