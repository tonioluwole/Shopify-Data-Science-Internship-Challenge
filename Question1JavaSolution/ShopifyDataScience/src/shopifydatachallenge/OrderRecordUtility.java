package shopifydatachallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

/**
 * A Charactor Records toolkit based on list of OrderRecords
 */
public class OrderRecordUtility {
	
	/**
	 * Used in sortOrderAmount()
	 */
	private Comparator<OrderRecord> OrderAmountComparator = new OrderAmountComparator();
	
	/**
	 * Initialized string variable used for storing column names
	 */
	private String columnNames = null; 
	
	/**
	 * for storing order Records,
	 */
	List<OrderRecord> records = new ArrayList<OrderRecord>();
	
	/**
	 * Main part of the order Records System, it prints what are basically status messages,
	 * and catches exceptions thrown. Any exception caught here will result in a message output to the
	 * user with any details, followed by this method returning so the program can shut down.
	 */
	public void processFile() {
		
		try {
		System.out.println("Attempting to open csv file...");
		loadFile();
		
		System.out.println("Sorting by order amount ...");
		sortOrderAmount();
		
		System.out.println("Saving to SortedByOrderAmount.csv ...");
		System.out.println("(Will overwrite any file with same name, sorry)");
		
		saveFile("SortedByOrderAmount.csv");
		
		System.out.println("order details Sorted and Saved.");
		System.out.println("Program by Olutoni Oluwole");
		// catches here
		newArray();
		} 
		catch(NoSuchElementException e) { 
			// Scanner could not read line?
			System.err.println("Could not read data from file.");
			System.err.println(e.getMessage());
		}
		catch(FormatterClosedException e) {
			// Formatter was closed and could not write?
			System.err.println("Could not write data to file.");
			System.err.println(e.getMessage());
		}
		catch(IllegalStateException e) { 
			// Scanner was closed and could not read?
			System.err.println("Could not read data from file.");
			System.err.println(e.getMessage());
		}
		catch(SecurityException e) {
			// File system not granting permission to write to file?
			System.err.println("System not permitting creation of and/or" +
					" writing to file.");
			System.err.println(e.getMessage());
		}
		catch(FileNotFoundException e) {
			// No file to read from 
			// (loading file before ever saving one?)
			// (user changed name of the file manually?)
			System.err.println("Could not locate file.");
			System.err.println(e.getMessage());
		}
		catch(IOException e) {
			// Input-Output (IO) problem of some sort
			System.err.println("Problem with file operation");
			System.err.println(e.getMessage());
		}
		catch(Exception e) {
			// Something else went wrong that was not addressed above
			System.err.println("Something unexpected happended.");
			e.printStackTrace();	
		}
	}
	/**
     * Loads records from a file with relative path from where the program
     * started from, and file name "OrderRecords.csv" to create a new list.
     * If the operation succeeds the new list is swapped into the class level
     * list variable. The older list and records are lost.
 
     * @see OrderRecord
     * @throws java.io.IOException If there is a problem reading from file.
     * @throws java.lang.NumberFormatException If there is non-numerical data where it is expected in the file.
     * @throws java.lang.IllegalArgumentException If there is a data validation rule broken in class orderRecord
     * @throws java.util.NoSuchElementException If the Scanner cannot read a line
     * @throws java.lang.IllegalStateException If the Scanner is closed and an attempt to read is made.
     */
	private void loadFile() throws IOException, NumberFormatException,
	IllegalArgumentException, NoSuchElementException, IllegalStateException {
		
		records.clear();
		
		List<OrderRecord> newList = new ArrayList<OrderRecord>();
		int count = 0;
	    try (Scanner input = new Scanner(Paths.get("OrderRecords.csv"))) {
	
	    	columnNames = input.nextLine();
		
			while(input.hasNextLine()) {
				count++; // count the records
				String[] data = input.nextLine().split(","); // parsing to separate by comma
				
				OrderRecord order = new OrderRecord(); // instantiation
				
				order.setOrderID(Integer.parseInt(data[0]));
				order.setShopID(Integer.parseInt(data[1]));
				order.setUserID(Integer.parseInt(data[2]));
				order.setOrderAmount(Integer.parseInt(data[3]));
				order.setTotalItems(Integer.parseInt(data[4]));
				order.setPaymentMethod(data[5]);
				
				newList.add(order);
			}
		
		}
		catch(NumberFormatException e) {
			throw new IllegalArgumentException(
					"Record in file on line number " + count + 
					" has non-numerical data for distance field.", e);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException(
					"Record in file on line number " + count + 
					" violates data validation rules.", e); 
		}
		records = newList;
	}

	/**
	 * Sorts the list by OrderAmount value of the referenced orderRecord objects 
	 * @see OrderAmountComparator
	 */
	private void sortOrderAmount() {
		Collections.sort(records, OrderAmountComparator);
	}
	
	/**
	 * Iterates over the list, and uses the orderRecord class toString() to write comma-separated-value records into the file.
	 * Any existing file will be replaced, if the file does not exist it will be created if possible.
	 * @throws java.io.FileNotFoundException If the file cannot be found
	 * @throws java.lang.SecurityException If there is a security violation
	 * @throws java.util.FormatterClosedException If the formater is closed when attempting to write
	 */
	private void saveFile(String fileName) throws FileNotFoundException, SecurityException,	FormatterClosedException{
		
		try(Formatter output = new Formatter("SortedbyOrderAmount.csv")){

			// the first line written is column names separated by commas
			for (int i = 1; i < 2; i++) {
				output.format("%s%n", columnNames);
			}
			
			// looping and write out the record data, adding line terminator.
			sortOrderAmount();
			for(OrderRecord order: records) {
				output.format("%s%n", order.toString());
			} 
		}catch(FileNotFoundException | SecurityException | FormatterClosedException e) {
			throw e;
		}	
	}
	
	private void newArray() throws IOException {
	    try (Scanner input2 = new Scanner(Paths.get("SortedbyOrderAmount.csv"))) {
	
	    	String column = input2.nextLine();
		
			while(input2.hasNextLine()) {
			
				int[] array = new int[5000];
	            for (int i=0; i < 5001; i ++){
	            	while (input2.hasNextLine()) {
	    				OrderRecord order = new OrderRecord(); // instantiation
	            		array[i] = order.getOrderAmount();
	            	}
            	Arrays.sort(array);
                double median;
                if (array.length % 2 == 0)
                    median = ((double)array[array.length/2] + (double)array[array.length/2 - 1])/2;
                else
                    median = (double) array[array.length/2];
            	System.out.print(median);
	            }			
			}
	    }
	}
}
