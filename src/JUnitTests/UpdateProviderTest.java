//Josh Engler
package JUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import chocan.ManageAccounts;

public class UpdateProviderTest {

	@Before
	public void setUp() throws Exception {
	}

	//Test for success
	@Test
	public void testSuccessUpdateProvider() throws FileNotFoundException, IOException {
		ManageAccounts.updateProvider("Rubeus Hagrid", 123456789, "1 Forbidden Forest Drive", "Hogwarts", "Scotland", 77777);
		assertEquals(1, ManageAccounts.validProvider(123456789));
	}
	
	//Test for failure (empty string inputs)
	@Test (expected = IllegalArgumentException.class)
	public void testStringFailureUpdateProvider() throws FileNotFoundException, IOException, IllegalArgumentException {
		ManageAccounts.updateProvider("", 123456789, "", "", "", 77777);
	}
	
	//Test for failure (Zip code length != 5)
	@Test (expected = IllegalArgumentException.class)
	public void testZipFailureUpdateProvider() throws FileNotFoundException, IOException, IllegalArgumentException {
		ManageAccounts.updateProvider("Rubeus Hagrid", 123456789, "1 Forbidden Forest Drive", "Hogwarts", "Scotland", 777777);
	}
	
	//Test for failure (Provider number length != 9)
	@Test (expected = IllegalArgumentException.class)
	public void testNumFailureUpdateProvider() throws FileNotFoundException, IOException, IllegalArgumentException {
		ManageAccounts.updateProvider("Rubeus Hagrid", 1234567890, "1 Forbidden Forest Drive", "Hogwarts", "Scotland", 77777);
	}

}
