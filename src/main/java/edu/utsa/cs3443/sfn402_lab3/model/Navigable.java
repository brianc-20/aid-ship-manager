package edu.utsa.cs3443.sfn402_lab3.model;

/**
 * Moves the unit toward a destination at the specified speed.
 *
 * @param destination the destination the unit will navigate toward
 * @param speed the speed at which the unit will travel
 */
public interface Navigable {
    void navigateTo(String destination, double speed);
}
