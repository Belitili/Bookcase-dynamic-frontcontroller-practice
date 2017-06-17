package domain.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import domain.model.Book;

public class LibraryServiceNoDB implements LibraryService {
	
	private Map<String, Book> books = new HashMap<String, Book>();

	public LibraryServiceNoDB() {
		this.addBook(new Book("Test1","Test2", 200, "123 12 1234 1"));
	}

	@Override
	public Collection<Book> read() {
		return books.values();
	}

	@Override
	public void delete(String isbn) {
		books.remove(isbn);
	}

	@Override
	public Book getBook(String isbn) {
		return books.get(isbn);
	}

	@Override
	public void addBook(Book book) {
		books.put( book.getIsbn(), book);		
	}

}




