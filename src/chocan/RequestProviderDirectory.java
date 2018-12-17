/*
 * Author: Josh Engler
 */

package chocan;
import java.util.ArrayList;

import chocan.ProviderDirectory;
import chocan.ProviderDirectoryEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RequestProviderDirectory {
	
	/**
	 * requestDirectory() calls the rewriteDirectory() function within the Terminal class.
	 * 				  
	 * @param none	
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return void
	 */
	public static void requestDirectory() throws FileNotFoundException, IOException {
	      Terminal.rewriteDirectory();
	  }

}
