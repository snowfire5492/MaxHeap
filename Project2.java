/**
 * CS 241
 * Professor: Dr. Wei Sophie
 *
 * Project #2
 *
 * <create a Max-heap from at least 100 integers using both sequential and optimal 
 * methods of insertion , and test software using (1) 20 sets of 100 randomly generated
 * integers to build 20 heaps and find average number of swaps for both methods, and 
 * (2) with fixed integers 1 - 100, printing first 10, the average number of swaps for both 
 * optimal and insertion methods, and then perform 10 removal operations and output first
 * 10 in order again. Program will print to console and to output.txt located in EricSchenck2.zip>
 *
 * @author Eric Schenck
 * last modified: 10/23/17
 */

import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Project2 extends MaxHeap{
	public static void main(String[] args) throws IOException{
		
		int totalSequentialSwaps = 0;				// used to store Total number of Swaps
		int totalOptimalSwaps = 0;					// for each add method
		int userInput = 0;							// for user input (directly from String input)
		int randomNumber = 0;						// used to store random number temporarily
		int[] entries = new int[100];				// to store values prior to heap insertion 							
		boolean validInput = false;					// to validate user input
		boolean exitOption = false;					// used for program loop
		String input = "";							// for initial user input (will be parsed to userInput)
		String output = "";							// used to save and print test output
		String seqOutput = "";						// used to save and print sequential output
		String optOutput = "";						// used to save and print optimal output
		String prompt = "";
		
		String fileName = "C://Users//Eric//"		// file path for output.txt
				+ "Desktop//output.txt";
		
		
		//String fileName = System.getProperty("user.dir") 	// Handles fileName 
			//	+ File.separator + "output.txt";
		
		File file = new File(fileName);						
		FileWriter clearFile = new FileWriter(file, false);	// clears existing file for new input
		clearFile.close();
		
															// using FileWriter to write to output.txt
		FileWriter outFile = new FileWriter(file , true); 	// will append data to file from here out
															// and not just overwrite the file
		
		Scanner keyboard = new Scanner(System.in);			// for user input
		Random rand = new Random();							// used for random integer generation
										
		while(!exitOption){
					
			prompt = "===============================================================================\n"
					+ "Please select how to test the program:\n"
					+ "(1) 20 sets of 100 randomly generated integers\n"
					+ "(2) Fixed Integer values 1-100\n" 
					+ "(3) Exit\n"
					+ "Enter choice: ";
		
			System.out.print(prompt);						// prints prompt to console
			
			validInput = false;								// reseting validInput

			while(!validInput){								// continues loop until given valid input
				input = keyboard.nextLine();
				try{
					userInput = Integer.parseInt(input);	// only parses integer value 
					if(userInput == 1 || userInput == 2 || userInput == 3)
						validInput = true;					// input is considered completely valid
					else
						System.out.println("Incorrect Entry: Please enter (1) , (2) or (3) only!");
				}catch( Exception e ){
					System.out.println("Incorrect Entry: Please enter integer value only!");
				}
			}
			outFile.write(prompt + userInput);				// output to output file, includes userInput
		
			if(userInput == 1){								// for Test (1) selection
				
				totalSequentialSwaps = 0;					// clearing out from last test
				totalOptimalSwaps = 0;
				
				for(int i = 0; i < 20 ; ++i){				// 20 maxHeaps
			
					MaxHeap sequentialHeap = new MaxHeap();	// sequentialHeap for sequential testing
					MaxHeap optimalHeap = new MaxHeap();	// optimalHeap for optimal testing
						
					for(int j = 0; j < entries.length; ++j){	// clears out array of random integers
															// or else repeatValue has issues
					entries[j] = 0;
					}
				
					for(int j = 0; j < entries.length; ++j){	// fills an array of non-repeating random integers
					
						randomNumber = rand.nextInt(100) + 1 ; 	// generating random numbers from 1 - 100 
					
						if(!repeatValue(entries , randomNumber)){ // no repeating values in array 
							entries[j] = randomNumber;
						}else									// if repeatValue found then iterate for loop again
							--j;
					}
				
					for (int j = 0; j < entries.length; ++j){	// fills up sequentialHeap					
																// adds entries and returns number of swaps
						totalSequentialSwaps += sequentialHeap.addSequential(entries[j]);
					}
																// adds entries and returns number of swaps
					totalOptimalSwaps += optimalHeap.addOptimal(entries);	// fills optimal heap
						
				}// after 20 sets of 100 non-repeating random integers 
			
				output = "\n\nAverage swaps for series of insertions: " + totalSequentialSwaps/20 
						+ "\nAverage swaps for optimal method: " + totalOptimalSwaps/20 + "\n";
							
				System.out.print(output);						// prints output to console
				outFile.write(output); 							// prints output to output.txt file
			
			}else if(userInput == 2){ 							// for test selection 2
			
				MaxHeap sequentialHeap = new MaxHeap();			// sequentialHeap for sequential testing
				MaxHeap optimalHeap = new MaxHeap();			// optimalHeap for optimal testing
			
				totalSequentialSwaps = 0;						// resetting swaps
		
				
				for(int i = 1; i <= 100; ++i){
					
					
					entries[i-1] = i;							// creates an array of entries from 1-100
				
					totalSequentialSwaps += sequentialHeap.addSequential(i);
				}												// adds values Sequential method into a heap
																// returns number of swaps required to perform add
					
				totalOptimalSwaps = optimalHeap.addOptimal(entries);
																// adds values Optimal method into heap
																
				seqOutput = "";									// clearing output strings from last test		
				optOutput = "";									
																// Formatting output with block of code
				seqOutput += "\n\nHeap built using series of insertions: ";
				optOutput += "\n\nHeap built using optimal method: ";
				for (int i = 1; i <= 10; ++i){
					seqOutput += sequentialHeap.getIndexValue(i) + ",";
					optOutput += optimalHeap.getIndexValue(i) + ",";
				}
				seqOutput += "...\nNumber of Swaps: " + totalSequentialSwaps;
				optOutput += "...\nNumber of Swaps: " + totalOptimalSwaps;
			
				for (int i = 0 ; i < 10; ++i){					// removing 10 Max values for both heaps
					sequentialHeap.removeMax();
					optimalHeap.removeMax();
				}
				seqOutput += "\nHeap after 10 removals: ";
				optOutput += "\nHeap after 10 removals: ";
				for (int i = 1; i <= 10; ++i){
					seqOutput += sequentialHeap.getIndexValue(i) + ",";
					optOutput += optimalHeap.getIndexValue(i) + ",";
				}
				seqOutput += "...";
				optOutput += "...\n";							// End of Formatting 
			
				System.out.print(seqOutput);					// printing formatted output to console
				System.out.print(optOutput);
			
				outFile.write(seqOutput);
				outFile.write(optOutput);						// printing formatted output to output.txt
			
			}else{
				output = "\nGoodbye!";
				System.out.print(output);
				outFile.write(output);
				exitOption = true;								// will exit program
			}
		}
		outFile.close(); 										// closing file to save
		keyboard.close();										// closing Scanner object
	}
	
	/**
	 * Additional function - used to check int[] entries for duplicate values
	 * I coded this here because it doesnt relate to MaxHeap, so i felt it shouldnt
	 * be include within that class.
	 * @param entries
	 * @param entry
	 * @return	true if entry is a repeatValue within array of integer
	 */
	public static boolean repeatValue(int[] entries , int entry){
		boolean isFound = false;
		
		A: for (int i = 0; i < entries.length; ++i){	// goes through array
			if(entries[i] == entry){					// if duplicate value found
				isFound = true;							// set isFound to true
				break A;								// exit for loop
			}
		}
		return isFound;									// return boolean answer 
	}
	
	
}
