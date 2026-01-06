package com.example.hostelmanagementprojectv3;

import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AdminStudentInformationShow {

    @FXML
    private TableView<Student> studentInformationTableView;
    @FXML
    private TableColumn<Student, String> studentNameTableColumn;
    @FXML
    private TableColumn<Student, String> studentIDTableColumn;
    @FXML
    private TableColumn<Student, String> studentDepartmentTableColumn;
    @FXML
    private TableColumn<Student, String> studentMobileTableColumn;
    @FXML
    private TableColumn<Student, String> studentGenderTableColumn;

    @FXML
    private Label totalRegisteredStudent;

    public void initialize(){
        if(totalRegisteredStudent != null){
            totalRegisteredStudent.setText("" + StudentDataFile.allStudent.size());
        }

        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentDepartmentTableColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        studentMobileTableColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        studentGenderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        ObservableList<Student> studentData = FXCollections.observableArrayList(StudentDataFile.allStudent);
        studentInformationTableView.setItems(studentData);

    }

}
