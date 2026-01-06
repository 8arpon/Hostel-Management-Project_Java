package com.example.hostelmanagementprojectv3;

public class Rooms {
    private String studentID, floor, roomNumber, status;
    public Rooms(String studentID, String floor, String roomNumber, String status){
        this.studentID = studentID;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.status = status;
    }


    public void setFloor(String floor){
        this.floor = floor;
    }
    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }
    public String getFloor(){
        return floor;
    }
    public String getRoomNumber(){
        return roomNumber;
    }
    public String getStatus(){
        return status;
    }

}
