package ParkingSystem;

import java.io.Serializable;

public abstract class  Vehicle implements Serializable{

    private String plateId;
    private String brand;
    private DateTime entryDateTime;
    private char VType;
    
    
        
    public char getVType() {
		return VType;
	}

	public void setVType(char vType) {
		VType = vType;
	}

	public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DateTime getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(DateTime entryDateTime) {
        this.entryDateTime = entryDateTime;
    }
}
