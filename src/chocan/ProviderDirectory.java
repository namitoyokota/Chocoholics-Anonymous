/*
 * Author: Ashley Phan
 */

package chocan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProviderDirectory {


  public ProviderDirectory() {

  }

  /**
   * validEntry() takes in the service code and returns the provider directory entry if it exists in the database
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @throws IllegalArgumentException
   * @return ProviderDirectoryEntry
   */   
  public static ProviderDirectoryEntry validEntry(int code)
      throws FileNotFoundException, IOException, IllegalArgumentException {
    ArrayList<ProviderDirectoryEntry> pde = Terminal.ReadProviderDirectoryEntry();
    ProviderDirectoryEntry entry = new ProviderDirectoryEntry();
    String temp = Integer.toString(code);
    if (temp.length() != 6) {
      throw new IllegalArgumentException("The service code must be 6 digits long!");
    }
    for (ProviderDirectoryEntry pd : pde) {
      if (pd.getServiceCode() == code) {
        entry = pd;
      }
    }

    return entry;
  }

  /**
   * findEntry() takes in the service code and returns a boolean if the providerDirectoryEntry exists
   * @param none
   * @throws FileNotFoundException
   * @throws IOException
   * @return boolean
   */ 
  public static boolean findEntry(int code) throws FileNotFoundException, IOException {
    ArrayList<ProviderDirectoryEntry> pde = Terminal.ReadProviderDirectoryEntry();
    ProviderDirectoryEntry entry = new ProviderDirectoryEntry();
    boolean flag = false;
    for (ProviderDirectoryEntry pd : pde) {
      if (pd.getServiceCode() == code) {
        entry = pd;
        flag = true;
      }
    }

    return flag;
  }

}
