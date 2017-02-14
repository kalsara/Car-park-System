package ParkingSystem;



public class DateTime {
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
    
}
