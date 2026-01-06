package com.example.hostelmanagementprojectv3;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        StudentDataFile.loadStudent();
        RoomDataFile.loadAllRooms();
        RoomDataFile.loadRoomDetails();
        RoomDataFile.availableRooms();
        StudentDataFile.loadPassword();
        ComplainDataFile.loadComplainBox();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FirstDisplayAdminStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 650);
        stage.setTitle("The Nexus Home");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
