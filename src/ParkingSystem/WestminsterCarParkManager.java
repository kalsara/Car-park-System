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
             }
    }