/**
 * The Provider Terminal class. This provides an interface for the provider to interact with.
 * Three actions can be performed: validate a chocan member, bill chocan, or request a provider directory
 * @author Namito Yokota
 * 
 */

package chocan;
import java.nio.file.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

//import com.sun.corba.se.spi.ior.Writeable;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class ProviderTerminal extends Terminal {
  
  private static Scanner reader = new Scanner(System.in);
  
  /**
   * providerMenu() prompts the provider to pick an action to be performed
   * @param none
   * @return void
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void providerMenu() throws FileNotFoundException, IOException {
	System.out.println("\nProvider Terminal");
	System.out.println("1.) Validate a ChocAn member"); 
	System.out.println("2.) Bill ChocAn"); 
	System.out.println("3.) Request a Provider Directory"); 
	System.out.println("4.) Go back to main terminal");
	System.out.print(" Option: ");
	
    int option = reader.nextInt();
    reader.nextLine();
    
    if (option == 1) { // validate member
    	System.out.println("Enter member number: ");
    	int memNum = reader.nextInt();
    	reader.nextLine();
    	ValidateMember.validateMember(memNum);
    }
    
    else if (option == 2) { // bill chocan
    	System.out.println("Enter date of service (MM-DD-YYYY): ");
        String actDate = reader.nextLine();
        System.out.println("Enter provider number: ");
        int providerNum = reader.nextInt();
        reader.nextLine();
        System.out.println("Enter the six-digit service code: ");
        int serviceCode = reader.nextInt();
        System.out.println("Enter member number: ");
        int memberNum = reader.nextInt();
        reader.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date);
    	BillChocAn.billChocan(strDate, actDate, providerNum, serviceCode, memberNum);
    }
    
    else if (option == 3) { // request provider directory
    	RequestProviderDirectory.requestDirectory();
    }
  }
  
}
