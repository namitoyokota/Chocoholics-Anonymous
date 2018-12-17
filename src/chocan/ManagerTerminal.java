/*
 * Author: Conrad Spear
 */

package chocan;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ManagerTerminal extends Terminal{

	private static Scanner reader = new Scanner(System.in);

	/**
	 * managerMenu() Allows manager to select the type of report they want to generate and then generates the report (used by Manager)
	 * @param none	
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void managerMenu() throws FileNotFoundException, IOException{

		int option = 0;

		System.out.println("\nManager Terminal");
		System.out.println("1.) Generate Summary Report");
		System.out.println("2.) Generate Member Report");
		System.out.println("3.) Generate Provider Report");
		System.out.println("4.) Back to Main Terminal");
		System.out.print(" Option: ");

		option = reader.nextInt();
		reader.nextLine();

		if (option == 1) { // generate summary report
			System.out.println("\nGenerate Summary Report");

			System.out.print("Please name the file: ");
			String name = reader.next();

			GenerateReport.printSummaryReport(name);
		}

		else if (option == 2) { // generate member report
			System.out.println("\nGenerate Member Report");
			System.out.println("1.) Generate Reports for All Members");
			System.out.println("2.) Generate Individual Member Report");
			
			option = reader.nextInt();
			reader.nextLine();
			
			if(option == 1){
				GenerateReport.generateMember();
			}
			else if(option == 2){
				
				System.out.print("Enter member's number: ");
				int number = reader.nextInt();
				reader.nextLine();

				GenerateReport.generateIndividualMember(number);
			}
			
		}

		else if (option == 3) { // generate provider report
			System.out.println("\nGenerate Provider Report");
			System.out.println("1.) Generate Reports for All Providers");
			System.out.println("2.) Generate Individual Provider Report");

			option = reader.nextInt();
			reader.nextLine();
			
			if(option == 1){
				GenerateReport.generateProvider();
			}
			else if(option == 2){
				
				System.out.print("Enter provider's number: ");
				int number = reader.nextInt();
				reader.nextLine();
				GenerateReport.generateIndividualProvider(number);
			}
			
		}

		return;
	}
}
