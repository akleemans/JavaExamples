package ch.kleemans.javaexamples.excelapi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * A class for writing Excel-Files with a bold header and auto-fitted column
 * sizes.
 * 
 * @author Adrianus Kleemans
 *
 */
public class ExcelAPI {

	/**
	 * Writes an array into a excel sheet and does some minimal makeup like
	 * highlighting the first row, change column sizes to fit the data, etc. It
	 * is based on the JXL-library for writing Excel files in Java.
	 * 
	 */
	public void write(String path, String name, List<String[]> data) {
		// Opening workbook
		System.out.println("Opening workbook...");
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(new File(path + "/" + name + ".xls"));
		} catch (IOException e) {
			System.out.println("Error while writing notebook:" + e.getMessage());
		}
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		System.out.println("Created workbook and worksheet.");

		// writing content
		System.out.println("Writing content...");
		try {
			for (int i = 0; i < data.size(); i++) {
				String[] s = data.get(i);
				for (int j = 0; j < s.length; j++) {
					Label label = new Label(j, i, s[j]);
					if (i == 0) { // header
						WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
						cellFont.setBoldStyle(WritableFont.BOLD);
						WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
						label = new Label(j, i, s[j], cellFormat);
					}
					sheet.addCell(label);
				}
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}

		sheetAutoFitColumns(sheet);

		// save + close
		try {
			workbook.write();
			workbook.close();
			System.out.println("Finished. Content written and workbook closed.");
		} catch (IOException | WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-fits the columns of a sheet and sets an appropriate width
	 * 
	 * @param sheet
	 */
	private void sheetAutoFitColumns(WritableSheet sheet) {
		for (int i = 0; i < sheet.getColumns(); i++) {
			Cell[] cells = sheet.getColumn(i);
			int longestStrLen = -1;

			if (cells.length == 0)
				continue;

			/* Find the widest cell in the column. */
			for (int j = 0; j < cells.length; j++) {
				if (cells[j].getContents().length() > longestStrLen) {
					String str = cells[j].getContents();
					if (str == null || str.isEmpty())
						continue;
					longestStrLen = str.trim().length();
				}
			}

			/* If not found, skip the column. */
			if (longestStrLen == -1)
				continue;

			/* If wider than the max width, crop width */
			if (longestStrLen > 255)
				longestStrLen = 255;

			CellView cv = sheet.getColumnView(i);
			cv.setSize(longestStrLen * 256 + 100);
			sheet.setColumnView(i, cv);
		}
	}
}
