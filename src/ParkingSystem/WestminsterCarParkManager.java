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
              //  printParkedVehicles();
                
                break;

            case 'p':
                 //percentageVehicleParked();

                
                break;


            case 't':
                //Vehicle lVehicle = getLongestParkingVehicle();
                  //check vehicles are in the park
                
                break;

            case 's':
                           
                break;

            case 'f':
            	//search deleted vehicles by entrance date 
                //findVehicleFromHistory();
                
                break;

            case 'r':
                //calculateParkCharges();
                
                break;
            
            case 'w':
            	//write to the file
            	//Writter();
                //process();

               break;
               
            case 'y':
              //read to the file 
            	//reader();
               // process();

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
    
             
    }