package com.example.hostelmanagementprojectv3;

import java.io.Serializable;

public class Student{
    private String studentName, studentID, department, mobile, gender;
    public Student(String studentName, String studentID, String department, String mobile, String gender){
        this.studentName = studentName;
        this.studentID = studentID;
        this.department = department;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getStudentName(){return studentName;}
    public String getStudentID(){return studentID;}
    public  String getDepartment(){return department;}
    public String getMobile(){return mobile;}
    public String getGender(){return gender;}
}
