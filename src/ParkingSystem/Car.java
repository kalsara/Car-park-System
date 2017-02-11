package ParkingSystem;

import java.io.Serializable;

public class Car extends Vehicle implements Serializable {

    private int numOfDoors;
    private String color;

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "plateId= "+ super.getPlateId() + ", brand= "+ super.getBrand() + ", entry date/time= "+ super.getEntryDateTime() +", numOfDoors= " + numOfDoors + ", color= " + color;
    }
}
