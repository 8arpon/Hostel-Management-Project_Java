package com.example.hostelmanagementprojectv3;

public class Complain {
    private String complainText, stID;
    private String adminReview;
    Complain(String stID, String complainText, String adminReview){
        this.stID = stID;
        this.complainText = complainText;
        this.adminReview = adminReview;
    }

    public void setAdminReview(String adminReview) {
        this.adminReview = adminReview;
    }

    public void setComplainText(String complainText) {
        this.complainText = complainText;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getComplainText() {
        return complainText;
    }

    public String getStID() {
        return stID;
    }

    public String getAdminReview() {
        return adminReview;
    }
}
