package ch.kleemans.javaexamples.csv;

import java.util.ArrayList;

/**
 * Test class for {@link OpenCSV}. Reads a CSV file and saves it using another
 * separator character.
 * 
 * @author Adrianus Kleemans
 *
 */
public class CSVTest {

	public static void main(String[] args) {
		OpenCSV csv = new OpenCSV();

		// read from CSV file
		ArrayList<String[]> data = csv.read("src/ch/kleemans/javaexamples/csv/letter_freq.csv", '\t');
		System.out.println("First entry: " + data.get(0)[0] + " - " + data.get(0)[1]);

		// save back to another file
		csv.write("src/ch/kleemans/javaexamples/csv/letter_freq_semicolon.csv", data, ';');
		System.out.println("Saving succesful.");
	}
}