package ParkingSystem;

import java.io.Serializable;

public class Van extends Vehicle implements Serializable{

    private int cargoVolume;

    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    @Override
    public String toString() {
        return "plateId= "+ super.getPlateId() + ", brand= "+ super.getBrand() + ", entry date/time= "+ super.getEntryDateTime() +", cargoVolume=" + cargoVolume;
    }
}
