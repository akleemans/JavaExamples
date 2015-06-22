package ch.kleemans.javaexamples.xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple Book class with some basic data about a book.
 * 
 * @author Adrianus Kleemans
 *
 */
public class Book {

	private int id;
	private String author;
	private String title;
	private String publisher;
	private Date publishDate;

	public Book(String id, String author, String title, String publisher, String publishDate) {
		this.id = Integer.parseInt(id);
		this.author = author;
		this.title = title;
		this.publisher = publisher;

		// parse Date in format dd.MM.yyyy
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			this.publishDate = df.parse(publishDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("MMMM yyyy");
		String dateStr = df.format(publishDate);
		String bookStr = "[" + id + "] " + author + ": " + title + ". " + publisher + " (" + dateStr + ").";
		return bookStr;
	}
}
