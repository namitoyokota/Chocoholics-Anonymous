/**
 * The Member Accounts class. This class is a entity class for all of the members stored
 * in the system. Each member has attributes: name, number(9 digits), address, city, state, zipcode,
 * status of their account, and a list of services received
 * @author Namito Yokota
 * 
 */

package chocan;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberAccounts {
	
	private String name;
	private int number;	
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private boolean status;
	private ArrayList<ServiceRecord> servicesProvided;
	
	/**
	 * MemberAccounts(), constructor
	 */
	public MemberAccounts () {
		
	}
	
	/**
	 * MemberAccounts() is a constructor with all of the parameters needed to create a new member
	 * @param name, name of the new member
	 * @param number, a unique 9 digit number assigned for the member
	 * @param address, address of the member
	 * @param city, city of the member
	 * @param state, state of the member
	 * @param zipCode, zip code of the member
	 * @param status, status of the member (true=not suspended, false=suspended)
	 * @param servicesProvided, list of services received
	 */
	public MemberAccounts (String name, int number, String address, String city, String state, int zipCode, boolean status, ArrayList<ServiceRecord> servicesProvided ) {
		this.name = name;
		this.number = number;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.status = status;
		this.servicesProvided = servicesProvided;
	}
	
	/**
	 * MemberAccounts() is another constructor for members without any services received
	 * @param name
	 * @param number
	 * @param address
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param status
	 */
	public MemberAccounts (String name, int number, String address, String city, String state, int zipCode, boolean status ) {
		this.name = name;
		this.number = number;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.status = status;
		
	}
	
	/**
	 * setServicesProvided() takes in the list of services provided for the member and stores it
	 * @param servicesProvided
	 */
	public void setServicesProvided(ArrayList<ServiceRecord> servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	/**
	 * setName() sets the name of the member
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * setNumber() sets the unique member number that is passed in
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * setAddress() sets the address of the member
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * setCity() sets the city of the member
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * setState() sets the state of the member
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * setZipCode() sets the zip code for that member
	 * @param zipCode
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * setStatus() sets the status of the member (true = not suspended, false = suspended)
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * getName() returns the name of the member
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getNumber() returns the unique number for the member
	 * @return
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * getAddress() returns the address of the member
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * getCity() returns the city of the member
	 * @return
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * getState() returns the state of the member
	 * @return
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * getZipCode() returns the zip code of the member
	 * @return
	 */
	public int getZipCode() {
		return zipCode;
	}
	
	/**
	 * getStatus() returns the status of the member (true = not suspended, false = suspended)
	 * @return
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * getServicesProvided() returns the list of ServiceRecord that the member has received
	 * @return
	 */
	public ArrayList<ServiceRecord> getServicesProvided() {
		return servicesProvided;
	}
	
	/**
	 * addService() takes a ServiceRecord and adds to the list of ServiceRecord for the member
	 * @param service
	 */
	public void addService(ServiceRecord service) {
		servicesProvided.add(service);
	}	
	
	/**
	   * Comparator for sorting the list by date. It returns the an integer representing the comparison
	   * result. The comparison returns the name closest to 'A'
	   * @param m1, first MemberAccounts for comparison
	   * @param m2, second MemberAccounts for comparison
	   * @return MemberName1.compareTo(MemberName2)
	   */
    public static Comparator<MemberAccounts> MemberNameComparator = new Comparator<MemberAccounts>() {

	public int compare(MemberAccounts m1, MemberAccounts m2) {
	   String MemberName1 = m1.getName().toUpperCase();
	   String MemberName2 = m2.getName().toUpperCase();

	   //ascending order
	   return MemberName1.compareTo(MemberName2);
    }};
}
