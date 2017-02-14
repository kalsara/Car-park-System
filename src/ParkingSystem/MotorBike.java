package ParkingSystem;

import java.io.Serializable;

public class MotorBike extends Vehicle implements Serializable{

    private int engineSize;

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    @Override
    public String toString() {
        return "plateId= "+ super.getPlateId() + ", brand= "+ super.getBrand() + ", entry date/time= "+ super.getEntryDateTime() +", engineSize=" + engineSize;
    }
}
