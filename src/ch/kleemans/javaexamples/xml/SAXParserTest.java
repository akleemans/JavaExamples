package ch.kleemans.javaexamples.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Test class for {@link SAXParser}. Reads the library in books.xml and prints
 * the first book.
 * 
 * @author Adrianus Kleemans
 *
 */
public class SAXParserTest {

	public static void main(String[] args) {
		String xml = getFile("books.xml");

		SAXParser parser = new SAXParser();
		ArrayList<Book> library = null;

		System.out.println("Parsing xml...");
		try {
			library = parser.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Books: ");
		for (Book book : library) {
			System.out.println(book.toString());
		}
	}

	private static String getFile(String file) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
