// Namito Yokota

package JUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import chocan.ManageAccounts;

public class AddProviderTest {

    @Before
    public void setUp() throws Exception {
    	if(1 == ManageAccounts.validProvider(256256256))
    		ManageAccounts.deleteProvider(256256256);
    	if(1 == ManageAccounts.validProvider(303030303))
    		ManageAccounts.deleteProvider(303030303);
    }

    @Test
    public void testAddProvider() throws FileNotFoundException, IOException,IllegalArgumentException{
    	ManageAccounts.addProvider("Namito Yokota", 256256256, "100 Mayfair Ct.", "Madison", "AL", 35758);
    	assertEquals(1, ManageAccounts.validProvider(256256256));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFailAddProvider() throws FileNotFoundException, IOException,IllegalArgumentException{
    	ManageAccounts.addProvider("Eddie Jackson", 44444444, "Eddie Street", "Tuscaloosa", "AL", 44444);
    }

    @Test
    public void testAddProvider2()throws FileNotFoundException, IOException,IllegalArgumentException{
    	ManageAccounts.addProvider("Stephen Curry", 303030303, "Curry St.", "Tuscaloosa", "AL", 30000);
    	assertEquals(1, ManageAccounts.validProvider(303030303));
    }
    
}
