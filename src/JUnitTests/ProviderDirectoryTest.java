package JUnitTests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import chocan.ProviderDirectory;
import chocan.ProviderDirectoryEntry;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Hunter Esposito
 *
 */
public class ProviderDirectoryTest {

  ProviderDirectoryEntry pd, test;
  int code = 111111;

  @Before
  public void setUp() throws Exception {
    pd = new ProviderDirectoryEntry("Broom Workout", code, 300.0);
  }

  @Test
  public void validEntrySuccess() throws FileNotFoundException, IOException {
    test = ProviderDirectory.validEntry(code);
    assertEquals(pd.getServiceName(), test.getServiceName());
  }

  @Test
  public void validEntryFailure() throws FileNotFoundException, IOException {
    assertNull(ProviderDirectory.validEntry(122222).getServiceName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void validEntryIllegalCode() throws FileNotFoundException, IOException, IllegalArgumentException {
    ProviderDirectory.validEntry(1234567);
  }
}