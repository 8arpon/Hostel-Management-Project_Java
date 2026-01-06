package com.example.hostelmanagementprojectv3;

import com.example.hostelmanagementprojectv3.AlertWorkspace.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminComplainBoxController extends Alerts {


    @FXML
    private VBox toShowComplains;
    @FXML
    private Label showComplainsLabel;
    @FXML
    private Label studentID;
    @FXML
    private TextField stID;
    @FXML
    private TextArea adminReview;
    @FXML
    private Button submitButton;
    @FXML
    private Label totalComplains;

    @FXML
    protected void initialize() throws IOException {

        if(totalComplains != null){
            totalComplains.setText("" + ComplainDataFile.pendingComplainIDs.size());
        }
        if(toShowComplains != null && toShowComplains.getChildren().isEmpty()){
            showAllComplains();
        }
    }

    @FXML
    protected void showAllComplains() throws IOException {
        toShowComplains.getChildren().clear();

        for(Complain complain : ComplainDataFile.complains){
            if(complain.getAdminReview().equals("Pending")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminShowAllComplainsContainer.fxml"));
                Node card = loader.load();

                AdminComplainBoxController controller = loader.getController();
                controller.studentID.setText(complain.getStID());
                controller.showComplainsLabel.setText(complain.getComplainText());

                toShowComplains.getChildren().add(card);
            }
        }
    }

    @FXML
    protected void onClickSubmitButton(ActionEvent event){
        String ID = stID.getText();
        String review = adminReview.getText();
        boolean flag = false;
        for(Complain complain : ComplainDataFile.complains){
            if(complain.getStID().equals(ID) && complain.getAdminReview().equals("Pending")){
                complain.setAdminReview(review);
                ComplainDataFile.addComplainToFile();
                String title = "Complain Review";
                String context = "Your Review has been sent to the student.";
                informationAlerts(title, context);
                flag = true;
                stID.clear();
                adminReview.clear();
                break;
            }
        }
        if(!flag){
            String title = "Complain Review";
            String context = "ID mismatch or you already reviewed it.";
            errorAlerts(title, context);
        }

    }
}
