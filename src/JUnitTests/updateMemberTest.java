/*
 * Author: Conrad Spear
 * Tests the functionality of updateMember
 */

package JUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import chocan.ManageAccounts;

public class updateMemberTest {

	@Before
	public void setUp() throws Exception {
	}

	//Test Success
	@Test
    public void testSuccessChangeAddressUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("Ronald Weasley",676767676,"221B Baker Street","Belk","Alabama",31902);
        assertEquals(1,ManageAccounts.validMember(676767676));        
    }
	
	//Test Success
	@Test
    public void testSuccessRevertAddressUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("Ronald Weasley",676767676,"679 West 24th Street","New York","New York",31902);
        assertEquals(1,ManageAccounts.validMember(676767676));
    }
	
	//Test Fail for empty Name
    @Test (expected = IllegalArgumentException.class)
    public void testFailEmptyNameUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("",123456789,"1234 Hogwarts Express","San Diego","California",30128);
    }

	//Test Fail for empty Address
    @Test (expected = IllegalArgumentException.class)
    public void testFailEmptyAddressUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("Harry Potter",123456789,"","San Diego","California",30128);
    }
    
    //Test Fail for empty City
    @Test (expected = IllegalArgumentException.class)
    public void testFailEmptyCityUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("Harry Potter",123456789,"1234 Hogwarts Express","","California",30128);
    }
    
    //Test Fail for empty State
    @Test (expected = IllegalArgumentException.class)
    public void testFailEmptyStateUpdateMember() throws FileNotFoundException, IOException,IllegalArgumentException{
        ManageAccounts.updateMember("Harry Potter",123456789,"1234 Hogwarts Express","San Diego","",30128);
    }
    
    //Test Fail for invalid zip code of 3 digits
    @Test (expected = IllegalArgumentException.class)
    public void testFailInvalidZipUpdateMember()throws FileNotFoundException, IOException,IllegalArgumentException{
    	ManageAccounts.updateMember("Harry Potter",123456789,"1234 Hogwarts Express","San Diego","California",301);
    }
    
    //Test Fail for invalid member number of 3 digits
    @Test (expected = IllegalArgumentException.class)
    public void testFailInvalidMemberNumberUpdateMember()throws FileNotFoundException, IOException,IllegalArgumentException{
    	ManageAccounts.updateMember("Harry Potter",123,"1234 Hogwarts Express","San Diego","California",30128);
    }
    

}
