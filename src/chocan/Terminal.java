/*
 * Author: Ashley Phan
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

import javax.swing.JOptionPane;

public class Terminal {
	
	private static Scanner reader = new Scanner(System.in);
  
  
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int option=0;
		
		while(option != 4) {
			System.out.println("\nChocAn System Menu");
			System.out.println("1.) Open Manager Terminal"); 
			System.out.println("2.) Open Provider Terminal"); 
			System.out.println("3.) Open Operator Terminal");
			System.out.println("4.) Quit");
			System.out.print(" Option: ");
			
			while(!reader.hasNext());
		
			if(reader.hasNext()) {
	            option = reader.nextInt();
	            //reader.next();
	        }
			
			
			if (option == 1) { // manager terminal
				OpenManagerTerminal();
				option = 0;
			}
			
			else if (option == 2) { // provider terminal
				OpenProviderTerminal();
				option = 0;
			}
			
			else if (option == 3) { // operator terminal
				OpenOperatorTerminal();
				option = 0;
			}
		}
		
		System.out.println("\nEnd");
  }
  
	/**
	   * OpenManagerTerminal() calls managerMenu
	   * @param none
	   * @throws FileNotFoundException
	   * @throws IOException
	   * @return void
	   */ 
  public static void OpenManagerTerminal () throws FileNotFoundException, IOException {
	  ManagerTerminal.managerMenu();
	  
  }
  
  /**
   * OpenProviderTerminal() calls provideTerminal
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return void
   */ 
  public static void OpenProviderTerminal () throws FileNotFoundException, IOException {
	  ProviderTerminal.providerMenu();
  }
  
  /**
   * OpenOperatorTerminal() calls operatorMenu
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return void
   */ 
  public static void OpenOperatorTerminal () throws FileNotFoundException, IOException {
	  OperatorTerminal.operatorMenu();
  }
  
  
  /**
   * ReadProviderDirectoryEntry() reads in the providerDirectory.txt and stores each line as a ProviderDirectoryEntry and returns
   * an ArrayList of these providerDirectoryEntries
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return ArrayList of ProviderDirectoryEntry
   */ 
  public static ArrayList<ProviderDirectoryEntry> ReadProviderDirectoryEntry() throws FileNotFoundException, IOException {
      String filename = "Database/providerDirectory.txt";
      Path pPath = Paths.get(filename);
    
      File mFile = pPath.toFile();
  
      
      ArrayList<ProviderDirectoryEntry> listPde = new ArrayList<ProviderDirectoryEntry>();
      
      if(Files.exists(pPath)){
          try(BufferedReader input = new BufferedReader(new FileReader(mFile))){
              String line = input.readLine();
          
              while (line != null && !line.equals("")) {
                  String[] fields = line.split(",");
                  ProviderDirectoryEntry entry = new ProviderDirectoryEntry();
                  
                  entry.setServiceName(fields[0]);
                  entry.setServiceCode(Integer.parseInt(fields[1]));
                  entry.setServiceFee(Integer.parseInt(fields[2]));
                  
                  listPde.add(entry);
                  line = input.readLine();
              }
              input.close();
          }
      }
      Collections.sort(listPde, ProviderDirectoryEntry.ServiceNameComparator);
      return listPde;
  }
  
  
  /**
   * ReadMember() reads in the member.txt database and creates a new memberAccount for each member, along with the list of services
   * they've been provided. This returns an arraylist of all the members in the database.
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return ArrayList of MemberAccounts
   */ 
  public static ArrayList<MemberAccounts> ReadMember() throws FileNotFoundException, IOException{
      String filename = "Database/member.txt";
      Path mPath = Paths.get(filename);
      
      File mFile = mPath.toFile();
      
      ArrayList<MemberAccounts> listMemA = new ArrayList<MemberAccounts>();
      
      if(Files.exists(mPath)){
          try(BufferedReader input = new BufferedReader(new FileReader(filename))){
               String line = input.readLine();
              while(line != null && !line.equals("")){
                  ArrayList<ServiceRecord> servicesProvided = new ArrayList<ServiceRecord>();
                  String[] fields = line.split(",");

                      MemberAccounts memA = new MemberAccounts();
                      memA.setName(fields[0]); 
                      memA.setNumber(Integer.parseInt(fields[1]));
                      memA.setAddress(fields[2]);
                      memA.setCity(fields[3]);
                      memA.setState(fields[4]);
                      memA.setZipCode(Integer.parseInt(fields[5]));
                      memA.setStatus(Boolean.parseBoolean(fields[6]));
                  
                  
                  line = input.readLine();
                  if (line == null) {
                      listMemA.add(memA);
                      break;
                  }
                  String[] serviceFields = line.split(",");
                  
                  while (serviceFields[0].equals("s") == true && serviceFields != null) {
                      ServiceRecord sr = new ServiceRecord();
                      sr.setCurrentDateTime(serviceFields[1]);
                      sr.setDateOfService(serviceFields[2]);
                      sr.setProviderNumber(Integer.parseInt(serviceFields[3]));
                      sr.setMemberNumber(Integer.parseInt(serviceFields[4]));
                      sr.setServiceCode(Integer.parseInt(serviceFields[5]));
                      if(serviceFields.length == 7)sr.setComments(serviceFields[6]);
                      servicesProvided.add(sr);
                      line = input.readLine();
                      if (line == null) break;
                      serviceFields = line.split(",");   
                  }

                  memA.setServicesProvided(servicesProvided);
                  listMemA.add(memA);
              }
              
              input.close();
          }
      }
      Collections.sort(listMemA, MemberAccounts.MemberNameComparator);
      return listMemA;
  }
  
  
  /**
   * ReadProviders() reads in the provider.txt database and creates a new ProviderAccount for each provider, along with the list of services
   * they've provided. This returns an arraylist of all the providers in the database.
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return ArrayList of Providers
   */ 
  public static ArrayList<ProviderAccounts> ReadProviders() throws FileNotFoundException, IOException{
      String filename = "Database/provider.txt";
      Path mPath = Paths.get(filename);
      
      File mFile = mPath.toFile();
      
      ArrayList<ProviderAccounts> listProvA = new ArrayList<ProviderAccounts>();
      
      if(Files.exists(mPath)){
          try(BufferedReader input = new BufferedReader(new FileReader(filename))){
               String line = input.readLine();
              while(line != null && !line.equals("")){
                  ArrayList<ServiceRecord> servicesProvided = new ArrayList<ServiceRecord>();
                  String[] fields = line.split(",");

                      ProviderAccounts provA = new ProviderAccounts();
                      provA.setName(fields[0]); 
                      provA.setNumber(Integer.parseInt(fields[1]));
                      provA.setAddress(fields[2]);
                      provA.setCity(fields[3]);
                      provA.setState(fields[4]);
                      provA.setZipCode(Integer.parseInt(fields[5]));
                      
                  
                  
                  line = input.readLine();
                  if (line == null) {
                      listProvA.add(provA);
                      break;
                  }
                  String[] serviceFields = line.split(",");
                  
                  while (serviceFields[0].equals("s") == true && serviceFields != null) {
                      ServiceRecord sr = new ServiceRecord();
                      sr.setCurrentDateTime(serviceFields[1]);
                      sr.setDateOfService(serviceFields[2]);
                      sr.setProviderNumber(Integer.parseInt(serviceFields[3]));
                      sr.setMemberNumber(Integer.parseInt(serviceFields[4]));
                      sr.setServiceCode(Integer.parseInt(serviceFields[5]));
                      if(serviceFields.length == 7)sr.setComments(serviceFields[6]);
                      servicesProvided.add(sr);
                      line = input.readLine();
                      if (line == null) break;
                      serviceFields = line.split(",");   
                  }

                  provA.setServicesProvided(servicesProvided);
                  listProvA.add(provA);
                  
              }
              
              input.close();
          }
      }
      return listProvA;
  }



  /**
   * rewriteDirectory() writes the providerDirectory to ProviderDirectory.txt. This is used for when the provider requests the
   * providerDirectory
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return void
   */ 
  public static void rewriteDirectory() throws FileNotFoundException, IOException{
      ArrayList<ProviderDirectoryEntry> pde = ReadProviderDirectoryEntry();

      String filename = "writtenFiles/ProviderDirectory/ProviderDirectory.txt";

      Path oPath = Paths.get(filename);
      
      File oFile = oPath.toFile();
      
      try(BufferedWriter input = new BufferedWriter(new FileWriter(oFile))){
          for (ProviderDirectoryEntry entry : pde) {
              input.write("Service Name: "+entry.getServiceName()+","+" Service Code: "+entry.getServiceCode()+","+" Service Fee: $"+entry.getServiceFee()+"\n");
          }
          input.close();
      }
      
      JOptionPane.showMessageDialog(null, "The provider directory has been saved to ProviderDirectory.txt within writtenFiles/ProviderDirectory");

      
  }
}


