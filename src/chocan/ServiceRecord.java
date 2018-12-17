/**
 * The Service Record class. This class is a entity class for the services provided by a provider for
 * a member. Each service record stores the current time that the information was entered, date that the
 * service was provided, provider number of the service, member number of the service, service code,
 * and comments as an option
 * @author Namito Yokota
 * 
 */

package chocan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ServiceRecord extends Terminal {
	
  private String currentDateTime;
  private String dateOfService;
  private int providerNumber;
  private int memberNumber;
  private int serviceCode;
  private String comments;
  
  /**
   * ServiceRecord(), a constructor
   */
  public ServiceRecord(){
	  
  }
  
  /**
   * ServiceRecord() is a constructor that stores all of the variables passed in
   * @param currentDateTime, current date and time that the information was submitted
   * @param dateOfService, date that the service was provided
   * @param providerNumber, a unique number of the provider that provided the service (9 digits)
   * @param memberNumber, a unique number of the member that receive the service (9 digits)
   * @param serviceCode, a unique number for that service (6 digits)
   * @param comments, optional field to enter additional information
   */
  public ServiceRecord(String currentDateTime, String dateOfService, int providerNumber, int memberNumber,
			int serviceCode, String comments) {
		super();
		this.currentDateTime = currentDateTime;
		this.dateOfService = dateOfService;
		this.providerNumber = providerNumber;
		this.memberNumber = memberNumber;
		this.serviceCode = serviceCode;
		this.comments = comments;
  }
  
  /**
   * getCurrentDateTime() returns currentDateTime for the service
   * @return currentDateTime
   */
  public String getCurrentDateTime() {
	return currentDateTime;
  }

  /**
   * setCurrentDateTime() sets currentDateTime for the service
   * @param currentDateTime
   */
  public void setCurrentDateTime(String currentDateTime) {
	this.currentDateTime = currentDateTime;
  }

  /**
   * getDateOfService() returns the date of service
   * @return dateOfService
   */
  public String getDateOfService() {
	return dateOfService;
  }

  /**
   * setDateOfService() sets date that the service was provided
   * @param dateOfService
   */
  public void setDateOfService(String dateOfService) {
	this.dateOfService = dateOfService;
  } 
  
  /**
   * getProviderName() takes the provider number and searches for the provider to return the their name
   * @param providerNumber
   * @return name, name of the provider found or null if not found
   * @throws FileNotFoundException
   * @throws IOException
   */
  public String getProviderName(int providerNumber) throws FileNotFoundException, IOException{
    ArrayList<ProviderAccounts> pa = ReadProviders();
    String name = null; 
    
    for (ProviderAccounts provide : pa) {
        if (provide.getNumber() == providerNumber) {
            name = provide.getName();
        }
    }
    return name;
  }
  
  /**
   * getMemberName() takes the member number and searches for the member to return their name
   * @param memberNumber
   * @return name, name of the member or null if not found
   * @throws FileNotFoundException
   * @throws IOException
   */
  public String getMemberName(int memberNumber) throws FileNotFoundException,IOException{
      ArrayList<MemberAccounts> ma = ReadMember();
      String name = null; 
      
      for (MemberAccounts member : ma) {
          if (member.getNumber() == memberNumber) {
              name = member.getName();
          }
      }
      return name;  
	
  }
  

  /**
   * getProviderNumber() returns the provider number
   * @return providerNumber
   */
  public int getProviderNumber() {
	return providerNumber;
  }

  /**
   * setProviderNumber() sets the provider number
   * @param providerNumber
   */
  public void setProviderNumber(int providerNumber) {
	this.providerNumber = providerNumber;
  }

  /**
   * getMemberNumber() returns the member number
   * @return memberNumber
   */
  public int getMemberNumber() {
	return memberNumber;
  }

  /**
   * setMemberNumber() sets the member number
   * @param memberNumber
   */
  public void setMemberNumber(int memberNumber) {
	this.memberNumber = memberNumber;
  }

  /**
   * getServiceCode() returns the service code
   * @return serviceCode
   */
  public int getServiceCode() {
	return serviceCode;
  }

  /**
   * setServiceCode() sets the service code
   * @param serviceCode
   */
  public void setServiceCode(int serviceCode) {
	this.serviceCode = serviceCode;
  }

  /**
   * getComments() returns the comments
   * @return comments
   */
  public String getComments() {
	return comments;
  }

  /**
   * setComments() sets the comments
   * @param comments
   */
  public void setComments(String comments) {
	this.comments = comments;
  }
  
  /**
   * getServiceName() takes in a service code and looks up the service to return its name
   * @param serviceCode
   * @return name
   * @throws FileNotFoundException
   * @throws IOException
   */
  public String getServiceName(int serviceCode) throws FileNotFoundException, IOException{
   ArrayList<ProviderDirectoryEntry>pde = ReadProviderDirectoryEntry();
   String name = null;
   for (ProviderDirectoryEntry provide : pde) {
       if (provide.getServiceCode() == serviceCode) {
           name = provide.getServiceName();
       }
   }
   return name;
  }

  /**
   * getServiceFee() takes in a service code and searches the service to return its fee
   * @param serviceCode
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public double getServiceFee(int serviceCode) throws FileNotFoundException, IOException{
    ArrayList<ProviderDirectoryEntry>pde = ReadProviderDirectoryEntry();
    double fee =0;
    for (ProviderDirectoryEntry provide : pde) {
        if (provide.getServiceCode() == serviceCode) {
            fee = provide.getServiceFee();
        }
    }
    return fee;
	}

  
  /**
   * Comparator for sorting the list by date. It returns the an integer representing the comparison
   * result. The comparison returns the earliest date
   * @param s1, first ServiceRecord for comparison
   * @param s2, second ServiceRecord for comparison
   * @return date1.compareTo(date2)
   */
  public static Comparator<ServiceRecord> ServiceDateComparator = new Comparator<ServiceRecord>() {
	
	  public int compare(ServiceRecord s1, ServiceRecord s2) {
		  Date date1 = new Date();
		  try {
			  date1 = new SimpleDateFormat("MM-dd-yyyy").parse(s1.getDateOfService());
		  } catch (ParseException e1) {
			  // TODO Auto-generated catch block
			  e1.printStackTrace();
		  }
		  Date date2 = new Date();
		  try {
			date2 = new SimpleDateFormat("MM-dd-yyyy").parse(s2.getDateOfService());
		  } catch (ParseException e) {
			  // TODO Auto-generated catch block
			e.printStackTrace();
		  }
		
		  //ascending order
		  return date1.compareTo(date2);	  
  }};
}

