module com.example.hostelmanagementprojectv3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;

    opens com.example.hostelmanagementprojectv3 to javafx.fxml;
    exports com.example.hostelmanagementprojectv3;
    exports com.example.hostelmanagementprojectv3.AlertWorkspace;
    opens com.example.hostelmanagementprojectv3.AlertWorkspace to javafx.fxml;
}