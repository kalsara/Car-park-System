package ParkingSystem;

import java.io.Serializable;

public class Main implements Serializable{

    public static void main(String[] args) {
        WestminsterCarParkManager carParkManager = new WestminsterCarParkManager();
        carParkManager.process();
    }
}
