package ParkingSystem;


import java.util.ArrayList;

public interface CarParkManager  {

    void addVehicle();
    void deleteVehicle();
    void printParkedVehicles();
    int checkFreeParkingSlots(boolean isVan);
    void percentageVehicleParked();
    Vehicle getLongestParkingVehicle();
    Vehicle getShortestParkingVehicle();
    void findVehicleFromHistory();
    int getFreeParkingSlotId(ArrayList<Integer> freeSlots, boolean isVan);
    DateTime addDateTime();
    void calculateParkCharges();
    void  Writter();
    void reader();

}
