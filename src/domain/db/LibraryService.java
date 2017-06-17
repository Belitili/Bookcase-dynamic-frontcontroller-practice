package domain.db;

import java.util.Collection;

import domain.model.Book;

public interface LibraryService {
	
	//public Book create(String isbn);
	public Collection<Book> read();
	// placeholder for update method
	public void delete(String isbn);
	
	public void addBook(Book book);
	public Book getBook(String isbn);
	
}
