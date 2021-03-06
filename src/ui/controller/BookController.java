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
import javafx.util.Pair;

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
			case "DELETE" :
				destination = deleteBook(request, response);
				break;
			case "DELETEConfirm" : 
				destination = deleteConfirm(request, response);
				break;
			case "UPDATE" : 
				destination = updateBook(request, response);
				break;
			case "UPDATEConfirm" : 
				destination = updateConfirm(request, response);
				break;
			default:
				destination = "index.jsp";
				break;
		}
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	protected String addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination;
		Pair<HashMap<String, String>,Book> errorTestResult = this.getErrorList(request, response);
		HashMap<String, String> errors = errorTestResult.getKey();
		Book newBook = errorTestResult.getValue();
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String nrOfPages = request.getParameter("nrpages");
		String isbn = request.getParameter("isbn");
		
		if (library.getBook(isbn)!=null) {
			errors.put("isbn", "There already is a book with this isbn.");
		}
		if (errors.size() <= 0) {
			library.addBook(newBook);
			destination = this.showBooks(request, response);
		} else {
			request.setAttribute("errors", errors);
			request.setAttribute("book", newBook);
			destination = "boekenFormulier.jsp";
		}
		return destination;
	}
	
	protected String showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "boekenkast.jsp";
		Collection<Book> books = library.read();
		request.setAttribute("books", books);
		return destination;
	}
	
	protected String deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "boekDeleteConfirmation.jsp";
		String isbn = request.getParameter("isbn");
		
		request.setAttribute("bookToDelete", library.getBook(isbn));
		
		return destination;
	}
	
	protected String deleteConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		library.delete(isbn);
		
		String destination = this.showBooks(request, response);
		return destination;
	}
	
	protected String updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "boekUpdate.jsp";
		String isbn = request.getParameter("isbn");
		
		request.setAttribute("bookToUpdate", library.getBook(isbn));
		
		return destination;
	}
	
	protected String updateConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String nrpages = request.getParameter("nrpages");
		
		HashMap<String, String> errors = this.getErrorList(request, response).getKey();
		
		String destination;
		Book book = library.getBook(isbn);
		if (errors.size() <= 0) {
			book.setTitle(title);
			book.setAuthor(author);
			book.setNrOfPages(Integer.valueOf(nrpages));
			destination = this.showBooks(request, response);
		} else {
			request.setAttribute("bookToUpdate", book);
			request.setAttribute("errors", errors);
			destination = "boekUpdate.jsp";
		}
		
		return destination;
	}
	
	private Pair<HashMap<String, String>, Book> getErrorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		return new Pair<>(errors, newBook);
	}

}
