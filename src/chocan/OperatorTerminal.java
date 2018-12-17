/*
 * Author: Josh Engler
 */

package chocan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.print.DocFlavor.URL;

public class OperatorTerminal extends Terminal {
	
	private static Scanner reader = new Scanner(System.in);
	
	/**
	 * operatorMenu() Allows the operator to select Manage Member or Manage Provider, each of which contains
	 * 				  the Add, Update, and Delete functions for both Members and Providers, respectively.
	 * @param none	
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void operatorMenu() throws FileNotFoundException, IOException {

		int option = 0;
		
		//Operator Menu (main)
		System.out.println("\nOperator Menu");
		System.out.println("1.) Manage Member");
		System.out.println("2.) Manage Provider");
		System.out.println("3.) Go back to main terminal"); 
		System.out.print(" Option: ");
			
		option = reader.nextInt();
		reader.nextLine();
		
		//Manage Member submenu
		if (option == 1) {
			System.out.println("\nManage Member");
			System.out.println("1.) Add Member");
			System.out.println("2.) Delete Member");
			System.out.println("3.) Update Member");
			System.out.println("4.) Go back to main terminal"); 
			System.out.print(" Option: ");
				
			option = reader.nextInt();
			reader.nextLine();
			
			//Add member option
			if (option == 1) { 
			    System.out.println("\nAdd Member");
			    System.out.print("Enter member's name: ");
		        String name = reader.nextLine();
		        System.out.print("Enter member's number: ");
		        int number = reader.nextInt();
		        reader.nextLine();
		        System.out.print("Enter member's address: ");
		        String address = reader.nextLine();
		        System.out.print("Enter member's city: ");
		        String city = reader.nextLine();
		        System.out.print("Enter member's state: ");
		        String state = reader.nextLine();
		        System.out.print("Enter member's zip code: ");
		        int zip = reader.nextInt();
		        reader.nextLine();
		        
			    ManageAccounts.addMember(name,number,address,city,state,zip);
			    System.out.println("\nThe member has been added");
			    return;
			}
			
			//Delete member option
			else if (option == 2) { 
			    System.out.println("\nDelete Member");
		        System.out.print("Enter member's number: ");
		        int number = reader.nextInt();
		        //reader.nextLine();
				ManageAccounts.deleteMember(number);
				System.out.println("\nThe member has been removed");
				return;
			}
			
			//Update member option
			else if (option == 3) {
				System.out.println("\nUpdate Member");
				
				ArrayList <MemberAccounts> members = ReadMember();
		        
		        System.out.print("Enter member's name: ");
		        String name = reader.nextLine();
		        
		        for (MemberAccounts m : members) {
		            if (m.getName().equals(name)) {
		                System.out.print("A member with this name already exists, do you wish to continue? [Y/N]: ");
		                String check = reader.nextLine();
		                if (check.toUpperCase().equals("N")) {
		                    reader.close();
		                    return;
		                }
		                else {
		                	System.out.print("Enter member's number: ");
		                    String number = reader.nextLine();
		                    System.out.print("Enter member's address: ");
		                    String address = reader.nextLine();
		                    System.out.print("Enter member's city: ");
		                    String city = reader.nextLine();
		                    System.out.print("Enter member's state: ");
		                    String state = reader.nextLine();
		                    System.out.print("Enter member's zip code: ");
		                    int zip = reader.nextInt();
		                    reader.nextLine();
		                    ManageAccounts.updateMember(name,Integer.parseInt(number),address,city,state,zip);
		                }
		            }
		        }
				
				System.out.println("\nThe member has been updated");
			}
		}
			
		//Manage Provider submenu
		else if (option == 2) {
			System.out.println("\nManage Provider");
			System.out.println("1.) Add Provider");
			System.out.println("2.) Delete Provider");
			System.out.println("3.) Update Provider");
			System.out.println("4.) Go back to main terminal"); 
			System.out.print(" Option: ");
				
			option = reader.nextInt();
			reader.nextLine();
				
			//Add provider option
			if (option == 1) { 
				System.out.println("\nAdd Provider");
				System.out.print("Enter provider's name: ");
		        String name = reader.nextLine();
		        System.out.print("Enter provider's number: ");
		        int number = reader.nextInt();
		        reader.nextLine();
		        System.out.print("Enter provider's address: ");
		        String address = reader.nextLine();
		        System.out.print("Enter provider's city: ");
		        String city = reader.nextLine();
		        System.out.print("Enter provider's state: ");
		        String state = reader.nextLine();
		        System.out.print("Enter provider's zip code: ");
		        int zip = reader.nextInt();
		        reader.nextLine();
				ManageAccounts.addProvider(name,number,address,city,state,zip);
				
				return;
			}
				
			//Delete provider option
			else if (option == 2) { 
				System.out.println("\nDelete Provider");
				System.out.println("Enter provider's number: ");
		        int number = reader.nextInt();
		        reader.nextLine();
				ManageAccounts.deleteProvider(number);
				return;
			}
				
			//Update provider option
			else if (option == 3) { 
				System.out.println("\nUpdate Provider");
				
				ArrayList <ProviderAccounts> providers = ReadProviders();
		        
		        System.out.print("Enter provider's name: ");
		        String name = reader.nextLine();
		        
		        for (ProviderAccounts p : providers) {
		            if (p.getName().equals(name)) {
		                System.out.println("A provider with this name already exists, do you wish to continue? [Y/N] ");
		                String check = reader.nextLine();
		                if (check.toUpperCase().equals("N")) {
		                    reader.close();
		                    return;
		                }
		                else {
		                	System.out.print("Enter provider's number: ");
		                    String number = reader.nextLine();
		                    System.out.print("Enter provider's address: ");
		                    String address = reader.nextLine();
		                    System.out.print("Enter provider's city: ");
		                    String city = reader.nextLine();
		                    System.out.print("Enter provider's state: ");
		                    String state = reader.nextLine();
		                    System.out.print("Enter provider's zip code: ");
		                    int zip = reader.nextInt();
		                    reader.nextLine();
		                    ManageAccounts.updateProvider(name,Integer.parseInt(number),address,city,state,zip);
		                }
		            }
		        }
				return;
			}
		}
	}
}


