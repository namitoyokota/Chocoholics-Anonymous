/**
 * ValidateMember: Used to find a user defined member number in the system and check to see whether
 * it is a valid number and checks if the member is suspended or not.
 *
 * @author Hunter Esposito
 * @version 1.0
 */

package chocan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ValidateMember {

  /**
   * The validate member function, validates user inputted member number
   *
   * @param memNum The member number to search for
   * @return If corresponding member number is found, return that MemberAccounts obj,
   *         else, return empty MemberAccounts obj.
   * @throws FileNotFoundException Throws if members.txt is unable to be found.
   * @throws IOException Throws if there are problems with file IO
   */
  public static MemberAccounts validateMember(int memNum)
      throws FileNotFoundException, IOException {
    ArrayList<MemberAccounts> me = Terminal.ReadMember();
    int found = -1;
    MemberAccounts ma = new MemberAccounts();
    ma.setStatus(false);

    for (MemberAccounts account : me) {
      if (account.getNumber() == memNum) {
        found = 0;
        if (account.getStatus() == false) {
          //System.out.println("\nSuspended");
          JOptionPane.showMessageDialog(null,
              "The member with member number " + memNum + " has been suspended.");
        } else {
          //System.out.println("\nValidated");
          JOptionPane.showMessageDialog(null, "Valid member number");
        }
        ma = account;
      }
    }
    if (found == -1) {
      //System.out.println("\nInvalid");
      JOptionPane.showMessageDialog(null, "Invalid member number");
    }
    return ma;
  }
}
