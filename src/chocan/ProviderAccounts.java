/**
 * The Provider Accounts class. This class is a entity class for all of the providers stored
 * in the system. Each provider has attributes: name, number(9 digits), address, city, state, zipcode,
 * and a list of services provided
 * @author Namito Yokota
 * 
 */

package chocan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProviderAccounts {
	private String name;
	private int number;	
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private ArrayList<ServiceRecord> servicesProvided;
	
	/**
	 * ProviderAccounts(), constructor
	 */
	public ProviderAccounts () {
		
	}
	
	/**
	 * ProviderAccounts() is a constructor that sets all of the parameters passed in to this entity class
	 * @param name, String input of the name for the provider
	 * @param number, a unique 9 digit number given for the provider
	 * @param address, String input for the provider's address
	 * @param city, String input for provider's city
	 * @param state, String input for provider's city
	 * @param zipCode, an integer input for the provider's zip code 
	 */
	public ProviderAccounts (String name, int number, String address, String city, String state, int zipCode) {
		this.name = name;
		this.number = number;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	/**
	 * setName() sets the name of the provider that is passed in
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * setNumber() sets the number given for that provider
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * setAddress() sets the address for that provider
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * setCity() sets the city for that provider
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * setStates() sets the state for that provider
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * setZipCode() sets the zip code for that provider
	 * @param zipCode
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * getName() returns the name of the provider
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getNumber() returns the number of the provider
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * getAddress() returns the address of the provider
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * getCity() returns the city of the provider
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * getState() returns the state of the provider
	 * @return state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * getZipCode() returns the zip code of the provider
	 * @return zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}
	
	/**
	 * getNumberServices() returns the number of services that the provider has provided
	 * @return servicesProvided.size()
	 */
	public int getNumberServices() {
	    if (servicesProvided.size() == 0) return 0;
	    else {
	        return servicesProvided.size();
	    }
	    
	}
	
	/**
	 * getTotalFees() returns the total fees 
	 * @param services
	 * @return fee
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public double getTotalFees(ArrayList<ServiceRecord> services) throws FileNotFoundException,IOException{
	    double fee = 0;
	    for(ServiceRecord se : services) {
	        fee = fee + se.getServiceFee(se.getServiceCode());
	    }
	    return fee;
	}
	
	/**
	 * getServicesProvided() returns the list of ServiceRecord that the provider has provided
	 * @return servicesProvided
	 */
	public ArrayList<ServiceRecord> getServicesProvided() {
		return servicesProvided;
	}
	
	/**
	 * addService() takes a ServiceRecord input and adds to the list of ServiceRecord for the provider
	 * @param service
	 */
	public void addService(ServiceRecord service){
		servicesProvided.add(service);
	}
	
	/**
	 * setServicesProvided() takes in the list of sericesProvided and stores it in this class
	 * @param servicesProvided
	 */
	public void setServicesProvided(ArrayList<ServiceRecord> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }
}
