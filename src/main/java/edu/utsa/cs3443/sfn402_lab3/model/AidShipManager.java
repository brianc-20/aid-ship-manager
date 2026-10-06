package edu.utsa.cs3443.sfn402_lab3.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages a collection of aid ships and loads and saves their CSV data.
 *
 * @author Brian Caloca
 */
public class AidShipManager {



    private ArrayList<AidShip> groupAidShip;
    // constructor that initializes the ArrayList of aid ships.
    public AidShipManager(){
        groupAidShip = new ArrayList<>();
    }

    //getter for aidshiplist
    public ArrayList<AidShip> getAidShipList() {
        return groupAidShip;
    }

    //setter for aidshiplist
    public void setAidShipList(ArrayList<AidShip> aidShipList) {
        if (aidShipList == null) {
            throw new IllegalArgumentException("Aid ship list cannot be null.");
        }
        this.groupAidShip = aidShipList;
    }


    //adds an AidShip object to the group of aid ships.
    public void addAidShip(AidShip objAidship){
        groupAidShip.add(objAidship);
    }

    /**
     * Loads aid ships from the CSV file, replacing the current list.
     *
     * @throws IOException if an error occurs while reading the file
     */
    public void loadAidShips() throws IOException{
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("data/aid_ships.csv"))) {

            groupAidShip.clear();
            reader.readLine(); // Skip the column headings

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    AidShip ship = convertLineToAidShip(line);
                    addAidShip(ship);
                }
            }
        }

    }
    /**
     * Finds an aid ship by its registration number.
     *
     * @param regiNum the registration number to search for
     * @return the matching aid ship, or null if no match is found
     */
    public AidShip findAidShip(String regiNum){

        for (AidShip ship : groupAidShip) {
            if (ship.getRegi().equals(regiNum)) {
                return ship;
            }
        }
        return null;
    }

    //takes a string representing the registration number of an aid ship and returns whether
    //the ship is found in the group of ships.
    public boolean isAidShipExists(String regiNum){
        for (AidShip ship : groupAidShip) {
            if (ship.getRegi().equals(regiNum)) {
                return true;
            }
        }
        return false;
    }

    //takes an AidShip object, and replaces the existing aid ship with this object.
    public boolean updateAidShip(AidShip aidshipObjTwo) throws IOException {

        AidShip existingShip = findAidShip(aidshipObjTwo.getRegi());
        int index = groupAidShip.indexOf(existingShip);

        if (index != -1) {
            groupAidShip.set(index, aidshipObjTwo);
            saveDataToFile();
            return true;
        }
        return false;
    }

    //takes an AidShip object, and removes it from the group of aid ships
    public boolean deleteAidShip(AidShip aidshipObjOne) throws IOException{
            if (groupAidShip.remove(aidshipObjOne)) {
                saveDataToFile();
                return true;
            }
            return false;
    }

    // saves the data to the data file after an update or delete.
    private void saveDataToFile() throws IOException{
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("data/aid_ships.csv"))) {

            writer.write("name,registration number,tonnage,crew size,port,aid type,aid capacity,has helipad");
            writer.newLine();

            for (AidShip ship : groupAidShip) {
                writer.write(convertAidShipToLine(ship));
                writer.newLine();
            }
        }

    }

    //takes a line read from a file and returns an AidShip object representing the data in the line.
    private AidShip convertLineToAidShip(String lineT){
        String[] parts = lineT.split(",");

        String name = parts[0];
        String registration = parts[1];
        double tonnage = Double.parseDouble(parts[2]);
        int crewSize = Integer.parseInt(parts[3]);
        String port = parts[4];
        String aidType = parts[5];
        int supplies = Integer.parseInt(parts[6]);
        boolean hasHelipad = Boolean.parseBoolean(parts[7]);

        return new AidShip(name, registration, tonnage, crewSize,
                port, 0.0, aidType, supplies, hasHelipad);
    }
    //takes an AidShip object and returns a string representing the object as a line in the file.
    private String convertAidShipToLine(AidShip aidshipO){
        return aidshipO.getName() + "," +
                aidshipO.getRegi() + "," +
                aidshipO.getTon() + "," +
                aidshipO.getcsize() + "," +
                aidshipO.getPort() + "," +
                aidshipO.getAid() + "," +
                aidshipO.getSupp() + "," +
                aidshipO.getHeli();
    }


    //returns a string representation of all aid ships.
    //uses a for each loop to append each ship into a string
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (AidShip ship : groupAidShip) {
            result.append(ship.toString());
            result.append("\n");
        }

        return result.toString();
    }









}
