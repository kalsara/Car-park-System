package ParkingSystem;

import java.io.Serializable;

public class DateTime implements Serializable{

    private int year;
    private int month;
    private int date;
    private int hours;
    private int minites;
	int countY=0;
	int countM=0;
	int countD=0;
	int countH=0;
	int countMin=0;


    public boolean checkYear(int year){

        if(year > 2015){
            return true;
        }
     
        else{
        	if(countY>0){
        	System.out.println("invalid year");
        	}
        	countY++;

            return false;
        }
    }

    public boolean checkMonth(int month){

        if(month > 0 && month <13){
            return true;
        }

        else{
        	if(countM>0){
            	System.out.println("invalid month");
            	}
            	countM++;
            return false;
        }
    }

    public boolean checkDate(int day){

        if(day > 0 && day < 32){
            return true;
        }

        else{
        	if(countD>0){
            	System.out.println("invalid day");
            	}
            	countD++;
            return false;
        }
    }

    public boolean checkHour(int hour){

        if(hour > 0 && hour < 25){
            return true;
        }

        else{
        	if(countH>0){
            	System.out.println("invalid hour");
            	}
            	countH++;
            return false;
        }
    }

    public boolean checkMinites(int minite){

        if(minite > 0 && minite < 60){
            return true;
        }

        else{
        	if(countMin>0){
            	System.out.println("invalid Minutes");
            	}
            	countMin++;            
            	return false;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinites() {
        return minites;
    }

    public void setMinites(int minites) {
        this.minites = minites;
    }

    @Override
    public String toString() {
        return year + "-" + month +"-"+ date + " " + hours + ":" + minites;
    }
}
