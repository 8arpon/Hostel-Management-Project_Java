package com.example.hostelmanagementprojectv3;

public class StudentIDPassword {
    private String studentId, studentPassword;
    StudentIDPassword(String studentId, String studentPassword){
        this.studentId = studentId;
        this.studentPassword = studentPassword;
    }
    public void setStudentId(String id){this.studentId = id;}
    public void setStudentPassword(String pass){this.studentPassword = pass;}
    public String getStudentId(){return studentId;}
    public String getStudentPassword(){return studentPassword;}
}
