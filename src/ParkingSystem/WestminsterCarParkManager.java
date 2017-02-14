package ParkingSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WestminsterCarParkManager implements CarParkManager, Serializable {

     Scanner sc = new Scanner(System.in);
     boolean pleasePay=false;
    private Slot[] slot = new Slot[20];//create a slot array
    ArrayList<Vehicle> history=new ArrayList<Vehicle>();//deleted vehicles are store in this arraylist

    public void process(){
        System.out.println("\t\t================================================= ");
        System.out.println("\t\t||     Welcome to the Westminster Car Park      ||");
        System.out.println("\t\t================================================= ");
        //menu
        System.out.println("\tA : Add vehicle ");
        System.out.println("\tD : Delete vehicle ");
        System.out.println("\tL : List of vehicles in the park ");
        System.out.println("\tP : Percentage of vehicles parked ");
        System.out.println("\tT : Vehicle that has longest parking time ");
        System.out.println("\tS : Last vehicle parked ");
        System.out.println("\tF : Search for a vehicle ");
        System.out.println("\tR : Current payment details for parking ");
        System.out.println("\tw : Writting to the file ");
        System.out.println("\ty : Reading to the file ");
        System.out.println("\tq : Exit from the system");

        System.out.print("\nChoose a character for proceed: ");
        char option = sc.next().toLowerCase().charAt(0);//store character in option variable

        switch (option) {
            case 'a':
                addVehicle();
                break;

            case 'd':
                deleteVehicle();
                break;

            case 'l':
                printParkedVehicles();
                System.out.println();
                System.out.println();
                process();
                break;

            case 'p':
                 percentageVehicleParked();

                System.out.println();
                process();
                break;


            case 't':
                Vehicle lVehicle = getLongestParkingVehicle();
                  //check vehicles are in the park
                if(lVehicle != null){
                    System.out.println();
                    System.out.print("Vehicle with longest parking time : ");
                    System.out.println(lVehicle);
                }
                else{
                    System.out.println("No vehicles in the park");
                }
                System.out.println();
                System.out.println();
                //back to menu
                process();
                break;

            case 's':
                Vehicle sVehicle = getShortestParkingVehicle();

                if(sVehicle != null){
                    System.out.println();
                    System.out.print("Last vehicle parked : ");
                    System.out.println(sVehicle);
                }
                else{
                    System.out.println("No vehicles in the park");
                }
                System.out.println();
                System.out.println();
                process();
                break;

            case 'f':
            	//search deleted vehicles by entrance date 
                findVehicleFromHistory();
                System.out.println();
                System.out.println();
                process();
                break;

            case 'r':
                calculateParkCharges();
                System.out.println();
                System.out.println();
                process();
                break;
            
            case 'w':
            	//write to the file
            	Writter();
                process();

               break;
               
            case 'y':
              //read to the file 
            	reader();
                process();

        	  break;
               
           case 'q':
                //exit from the system
              System.exit(0);

          	  break;
          	  
            default:
                System.out.println("Please input valid character");
                process();
                break;

        }
    }

    @Override
    public void addVehicle() {
        //get the parked vehicle type
        System.out.println("C : Car");
        System.out.println("V : Van");
        System.out.println("M : Motorbike");
        System.out.print("Enter vehicle type:");

        char vType = sc.next().toLowerCase().charAt(0);
        Car car = new Car();
        Van van = new Van();
        MotorBike motorBike = new MotorBike();
//set vehicle type in to the vehicle object
        car.setVType(vType);
         van.setVType(vType);
         motorBike.setVType(vType);
         
        switch (vType){
            case 'c':

                int freeSlotId = checkFreeParkingSlots(false);
                if(freeSlotId != 0){
               //get inputs by the user
                    System.out.println();
                    System.out.print("Add plate Id : ");
                    String plateId = sc.next();
                    car.setPlateId(plateId);

                    System.out.print("Add brand : ");
                    String brand = sc.next();
                    car.setBrand(brand);

                    System.out.print("Add Entry Date/Time : ");
                    //store entry time in the vehicle reference
                    car.setEntryDateTime(addDateTime());
                    int numberOfDoors;
                    boolean door=false;
                    while(!door){
                    	System.out.println("Add num of doors : ");
                    	if(sc.hasNextInt()){
                        numberOfDoors = sc.nextInt();
                        car.setNumOfDoors(numberOfDoors);
                          door=true;
                    	}else{
                    		System.out.println("invalid input");
                    		sc.next();
                    	}
                    }

                    System.out.println("Add Color : ");
                    String color = sc.next();
                    car.setColor(color);
                   //create a new slot array object
                    slot[freeSlotId-1] = new Slot();
                    //set the slot number to the slot array
                    slot[freeSlotId-1].setSlotId(freeSlotId);
                    //set vehicle object type to the slot array
                    slot[freeSlotId-1].setVehicle(car);

                    System.out.println();
                    System.out.println();
                }

                process();
                break;

            case 'v':
            	//get inputs to the van
                freeSlotId = checkFreeParkingSlots(true);
                if(freeSlotId != 0){

                    System.out.println();
                    System.out.print("Add plate Id : ");
                    String plateId = sc.next();
                    van.setPlateId(plateId);

                    System.out.print("Add brand : ");
                    String brand = sc.next();
                    van.setBrand(brand);

                    System.out.print("Add Entry Date/Time : ");
                    van.setEntryDateTime(addDateTime());
                    boolean volume=false;
                    while(!volume){
                        System.out.println("Cargo Volume : ");
                    	if(sc.hasNextInt()){
                            int cargoVolume = sc.nextInt();
                            van.setCargoVolume(cargoVolume);
                          volume=true;
                    	}else{
                    		System.out.println("invalid input");
                    		sc.next();
                    	}
                    }

                    slot[freeSlotId-1] = new Slot();
                    slot[freeSlotId-1].setSlotId(freeSlotId);
                    slot[freeSlotId-1].setVehicle(van);

                    slot[freeSlotId] = new Slot();

                    System.out.println();
                    System.out.println();

                }
                process();
                break;

            case 'm':
            	//gets input to the motorbike
                freeSlotId = checkFreeParkingSlots(false);
                if(freeSlotId != 0){

                    System.out.println();
                    System.out.print("Add plate Id : ");
                    String plateId = sc.next();
                    motorBike.setPlateId(plateId);

                    System.out.print("Add brand : ");
                    String brand = sc.next();
                    motorBike.setBrand(brand);

                    System.out.print("Add Entry Date/Time : ");
                    motorBike.setEntryDateTime(addDateTime());
                    boolean size=false;
                    while(!size){
                        System.out.println("Engine Size : ");
                    	if(sc.hasNextInt()){
                    		 int engineSize = sc.nextInt();
                             motorBike.setEngineSize(engineSize);
                          size=true;
                    	}else{
                    		System.out.println("invalid input");
                    		sc.next();
                    	}
                    }
                   

                    slot[freeSlotId-1] = new Slot();
                    slot[freeSlotId-1].setSlotId(freeSlotId);
                    slot[freeSlotId-1].setVehicle(motorBike);

                    System.out.println();
                    System.out.println();

                }
                process();
                break;

            default:
                System.out.println("Please input valid character");
                addVehicle();
                break;

        }
    }

    @Override
    public void deleteVehicle() {
    	
    	
    	
    	
    	boolean skip2=false;
        System.out.println();
        System.out.println("Add current time ");

        DateTime currentDateTime = addDateTime();
        int timeGap;
            System.out.println("Enter Id Pate no:");
           // Scanner sc=new Scanner(System.in);
            String plateId = sc.next();
            
        System.out.println("Parking Charges\nVehicle Type\t\t\tID Plate\t\t\t\tFinal Price($)");
        System.out.println("------------------------------------------------------");
          boolean payment=true;
        for(Slot s: slot){
        	if(skip2){
        		skip2=false;
        		continue;
        	}
            if(s != null){
            	if(s.getVehicle().getVType()=='v'){
            		skip2=true;
            	}
            	
            	if(s.getVehicle().getPlateId().equals(plateId)){
            		payment=false;
            		 timeGap = calculateTimeGap(s.getVehicle().getEntryDateTime(), currentDateTime);
                     System.out.println(s.getVehicle().getVType()+"\t\t\t\t\t"+s.getVehicle().getPlateId()+"\t\t\t\t\t"+calculateCharges(timeGap/60));	
            	
            		
            		}
               
            }
        }
        if(payment){
        	
         System.out.println("No any vehicle from "+plateId);
         System.out.println("Do you want to enter Id Plate number agrain:Y/N");
         Scanner sc=new Scanner(System.in);
         char input=sc.next().charAt(0);
         if(input=='y' || input=='Y'){
        	 calculateParkCharges();
         }
        }
    	   	
    	   	  	
    	        System.out.println();
        System.out.print("Add plate Id : ");
        boolean skip=false;
        boolean hasVeh=true;

   
        boolean isDelete = false;
        //check for user given plate id is available
        for(Slot s: slot){
        	if(s != null){
        		if(skip){
        			skip=false;
        			continue;
        			
        		}
        		if(s.getVehicle().getVType()=='v'){
        			skip=true;
        			        			
        		}
            if( s.getVehicle().getPlateId().equals(plateId)){
            	//add deleted vehicle to the history arraylist
            	history.add(s.getVehicle());
            for(int j=0;j<history.size();j++){
            	//System.out.println(history.get(j));
            }
                slot[s.getSlotId()-1] = null;
                if(s.getVehicle().getVType()=='v'){
                    slot[s.getSlotId()] = null;
                }

                System.out.println(s.getVehicle().getPlateId() + " successfully deleted");
                char character=s.getVehicle().getVType();
                switch(character){
                
                      case 'c':
                        System.out.println("car is successfully deleted");
                      break;
                    
                     case 'v':
                       System.out.println("van is successfully deleted");
                      break;
                    
                    case 'm':
                       System.out.println("motorbike is successfully deleted");
                      break;  
                    
                    
                }

                isDelete = true;
            }
        	}
        }
          
        
        if(!isDelete){
            System.out.println("Cannot find the plate id");
        }
       
    
           
    process();
    	}
    

    @Override
    public void printParkedVehicles() {
         int count=0;
        for(Slot s: slot){
            if(s != null && s.getSlotId()!=0){
            	count++;
                System.out.println(s.getVehicle());
                char character=s.getVehicle().getVType();
                switch(character){
                
                      case 'c':
                        System.out.println("vehicle type is a CAR");
                      break;
                    
                     case 'v':
                       System.out.println("vehicle type is a Van");
                      break;
                    
                    case 'm':
                       System.out.println("vehicle type is a motorbike");
                      break;  
                    
                    
                }
            }
        }
     

    }

    @Override
    public int checkFreeParkingSlots(boolean isVan) {

        ArrayList<Integer> freeSlots = new ArrayList<Integer>();

        for(int i=0; i < 20; i++){
            if(!isVan){
                if(slot[i] == null){
                    freeSlots.add(i+1);
                }
            }
            else {
                if(slot[i] == null){
                    if(i < 19){
                        if(slot[i+1] == null){

                            freeSlots.add(i+1);
                        }
                    }
                }
            }
        }

        if(freeSlots.isEmpty()){
            System.out.println("No free slots available, Car Park Full");
            return 0;
        }
        else {
            System.out.print("Free Slots : ");
            System.out.println(Arrays.toString(freeSlots.toArray()));
            return getFreeParkingSlotId(freeSlots, isVan);
        }
    }

    @Override
    public void percentageVehicleParked() {
    	
        //int numVehicles = 0;
    	double total=0;
        double countc=0;
        double countv=0;
        double countm=0;
        boolean skip=false;
       
        
        for(Slot s: slot){
        	if(skip){
        		skip=false;
        		continue;
        	}
        if(s!=null){
          	if(s.getVehicle().getVType()=='c'){
        	   countc++;
        	}else if(s.getVehicle().getVType()=='v'){
        		
        		countv++;
        		skip=true;
        	}else{
        		countm++;
        	}
        }
        }
   
        total=countc+countv+countm;

        if(total!=0){
        double carPercentage=(double)(countc/total)*100;
        double vanPercentage=(double)(countv/total)*100;
        double motorbikePercentage=(double)(countm/total)*100;
        System.out.println("Percentage of cars parked : "+carPercentage+"%");
        System.out.println("Percentage of vans parked : "+vanPercentage+"%");
        System.out.println("Percentage of motorbike parked : "+motorbikePercentage+"%");
        }else{
        	System.out.println("no vehicles in the system");
        
    	}

      /*  System.out.print("Percentage of vehicles parked : ");
        double percentage = ((double) numVehicles/20)*100;*/
       
    }

    @Override
    public Vehicle getLongestParkingVehicle() {

        System.out.println();
        System.out.println("Add current time ");

        DateTime currentDateTime = addDateTime();
        int maxTime = 0;
        int maxSlotNumber = 0;
        int timeGap;
         boolean skip=false;

        for(Slot s: slot){
        	if(skip){
        		skip=false;
        		continue;
        	}
            if(s != null){
            	if(s.getVehicle().getVType()=='v'){
            		skip=true;
            	}
                timeGap = calculateTimeGap(s.getVehicle().getEntryDateTime(), currentDateTime);
         
                if(timeGap > maxTime){
                    maxTime = timeGap;
                    maxSlotNumber = s.getSlotId();
                }
            }
        }
       
        return slot[maxSlotNumber-1].getVehicle();
    }

    @Override
    public Vehicle getShortestParkingVehicle() {
        System.out.println();
        boolean skip=false;
        System.out.println("Add current time ");

        DateTime currentDateTime = addDateTime();
        int minTime = Integer.MAX_VALUE;
        int minSlotNumber = 0;
        int timeGap;

        for(Slot s: slot){
        	if(skip){
        		skip=false;
        		continue;
        	}
            if(s != null){
            	if(s.getVehicle().getVType()=='v'){
            		skip=true;
            	}
                timeGap = calculateTimeGap(s.getVehicle().getEntryDateTime(), currentDateTime);
                if(timeGap < minTime){
                    minTime = timeGap;
                    minSlotNumber = s.getSlotId();
                }
            }
        }

        return slot[minSlotNumber-1].getVehicle();
    }

    @Override
    public void findVehicleFromHistory() {

        System.out.println();
        boolean day=false;
        int date=0;
        while(!day){
            System.out.print("date : ");
        	if(sc.hasNextInt() ){
                 date = sc.nextInt();
                if(date>0 && date<=31){
              day=true;
                }else{
                	
            		System.out.println("invalid day input");

                }
        	}else{
        		System.out.println("invalid input");
        		sc.next();
        	}
        }
       

        boolean isFind = false;
       /* for(Slot s: slot){
            if(s != null && s.getVehicle().getPlateId().equals(plateId)){
                System.out.println("Vehicle found successfully");
                System.out.println(s.getVehicle());
                isFind = true;
            }
        }*/
        for(Vehicle h : history){
        	if(h.getEntryDateTime().getDate()== date){
                System.out.println("Vehicle found successfully");

        		System.out.println("Brand : "+h.getBrand());
        		System.out.println("Id palate: "+h.getPlateId());
        		System.out.println("Vehicle type: "+h.getVType());
        		System.out.println("Entry time: "+h.getEntryDateTime());
                isFind = true;

        	}
        }

        if(!isFind){
            System.out.println("No vehicle parked");
        }
        process();
    }

    @Override
    public int getFreeParkingSlotId(ArrayList<Integer> freeSlots, boolean isVan) {

        boolean isValue = false;
        int parkingSlot = 0;

        if(!isVan){
            for(int slot : freeSlots){
                if(!isValue){
                    isValue = true;
                    parkingSlot = slot;
                }
                else {
                    break;
                }
            }
        }
        else{

            Object[] freeSlotsForVan = freeSlots.toArray();

            for(int i = 0; i < freeSlotsForVan.length-1; i++){
                if((int)freeSlotsForVan[i+1] == (int)freeSlotsForVan[i]+1){
                    if(!isValue){
                        isValue = true;
                        parkingSlot = (int)freeSlotsForVan[i];

                    }
                    else {
                        break;
                    }
                }
            }

        }

        return parkingSlot;
    }

    @Override
    public DateTime addDateTime() {

        DateTime dateTime = new DateTime();
        int year;
        int month;
        int dateNum;
        int hour;
        int min;
        boolean split=true;
        do{
        System.out.println("Add Date (Use format: YYYY-MM-DD)");
        String date = sc.next();

        String[] dateFormat = date.split("-");
        while(!date.contains("-")) {
        	System.out.println("invalid date input");
      
        	System.out.println("Add Date (Use format: YYYY-MM-DD)");
             date = sc.next();

            dateFormat = date.split("-");         
            }
        
        year = Integer.parseInt(dateFormat[0]);
        if(dateTime.checkYear(year)){
            dateTime.setYear(year);
        }

         month = Integer.parseInt(dateFormat[1]);
        
        
        if(dateTime.checkMonth(month)){
            dateTime.setMonth(month);
        }

        dateNum = Integer.parseInt(dateFormat[2]);
        if(dateTime.checkDate(dateNum)){
            dateTime.setDate(dateNum);
        }
        
        }while(!(dateTime.checkYear(year) && dateTime.checkMonth(month) && dateTime.checkDate(dateNum) ));
  do{
        System.out.println("Add Time (Use format: HH:MM)");
        String time = sc.next();

        String[] timeFormat = time.split(":");
        while(!time.contains(":")) {
        	System.out.println("invalid Time input");
      
        	System.out.println("Add Date (Use format: YYYY-MM-DD)");
             time = sc.next();

            timeFormat = time.split(":");         
            }
         hour = Integer.parseInt(timeFormat[0]);
        if(dateTime.checkHour(hour)){
            dateTime.setHours(hour);
        }

         min = Integer.parseInt(timeFormat[1]);
        if(dateTime.checkMinites(min)){
            dateTime.setMinites(min);
        }
      }while(!(dateTime.checkHour(hour) && dateTime.checkMinites(min)));


        return dateTime;

    }

    @Override
    public void calculateParkCharges() {
    	boolean skip2=false;
        System.out.println();
        System.out.println("Add current time ");

        DateTime currentDateTime = addDateTime();
        int timeGap;
            System.out.println("Enter Id Pate no:");
           // Scanner sc=new Scanner(System.in);
            String payId=sc.next();
            
        System.out.println("Parking Charges\nVehicle Type\t\t\tID Plate\t\t\t\tFinal Price($)");
        System.out.println("------------------------------------------------------");
          boolean payment=true;
        for(Slot s: slot){
        	if(skip2){
        		skip2=false;
        		continue;
        	}
            if(s != null){
            	if(s.getVehicle().getVType()=='v'){
            		skip2=true;
            	}
            	
            	if(s.getVehicle().getPlateId().equals(payId)){
            		payment=false;
            		 timeGap = calculateTimeGap(s.getVehicle().getEntryDateTime(), currentDateTime);
                     System.out.println(s.getVehicle().getVType()+"\t\t\t\t\t"+s.getVehicle().getPlateId()+"\t\t\t\t\t"+calculateCharges(timeGap/60));	
            	
            		
            		}
               
            }
        }
        if(payment){
        	
         System.out.println("No any vehicle from "+payId);
         System.out.println("Do you want to enter Id Plate number agrain:Y/N");
         Scanner sc=new Scanner(System.in);
         char input=sc.next().charAt(0);
         if(input=='y' || input=='Y'){
        	 calculateParkCharges();
         }
        }
       
        	process();
        	
        
    }

    public int calculateTimeGap(DateTime entryDateTime, DateTime currentDateTime) {

        int min = currentDateTime.getMinites() - entryDateTime.getMinites();
        int hours = currentDateTime.getHours() - entryDateTime.getHours();
        int day = currentDateTime.getDate() - entryDateTime.getDate();
        int month = currentDateTime.getMonth() - entryDateTime.getMonth();
        int year = currentDateTime.getYear() -  entryDateTime.getYear();

        return min + hours*60 + day*24*60 + month*30*24*60 + year*12*30*24*60;
    }

    public int calculateCharges(int hours){
        int charges = 0;

        if(hours > 0 && hours <= 3){
            charges += hours*3;
        }
        else if(hours > 3 && hours <= 24){
            charges += 3*3;
            charges += 4*(hours-3);
            if(charges > 30){
                charges = 30;
            }
        }
        else if(hours > 24){
            charges += (hours/24)*30;
        }

        return charges;
    }
    
public void Writter(){
	for(int i=0;i<20;i++){
		//System.out.println(slot[i]);
		
	//System.out.println(history.get(i));
	}
	 for(int j=0;j<history.size();j++){
     	System.out.println(history.get(j));
     }
	
		File file=new File("input.txt");
		try {
			FileOutputStream fo=new FileOutputStream(file);
			ObjectOutputStream oo=new ObjectOutputStream(fo);
			
			oo.writeObject(slot);
			oo.writeObject(history);
			oo.close();
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        System.out.println("File not found");	
        } catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	
	
	public void reader(){
		File file=new File("input.txt");
		try {
			FileInputStream fi=new FileInputStream(file);
			ObjectInputStream oi=new ObjectInputStream(fi);
			slot=(Slot[])oi.readObject();
			history=(ArrayList<Vehicle>)oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        // System.out.println("file not found");
			e.printStackTrace();

         } catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		for(int i=0;i<20;i++)
			if(slot[i]==null){}else{
		System.out.println(slot[i].getVehicle());
			}
		System.out.println(history.size());
		for(int j=0;j<history.size();j++){
	     	System.out.println(history.get(j));
	     }
		//System.out.println(em[2]);

	}

}
