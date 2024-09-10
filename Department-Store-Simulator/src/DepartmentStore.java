/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 2
 * CSE214.R30
 */
import java.util.Scanner;

/**
 * The DepartmentStore class provides a menu to use the ItemList.
 * You can insert items, move items, list items by location, print all items, checkout items,
 * clean the store, update the inventory system, and print items by RFID tag number.
 */
public class DepartmentStore 
{
   /**
    * The main method initializes the ItemList
    * and provides a menu for user interaction.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) 
   {
       Scanner input = new Scanner(System.in);
       ItemList itemList = new ItemList();
       System.out.println("Welcome to the Department Store Inventory Management System!");
       System.out.println("Menu:\r\n"
               + "C) Clean store \r\n"
               + "I) Insert an item into the list\r\n"
               + "L) List by location\r\n"
               + "M) Move an item in the store \r\n"
               + "O) Checkout\r\n"
               + "P) Print all items in store\r\n"
               + "R) Print by RFID tag number\r\n"
               + "U) Update inventory system\r\n"
               + "Q) Quit\r\n"
               + "Please select an option");

       String option;
       boolean running = true;
       while (running) 
       {
           option = input.nextLine().toUpperCase();
           try 
           {
               switch (option) 
               {
                   case "C":
                       itemList.cleanStore();
                       System.out.println("The store is cleaned!");
                       break;
                   case "I":
                       try 
                       {
                           System.out.print("Enter the name: ");
                           String name = input.nextLine().trim();
                           if (name.length() > 20) 
                           {
                               throw new Exceptions.IllegalArgumentException("Name must be 20 characters or less.");
                           }
                           System.out.print("Enter the RFID: ");
                           String rfidTag = input.nextLine().trim();
                           if (rfidTag.length() != 9 || !rfidTag.matches("[0-9A-Fa-f]+")) 
                           {
                               throw new Exceptions.IllegalArgumentException("RFID tag number must be 9 characters long and contain only hexadecimal characters.");
                           }
                           System.out.print("Enter the original location: ");
                           String originalLocation = input.nextLine().trim();
                           if (originalLocation.length() != 6 || !(originalLocation.substring(0, 1).equals("s") || originalLocation.substring(0, 1).equals("c"))) {
                               throw new Exceptions.IllegalArgumentException("Invalid original location, must be 6 characters starting with 's' or 'c'.");
                           }
                           System.out.print("Enter the price: ");
                           double price = Double.parseDouble(input.nextLine().trim());
                           if (price < 0) 
                           {
                               throw new Exceptions.IllegalArgumentException("Price cannot be negative.");
                           }

                           itemList.insertInfo(name, rfidTag, price, originalLocation);
                           System.out.println("Item inserted.");
                       } 
                       catch (Exception e) 
                       {
                           System.out.println("Can't insert: " + e.getMessage());
                       }
                       break;
                   case "L":
                   {
                       System.out.print("Enter the location: ");
                       String location = input.nextLine().trim();
                       itemList.printByLocation(location);
                       break;
                   }
                   case "M":
                       try 
                       {
                           System.out.print("Enter RFID tag number: ");
                           String rfidTag = input.nextLine().trim();

                           System.out.print("Enter source location: ");
                           String source = input.nextLine().trim();

                           System.out.print("Enter destination location: ");
                           String destination = input.nextLine().trim();

                           if (itemList.moveItem(rfidTag, source, destination)) 
                           {
                               System.out.println("Item moved successfully.");
                           } 
                           else 
                           {
                               System.out.println("Item not found or invalid move.");
                           }
                       } 
                       catch (Exception e) 
                       {
                           System.out.println("Error moving item: " + e.getMessage());
                       }
                       break;
                   case "O":
                       try 
                       {
                           System.out.print("Enter the cart number: ");
                           String cartNumber = input.nextLine().trim();
                           double totalCost = itemList.checkOut(cartNumber);
                           System.out.printf("Total cost of items in cart: $%.2f%n", totalCost);
                       } 
                       catch (Exception e) 
                       {
                           System.out.println("Error during checkout: " + e.getMessage());
                       }
                       break;
                   case "P":
                   {
                       itemList.printAll();
                       break;
                   }
                   case "R":
                   {
                       System.out.print("Enter the RFID number: ");
                       String rfidTag = input.nextLine().trim();
                       itemList.printByRFID(rfidTag);                   
                       break;
                   }
                   case "U":
                   {
                       itemList.removeAllPurchased();
                       break;
                   }
                   case "Q":
                   {
                       running = false;
                       break;
                   }
                   default:
                   {
                       System.out.println("Invalid option. Please select again.");
                   }
               }
           } 
           catch (Exception e) 
           {
               System.out.println("An error occurred: " + e.getMessage());
           }

           if (running) 
           {
               System.out.println("Menu:\r\n"
                       + "C) Clean store \r\n"
                       + "I) Insert an item into the list\r\n"
                       + "L) List by location\r\n"
                       + "M) Move an item in the store \r\n"
                       + "O) Checkout\r\n"
                       + "P) Print all items in store\r\n"
                       + "R) Print by RFID tag number\r\n"
                       + "U) Update inventory system\r\n"
                       + "Q) Quit\r\n"
                       + "Please select an option");
           }
       }
       input.close();
   }
}