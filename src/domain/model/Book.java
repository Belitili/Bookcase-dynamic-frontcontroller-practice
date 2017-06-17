package domain.model;

import domain.DomainException;

public class Book {
	
	private String title = "";
	private String author = "";
	private int nrOfPages = 0;
	private String isbn = "";
	
	public Book() {
	}
	
	public Book(String title, String author, int nrOfPages, String isbn) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setNrOfPages(nrOfPages);
		this.setIsbn(isbn);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		if(title == null || title.trim().equals("")){
			throw new DomainException("The title for your book cannot be empty");
		}
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if(author == null || author.trim().equals("")){
			throw new DomainException("The author for your book cannot be empty");
		}
		this.author = author;
	}

	public int getNrOfPages() {
		return nrOfPages;
	}

	public void setNrOfPages(int nrOfPages) {
		if(nrOfPages <= 0){
			throw new DomainException("The number of pages for your book must be positive");
		}
		this.nrOfPages = nrOfPages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if(isbn == null || isbn.trim().equals("") || isbn.trim().replace("-","").length() != 13){
			throw new DomainException("The isbn code for your book should be a 13-digit code, possibly in 5 groups seperated by spaces or hyphens.");
		}
		this.isbn = isbn;
	}



}
