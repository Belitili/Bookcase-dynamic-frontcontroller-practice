package ui.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DomainException;
import domain.db.LibraryService;
import domain.db.LibraryServiceNoDB;
import domain.model.Book;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibraryService library = new LibraryServiceNoDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String destination;
		
		switch(action) {
			case "ADD" :
				destination = addBook(request, response);
				break;
			case "READ" :
				destination = showBooks(request, response);
				break;
			default:
				destination = "index.jsp";
				break;
		}
		System.out.println("dest2: " + destination);
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	protected String addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination;
		HashMap<String, String> errors = this.getErrorList(request, response);
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String nrOfPages = request.getParameter("nrpages");
		String isbn = request.getParameter("isbn");
		
		if (library.getBook(isbn)!=null) {
			errors.put("isbn", "There already is a book with this isbn.");
		}
		System.out.println("errorsize: " + errors.size());
		if (errors.size() <= 0) {
			System.out.println("nonerr");
			library.addBook(new Book(title,author,Integer.valueOf(nrOfPages),isbn));
			destination = this.showBooks(request, response);
		} else {
			System.out.println("eorr");
			request.setAttribute("errors", errors);
			destination = "boekenFormulier.jsp";
		}
		System.out.println("dest1: " + destination);
		return destination;
	}
	
	protected String showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "boekenkast.jsp";
		Collection<Book> books = library.read();
		request.setAttribute("books", books);
		return destination;
	}
	
	private HashMap<String, String> getErrorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String nrOfPages = request.getParameter("nrpages");
		String isbn = request.getParameter("isbn");
		
		HashMap<String, String> errors = new HashMap<String, String>();
		Book newBook = new Book();
		
		try {
			newBook.setTitle(title);
		} catch (DomainException e) {
			errors.put("title", e.getMessage());
		}
		try {
			newBook.setAuthor(author);
		} catch (DomainException e) {
			errors.put("author", e.getMessage());
		}
		try {
			if (nrOfPages != null && !nrOfPages.trim().isEmpty()) {
				if (Integer.valueOf(nrOfPages) instanceof Integer) {
					newBook.setNrOfPages(Integer.valueOf(nrOfPages));
				} else {
					errors.put("nrpages","Fill in a number in number of pages");
				}
			} else {
				errors.put("nrpages","Fill in number of pages");
			}
		} catch (DomainException e) {
			errors.put("nrpages", e.getMessage());
		}
		try {
			newBook.setIsbn(isbn);
		} catch (DomainException e) {
			errors.put("isbn", e.getMessage());
		}
		
		return errors;
	}

}
