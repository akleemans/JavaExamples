package ch.kleemans.javaexamples.excelapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelAPITest {

	public static void main(String[] args) {
		List<String[]> dataList = new ArrayList<String[]>();
		String[][] data = { { "Fruits", "Number", "Price", "Total Price" }, { "Apples", "15", "0.6", "9" },
				{ "Pears", "3", "0.9", "2.7" }, { "Oranges", "9", "0.5", "4.5" } };
		Collections.addAll(dataList, data);
		ExcelAPI excel = new ExcelAPI();
		excel.write(".", "fruit_prices", dataList);
	}

}
