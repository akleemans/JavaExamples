package ch.kleemans.javaexamples.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Implementation for parsing a specific XML file. This SAXParser reads a book
 * catalog and deserializes it into an Array of books.
 * 
 * @author Adrianus Kleemans
 *
 */
public class SAXParser extends DefaultHandler {

	private HashMap<String, String> book;
	private String node;
	private static ArrayList<Book> books;

	/**
	 * Parses a given XML string.
	 * 
	 * @param xml
	 *            a XML string
	 * @return books an ArrayList of books
	 * @throws Exception
	 *             when reading the xml fails
	 */
	public ArrayList<Book> parse(String xml) throws Exception {
		books = new ArrayList<Book>();
		XMLReader xr = XMLReaderFactory.createXMLReader();
		SAXParser handler = new SAXParser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		StringReader r = new StringReader(xml);
		xr.parse(new InputSource(r));
		return books;
	}

	public SAXParser() {
		super();
	}

	// public void startDocument () { }
	// public void endDocument () { }

	public void startElement(String uri, String name, String qName, Attributes atts) {
		if (qName.equals("catalog")) {
			System.out.println("Start reading catalog...");
		} else if (qName.equals("book")) {
			book = new HashMap<String, String>();
			book.put("id", atts.getValue("id"));
		} else {
			node = qName;
		}
	}

	public void endElement(String uri, String name, String qName) {
		if (qName.equals("book"))
			books.add(new Book(book.get("id"), book.get("author"), book.get("title"), book.get("publisher"), book
					.get("publish_date")));
		else if (qName.equals("catalog"))
			System.out.println("Finished reading catalog.");
	}

	public void characters(char ch[], int start, int length) {
		String attr = "";
		for (int i = start; i < start + length; i++) {
			attr += ch[i];
		}
		attr = attr.trim();
		if (attr != null && !attr.equals("")) {
			book.put(node, attr);
		}
	}
}