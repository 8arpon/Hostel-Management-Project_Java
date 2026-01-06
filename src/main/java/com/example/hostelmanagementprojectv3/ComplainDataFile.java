package com.example.hostelmanagementprojectv3;

import java.io.*;
import java.util.ArrayList;

public class ComplainDataFile {
    public static ArrayList<Complain> complains = new ArrayList<>();
    public static ArrayList<String> pendingComplainIDs = new ArrayList<>();

    public static void addComplainToFile(){
        try(BufferedWriter bWriter = new BufferedWriter(new FileWriter("complain.txt"))){
            for(Complain com : complains){
                String line = com.getStID() + "," + com.getComplainText() + "," + com.getAdminReview();
                bWriter.write(line);
                bWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadComplainBox() throws FileNotFoundException {
        complains.clear();
        pendingComplainIDs.clear();

        try(BufferedReader bReader = new BufferedReader(new FileReader("complain.txt"))){
            String line;

            while((line = bReader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 3){
                    String ID = parts[0];
                    String studentComplain = parts[1];
                    String adminReview = parts[2];
                    if(adminReview.equals("Pending")){
                        pendingComplainIDs.add(ID);
                    }
                    Complain com = new Complain(ID, studentComplain, adminReview);
                    complains.add(com);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
