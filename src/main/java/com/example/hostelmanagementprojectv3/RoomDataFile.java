package com.example.hostelmanagementprojectv3;

import java.io.*;
import java.util.ArrayList;

public class RoomDataFile {
    public static String hostelFeePerMonth = "3500";
    public static String hostelApplicationFee = "500";

    public static ArrayList<String> allRoomList = new ArrayList<>();
    public static ArrayList<Rooms> studentRoomDetails = new ArrayList<>();
    public static ArrayList<String> totalIDs = new ArrayList<>();
    public static ArrayList<String> pendingApplicationsID = new ArrayList<>();
    public static ArrayList<String> bookedRooms = new ArrayList<>();
    public static ArrayList<String> availableRoomsList = new ArrayList<>();
    public static ArrayList<String> approvedStudentID = new ArrayList<>();
    public static Rooms currentStudentRoomDetails;
    public static ArrayList<String> notAppliedStudentsID = new ArrayList<>();

    public static void applicationsToFile() throws IOException{
        try(BufferedWriter bWriter = new BufferedWriter(new FileWriter("studentRoomInformation.txt"))){
            for(Rooms room : studentRoomDetails){
                String line = room.getStudentID() + "," + room.getFloor() + "," + room.getRoomNumber() + "," + room.getStatus();
                bWriter.write(line);
                bWriter.newLine();
            }
        }
    }

    public static void availableRooms(){
        availableRoomsList.clear();
        for(String room : allRoomList){
            if(!bookedRooms.contains(room)){
                availableRoomsList.add(room);
            }
        }
    }

    public static void loadRoomDetails() throws IOException{
        studentRoomDetails.clear();
        pendingApplicationsID.clear();
        availableRoomsList.clear();
        bookedRooms.clear();
        approvedStudentID.clear();
        totalIDs.clear();
        notAppliedStudentsID.clear();

        try(BufferedReader bReader = new BufferedReader(new FileReader("studentRoomInformation.txt"))){
            String line;
            while((line = bReader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 4){
                    String ID = parts[0];
                    String Floor = parts[1];
                    String RoomNumber = parts[2];
                    String Status = parts[3];
                    totalIDs.add(ID);

                    if (Status.equals("Pending")) {
                        pendingApplicationsID.add(ID);
                    }
                    else if(Status.equals("Approved")){
                        approvedStudentID.add(ID);
                        bookedRooms.add(RoomNumber);
                    }

                    Rooms roomToList = new Rooms(ID, Floor, RoomNumber, Status);
                    studentRoomDetails.add(roomToList);
                }
            }
        }
    }

    public static void loadAllRooms() throws FileNotFoundException {
        allRoomList.clear();
        try(BufferedReader bReader = new BufferedReader(new FileReader("Rooms.txt"))){
            String line;
            while((line = bReader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 5){
                    for(int i = 0; i < 5; i++){
                        String rooms = parts[i];
                        allRoomList.add(rooms);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
