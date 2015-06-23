package ch.kleemans.javaexamples.csv;

import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVReadProc;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Writes data into a CSV file using the free library openCSV 2.4. Separator
 * character (e.g. , ; | \t) has to as specified.
 * 
 * @author Adrianus Kleemans
 * 
 */
public class OpenCSV {

	/**
	 * Writes data into a CSV file.
	 * 
	 * @param filename
	 *            filename of csv to write
	 * @param data
	 *            a list of String[]
	 * @param separator
	 *            separator to use
	 */
	public void write(String filename, final List<String[]> data, char separator) {
		CSV csv = CSV.separator(separator) // delimiter of fields
				.quote('"') // quote character
				.create(); // new instance is immutable

		csv.write(filename, new CSVWriteProc() {
			@Override
			public void process(CSVWriter out) {
				out.writeAll(data);
			}
		});
	}

	/**
	 * Reads from a CSV file.
	 * 
	 * @param filename
	 *            filename of csv to read from
	 * @param separator
	 *            separator to use
	 * @return data a list of data
	 */
	public ArrayList<String[]> read(String filename, char separator) {
		final ArrayList<String[]> data = new ArrayList<String[]>();

		CSV csv = CSV.separator(separator).create();
		csv.read(filename, new CSVReadProc() {
			public void procRow(int rowIndex, String... values) {
				data.add(values);
			}
		});
		return data;
	}
}
