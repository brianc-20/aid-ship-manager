package edu.utsa.cs3443.sfn402_lab3.model;

/**
 * Represents the shared attributes and behaviors of a ship.
 * This abstract class provides navigation capabilities and serves
 * as the parent class for specific types of ships.
 */
public abstract class Ship implements Navigable{
    private String name;
    private String regiNum;
    double tonnage;
    int crewSize;
    double currSpeed;
    private String currPort;


    /**
     * Constructs a ship with its identifying information,
     * physical properties, location, and speed.
     *
     * @param n     the name of the ship
     * @param rn    the ship's registration number
     * @param tn    the ship's tonnage
     * @param crewS the number of crew members aboard the ship
     * @param cp    the ship's current port
     * @param currS the ship's current speed
     */

//constructor
public Ship(String n, String rn, double tn, int crewS, String cp, double currS){
    setName(n);
    setRegi(rn);
    setTon(tn);
    setcsize(crewS);
    setSpeed(currS);
    setPort(cp);

}


//name getters and setters
public String getName(){
    return name;
}

public void setName(String nameL){
    if (nameL == null) {
        throw new IllegalArgumentException("Name cannot be empty.");
    }

    name = nameL;
}

//registration number getters and setters
public String getRegi(){
    return regiNum;

}

public void setRegi(String regiL){
    if (regiL == null) {
        throw new IllegalArgumentException(
                "Registration number cannot be empty.");
    }

    regiNum = regiL;
}


//tonnage getters and setters
public double getTon(){
    return tonnage;
}

public void setTon(double t){
    if (t <= 0) {
        throw new IllegalArgumentException(
                "Tonnage must be greater than zero.");
    }

    tonnage = t;
}


//crew size getters and setters
public int getcsize(){
    return crewSize;
}

public void setcsize(int r){
    if (r < 0) {
        throw new IllegalArgumentException(
                "Crew size cannot be negative.");
    }

    crewSize = r;
}



//current speed getters and setters
public double getSpeed(){
    return currSpeed;
}

public void setSpeed(double speed){
    if (speed < 0) {
        throw new IllegalArgumentException(
                "Speed cannot be negative.");
    }

    currSpeed = speed;

}

//current port getters and setters
public String getPort(){
    return currPort;
}

public void setPort(String port){
    if (port == null) {
        throw new IllegalArgumentException(
                "Port cannot be empty.");
    }

    currPort = port;
}


    /**
     * Navigates the ship toward a specified destination at the given speed.
     * The ship's current port is changed to "At Sea".
     *
     * @param destination the destination toward which the ship will navigate
     * @param speed the speed at which the ship will travel
     * @throws IllegalArgumentException if the destination is null
     *                                  or the speed is invalid
     */

    @Override
    public void navigateTo(String destination, double speed) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination cannot be empty.");
        }

        setSpeed(speed);
        setPort("At Sea");

        System.out.println(getName() + " is navigating to "
                + destination + " at speed " + speed + ".");
    }

    /**
     * Docks the ship at a specified port and changes its speed to zero.
     *
     * @param portName the name of the port where the ship will dock
     * @throws IllegalArgumentException if the port name is invalid
     */

    public void dock(String portName) {
        setPort(portName);
        setSpeed(0.0);
    }

@Override
public String toString(){
    return "Aid Ship{" +
            "name='" + name + '\'' +
            ", registrationNumber='" + regiNum + '\'' +
            ", tonnage=" + tonnage +
            ", crewSize=" + crewSize +
            ", currentSpeed=" + currSpeed +
            ", currentPort='" + currPort + '\'' +
            '}';
}

}