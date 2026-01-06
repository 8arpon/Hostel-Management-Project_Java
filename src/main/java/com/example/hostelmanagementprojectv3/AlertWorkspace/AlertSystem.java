package com.example.hostelmanagementprojectv3.AlertWorkspace;

public interface AlertSystem {
     boolean confirmationAlerts(String title, String context);
     void warningAlerts(String title, String context);
     void informationAlerts(String title, String context);
     void errorAlerts(String title, String context);
}
