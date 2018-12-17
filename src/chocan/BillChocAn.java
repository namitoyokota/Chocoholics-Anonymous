/*
 * Author: Ashley Phan
 */

package chocan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BillChocAn {

  private static Scanner reader = new Scanner(System.in);

  /**
   * billChocan() allows the provider to bill chocan after a service has been provided and updates both member and provider database
   * @param String currentDate, String actDate, int providerNum, int serviceCode, int memberNum
   * @throws FileNotFoundException
   * @throws IOException
   * @return void
   */   
  public static void billChocan(String currentDate, String actDate, int providerNum, int serviceCode, int memberNum)
      throws FileNotFoundException, IOException {
    ArrayList<MemberAccounts> me = Terminal.ReadMember();
    ArrayList<ProviderAccounts> pa = Terminal.ReadProviders();
    ArrayList<ProviderDirectoryEntry> pde = Terminal.ReadProviderDirectoryEntry();
    ServiceRecord sr = new ServiceRecord();

    MemberAccounts found = ManageAccounts.findMember(memberNum);
    ProviderAccounts providerFound = ManageAccounts.findProvider(providerNum);

    if (found.getStatus() == false) {
      reader.close();
      return;
    }

    sr.setCurrentDateTime(currentDate);

    sr.setDateOfService(actDate);
    sr.setProviderNumber(providerNum);
    sr.setMemberNumber(memberNum);
    String ProviderDir = "";
    for (ProviderDirectoryEntry pd : pde) {
    	ProviderDir += pd.getServiceName() + "," + pd.getServiceCode() + "," + pd.getServiceFee()+"\n";
    }
    
    ProviderDirectoryEntry valid = ProviderDirectory.validEntry(serviceCode);
    if (valid == null) {
      reader.close();
      return;
    }
   
    int dialogResult = JOptionPane.showConfirmDialog(null, "The total fee for the service " + valid.getServiceName() + " is $" + valid.getServiceFee()+" Would you like to continue? ", "Attention",JOptionPane.YES_NO_OPTION);
    

    if (dialogResult == JOptionPane.NO_OPTION) {
      reader.close();
      return;
    }
    else if(dialogResult == JOptionPane.YES_OPTION){
    	 sr.setServiceCode(serviceCode);

    	    String comment = JOptionPane.showInputDialog("Enter comments : ");
    	    sr.setComments(comment);

    	    sr.setMemberNumber(found.getNumber());
    	    found.addService(sr);
    	    providerFound.addService(sr);

    	    for (MemberAccounts member : me) {
    	      if (member.getNumber() == found.getNumber()) {
    	        member.addService(sr);
    	        break;
    	      }
    	    }
    	    
    	    for (ProviderAccounts provider : pa) {
      	      if (provider.getNumber() == providerFound.getNumber()) {
      	        provider.addService(sr);
      	        break;
      	      }
      	    }
    	    
    	    ManageAccounts.printProviders(pa);
    	    printBill(me);
    }
   
    return;
  }
  
  /**
   * getProviderDirectoryInfo() returns a string of each Provider Directory entry
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return string
   */  
  public static String getProviderDirectoryInfo() throws FileNotFoundException, IOException{
	  ArrayList<ProviderDirectoryEntry> pde = Terminal.ReadProviderDirectoryEntry();
	  String providerDir = "";
	    for (ProviderDirectoryEntry pd : pde) {
	    	providerDir += "Service Name: "+pd.getServiceName() + ", Code: " + pd.getServiceCode() + ", Fee: $" + pd.getServiceFee()+"\n";
	    }
	    //JOptionPane.showMessageDialog(null,ProviderDir);
	    return providerDir;
  }

  /**
   * printBill() updates the member database by adding the new service provided
   * @param ArrayList of memberAccounts
   * @throws FileNotFoundException
   * @throws IOException
   * @return none
   */  
  public static void printBill(ArrayList<MemberAccounts> ma)
      throws FileNotFoundException, IOException {
    String filename = "Database/member.txt";

    File file = new File(filename);

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
    JOptionPane.showMessageDialog(null,"The billed report was successfully generated and stored in member.txt");
  }  

}
