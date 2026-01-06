package com.example.hostelmanagementprojectv3;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class StudentDataFile {
    public static ArrayList<Student> allStudent = new ArrayList<>();
    public static ArrayList<StudentIDPassword> passID = new ArrayList<>();
    public static Student tempStudentData;
    public static Student currentLoggedInStudent;


    //Student Management Files
    public static void addStudent(){
        try(BufferedWriter bWriter = new BufferedWriter(new FileWriter("student.txt"))){
            for(Student s : allStudent){
                String line = s.getStudentName() + "," + s.getStudentID() + "," + s.getDepartment() + "," + s.getMobile() + "," + s.getGender();
                bWriter.write(line);
                bWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can not be created.\nThe Reason:" + e);
        }
    }

    public static void loadStudent() throws FileNotFoundException {
        allStudent.clear();
        try(BufferedReader bReader = new BufferedReader(new FileReader("student.txt"))){
            String line;
            while((line = bReader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 5){
                    String stdName = parts[0];
                    String stdID = parts[1];
                    String stdDept = parts[2];
                    String stdMo = parts[3];
                    String stGen = parts[4];
                    Student s = new Student(stdName, stdID, stdDept, stdMo, stGen);
                    allStudent.add(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Password Management Files
    public static void addPasswordFile() throws IOException {
        try(BufferedWriter bWriter = new BufferedWriter(new FileWriter("password.txt"))){

            for(StudentIDPassword stPass : passID){
                String line = stPass.getStudentId() + "," + stPass.getStudentPassword();
                bWriter.write(line);
                bWriter.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException("File can not be created.\nThe Reason:" + e);
        }
    }

    public static void loadPassword() throws IOException {
        passID.clear();
        try(BufferedReader bReader = new BufferedReader(new FileReader("password.txt"))){
            String line;
            while((line = bReader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    String ID = parts[0];
                    String password = parts[1];
                    StudentIDPassword st = new StudentIDPassword(ID, password);
                    passID.add(st);
                }
            }
        }
    }
}
