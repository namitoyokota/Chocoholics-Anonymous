package chocan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * ManageAccounts: Main controller class for OperatorTerminal. Lets operator add, update, and delete
 * members and providers in the database.
 *
 * @author Hunter Esposito
 * @version 1.0
 */
public class ManageAccounts {

  private static Scanner reader = new Scanner(System.in);
  public static MemberAccounts ma = new MemberAccounts();
  public static ProviderAccounts pacc = new ProviderAccounts();


  /**
   * Function to add a new member to the database
   *
   * @param name New member's name
   * @param number New member's member number
   * @param address New member's home address
   * @param city New member's home city
   * @param state New member's home state
   * @param zip New members home zip code
   * @return Returns a boolean, true if member was added successfully, false if member number is
   *         longer than 9 digits.
   * @throws FileNotFoundException
   * @throws IOException
   * @throws IllegalArgumentException
   */
  public static boolean addMember(String name, int number, String address, String city,
      String state,
      int zip) throws FileNotFoundException, IOException, IllegalArgumentException {
    ArrayList<MemberAccounts> members = Terminal.ReadMember();
    boolean flag = false;
    ma.setName(name);

    String temp = Integer.toString(number);

    int exists = validMember(number);
    if (exists == 1) {
      throw new IllegalArgumentException("The member number alreay exists!");
    }

    if (temp.length() != 9) {
      throw new IllegalArgumentException("The member number must be 9 digits long!");
    }

    ma.setNumber(number);
    ma.setAddress(address);
    ma.setCity(city);
    ma.setState(state);
    ma.setZipCode(zip);
    ma.setStatus(true);

    members.add(ma);
    printMember(members);
    flag = true;

    return flag;
  }


  /**
   * Function to delete a member from the database
   *
   * @param number Member number to be found
   * @throws FileNotFoundException
   * @throws IOException
   * @throws IllegalArgumentException if number is greater than or less than 9 digits
   */
  public static void deleteMember(int number)
      throws FileNotFoundException, IOException, IllegalArgumentException {
    ArrayList<MemberAccounts> members = Terminal.ReadMember();
    int found = -1;

    String temp = Integer.toString(number);

    if (temp.length() != 9) {
      throw new IllegalArgumentException("The member number must be 9 digits long!");
    }

    for (MemberAccounts m : members) {
      if (m.getNumber() == number) {
        members.remove(m);
        found = 1;
        break;
      }
    }

    if (found == -1) {
      reader.close();
      throw new IllegalArgumentException("The member does not exist!");
    }

    printMember(members);

  }


  /**
   * Function to update an existing members information
   *
   * @param name member's updated name
   * @param number member's updated member #
   * @param address member's updated address
   * @param city member's updated city
   * @param state member's updated state
   * @param zip member's updated zip code
   * @return An int as a return code
   * @throws FileNotFoundException
   * @throws IOException
   * @throws IllegalArgumentException if member number is not 9 digits long
   */
  public static int updateMember(String name, int number, String address, String city, String state,
      int zip)
      throws FileNotFoundException, IOException, IllegalArgumentException {
    ArrayList<MemberAccounts> members = Terminal.ReadMember();
    String zipcode = Integer.toString(zip);
    String numberStr = Integer.toString(number);
    int output = 0;

    if (name.equals("") || address.equals("") || city.equals("") || state.equals("")) {
      output = -1;
      throw new IllegalArgumentException("Input fields cannot be empty!");
    } else if (zipcode.length() != 5) {
      output = -2;
      throw new IllegalArgumentException("Zip Code must be 5 digits long!");
    } else if (numberStr.length() != 9) {
      output = -3;
      throw new IllegalArgumentException("Member number must be 9 digits long!");
    }

    for (MemberAccounts m : members) {
      if (m.getNumber() == number) {
        m.setName(name);
        m.setNumber(m.getNumber());
        m.setAddress(address);
        m.setCity(city);
        m.setState(state);
        m.setZipCode(zip);
      }
    }
    printMember(members);

    return output;
  }


  /**
   * Function to print the provided list of members
   *
   * @param ma Member account to print
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void printMember(ArrayList<MemberAccounts> ma)
      throws FileNotFoundException, IOException {
    String filename = "Database/member.txt";

    File file = new File(filename);
	      /*Path oPath = Paths.get(filename);
	      File oFile = oPath.toFile();*/
    try (BufferedWriter input = new BufferedWriter(new FileWriter(file))) {
      for (MemberAccounts member : ma) {
        input.write(
            member.getName() + "," + member.getNumber() + "," + member.getAddress() + "," + member
                .getCity() + "," + member.getState() + "," + member.getZipCode() + "," + member
                .getStatus() + "\n");

        ArrayList<ServiceRecord> sr = member.getServicesProvided();
        if (sr != null) {
          for (ServiceRecord service : sr) {
            input.write("s," + service.getCurrentDateTime() + "," + service.getDateOfService() + ","
                + service.getProviderNumber() + "," + service.getMemberNumber() + "," + service
                .getServiceCode() + "," + service.getComments() + "\n");
          }
        }
      }
      input.close();
    }
  }


  /**
   * Function to verify if a specific member exists in the database
   *
   * @param number Member number to find
   * @return returns a 1 if member is found, 0 otherwise
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static int validMember(int number) throws FileNotFoundException, IOException {
    ArrayList<MemberAccounts> members = Terminal.ReadMember();

    int found = 0;
    for (MemberAccounts m : members) {
      if (m.getNumber() == number) {
        found = 1;
        break;
      }
    }
    return found;
  }


  /**
   * Function to add a new provider to the database
   *
   * @param name Provider name
   * @param number Provider number
   * @param address Provider address
   * @param city Provider city
   * @param state Provider state
   * @return returns a boolean, true if provider was added successfully, false otherwise
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static boolean addProvider(String name, int number, String address, String city,
      String state,
      int zip) throws FileNotFoundException, IOException {
    ArrayList<ProviderAccounts> providers = Terminal.ReadProviders();
    boolean flag = false;

    String pn = Integer.toString(number);

    int exists = validProvider(number);
    if (exists == 1) {
      throw new IllegalArgumentException("The provider number alreay exists!");
    }

    if (pn.length() != 9) {
      throw new IllegalArgumentException("The provider number must be 9 digits long!");
    }

    pacc.setName(name);
    pacc.setNumber(number);
    pacc.setAddress(address);
    pacc.setCity(city);
    pacc.setState(state);
    pacc.setZipCode(zip);

    providers.add(pacc);
    printProviders(providers);
    flag = true;

    return flag;

  }


  /**
   * Function to delete a provider from the database
   *
   * @param number Provider number to find
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void deleteProvider(int number) throws FileNotFoundException, IOException {
    ArrayList<ProviderAccounts> providers = Terminal.ReadProviders();

    int found = -1;
    for (ProviderAccounts p : providers) {
      if (p.getNumber() == number) {
        providers.remove(p);
        found = 1;
        break;
      }
    }

    if (found == -1) {
    	throw new IllegalArgumentException("The provider does not exist!");  
      
    }

    printProviders(providers);
    //JOptionPane.showMessageDialog(null, "The provider was sucessfully deleted");
  }

  /**
   * Function to update a specific provider's information
   *
   * @param name Updated provider name
   * @param number Updated provider number
   * @param address Updated provider address
   * @param city Updated provider city
   * @param state Updated provider state
   * @param zip Updated provider zip code
   * @return Returns a return code int
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static int updateProvider(String name, int number, String address, String city,
      String state, int zip)
      throws FileNotFoundException, IOException {
    ArrayList<ProviderAccounts> providers = Terminal.ReadProviders();
    String zipcode = Integer.toString(zip);
    String numberStr = Integer.toString(number);
    int output = 0;

    
      if (name.equals("") || address.equals("") || city.equals("") || state.equals("")) {
        output = -1;
        throw new IllegalArgumentException("Input fields cannot be empty!");
      } else if (zipcode.length() != 5) {
        output = -2;
        throw new IllegalArgumentException("Zip Code must be 5 digits long!");
      } else if (numberStr.length() != 9) {
        output = -3;
        throw new IllegalArgumentException("Provider number must be 9 digits long!");
      }
    

    for (ProviderAccounts p : providers) {
      if (p.getNumber() == number) {
        p.setName(name);
        p.setNumber(p.getNumber());
        p.setAddress(address);
        p.setCity(city);
        p.setState(state);
        p.setZipCode(zip);

        printProviders(providers);
      }
    }
    return output;
  }


  /**
   * Helper function that verifies if a provider exists in the directory or not
   *
   * @param number the provider number to be found
   * @return 1 if found, 0 otherwise
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static int validProvider(int number) throws FileNotFoundException, IOException {
    ArrayList<ProviderAccounts> provider = Terminal.ReadProviders();

    int found = 0;
    for (ProviderAccounts p : provider) {
      if (p.getNumber() == number) {
        found = 1;
        break;
      }
    }
    return found;
  }


  /**
   * Prints the list of providers and the corresponding Service Records
   *
   * @param pa the list of providers to be printed
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void printProviders(ArrayList<ProviderAccounts> pa)
      throws FileNotFoundException, IOException {
    String filename = "Database/provider.txt";
    Path oPath = Paths.get(filename);

    File oFile = oPath.toFile();
    try (BufferedWriter input = new BufferedWriter(new FileWriter(oFile))) {
      for (ProviderAccounts provider : pa) {
        input.write(
            provider.getName() + "," + provider.getNumber() + "," + provider.getAddress() + ","
                + provider.getCity() + "," + provider.getState() + "," + provider.getZipCode()
                + "\n");

        ArrayList<ServiceRecord> sr = provider.getServicesProvided();
        if (sr != null) {
          for (ServiceRecord service : sr) {
            input.write("s," + service.getCurrentDateTime() + "," + service.getDateOfService() + ","
                + service.getProviderNumber() + "," + service.getMemberNumber() + "," + service
                .getServiceCode() + "," + service.getComments() + "\n");
          }
        }
      }
      input.close();
    }
  }


  /**
   * Function to find a member by member number in the database
   *
   * @param memNum the member to find
   * @return returns the specified member object, empty if member was not found.
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static MemberAccounts findMember(int memNum) throws FileNotFoundException, IOException {
    ArrayList<MemberAccounts> me = Terminal.ReadMember();
    MemberAccounts ma = new MemberAccounts();

    for (MemberAccounts account : me) {
      if (account.getNumber() == memNum) {
        ma = account;
      }
    }

    return ma;
  }
  
  public static ProviderAccounts findProvider(int proNum) throws FileNotFoundException, IOException {
	    ArrayList<ProviderAccounts> lpa = Terminal.ReadProviders();
	    ProviderAccounts pa = new ProviderAccounts();

	    for (ProviderAccounts account : lpa) {
	      if (account.getNumber() == proNum) {
	        pa = account;
	      }
	    }

	    return pa;
	  }
}


